package in.creativekitten.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.creativekitten.entity.Product;
import in.creativekitten.entity.ProductInput;
import in.creativekitten.repository.AppConfig;
import in.creativekitten.services.AwsDynamodbService;
import in.creativekitten.services.AwsLambdaService;
import in.creativekitten.services.AwsS3Service;

@Controller
public class WebController {

	@Autowired
	private AppConfig config;

	@Autowired
	private AwsS3Service s3Service;

	@Autowired
	private AwsDynamodbService dynamodbService;

	@Autowired
	private AwsLambdaService awsLambdaService;

	@GetMapping({ "/", "/home" })
	public String root() {
		return "home";
	}

	@GetMapping("/createProduct")
	public String createProductForm() {
		return "createProduct";
	}

	@PostMapping("/createProduct")
	public String createProduct(Model model, @ModelAttribute ProductInput productInput,
			@RequestParam("image1") MultipartFile image1, @RequestParam("image2") MultipartFile image2,
			@RequestParam("image3") MultipartFile image3, @RequestParam("image4") MultipartFile image4) {

		Product product = new Product(productInput);
		product.setId(awsLambdaService.getNextSequence("product_id"));

		for (MultipartFile multiplart : List.of(image1, image2, image3, image4)) {

			String originalFileName = multiplart.getOriginalFilename();

			if (originalFileName != null && !originalFileName.isBlank()) {
				String prefix = "assets/product-images";
				String uuid = UUID.randomUUID().toString();
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				String s3FileName = String.join("/", prefix, String.valueOf(product.getId()), uuid) + extension;

				uploadImage(config.getBucketName(), s3FileName, multiplart);
				product.getImages().add(s3FileName);
				if (product.getImage0() == null) {
					product.setImage0(s3FileName);
				}
			}
		}

		System.out.println("Creating " + product.toString());
		String message;
		try {
			dynamodbService.putObject(config.getProductsTable(), product, Product.class);
			message = "Product created successfully!";
		} catch (Exception ex) {
			message = "Error creating product: " + ex.getMessage();
		}
		System.out.println(message);
		model.addAttribute("message", message);
		return "createProductSuccess";
	}

	private void uploadImage(String bucket, String s3FileName, MultipartFile multipart) {

		System.out.println("uploading file " + s3FileName + " to bucket " + bucket);
		try {
			s3Service.uploadFile(bucket, s3FileName, multipart.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error occurred while upload image to s3, Exception: " + e.getMessage());
		}
	}

	@GetMapping("/createProductSuccess")
	public String createProductSuccess() {
		return "createProductSuccess";
	}

	@GetMapping("/deleteProduct")
	public String deleteProductForm() {
		return "deleteProduct";
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(Model model, String productId) {
		try {
			System.out.println("delete product request received for productId " + productId);
			System.out.println("deleting images ..");
			s3Service.deleteImages(config.getBucketName(), productId);
			System.out.println("deleting from dynamodb .. ");
			dynamodbService.deleteProduct(config.getProductsTable(), productId);
			System.out.println("product " + productId + " deleted successfully!");
			model.addAttribute("message", "ProductId " + productId + " deleted!");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "deleteProduct";
	}

}
