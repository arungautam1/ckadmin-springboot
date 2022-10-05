package in.creativekitten.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Product {

	private Integer id;
	private String title;
	private Double originalPrice;
	private Double discountedPrice;

	private String shortDescription;

	private String category;
	private String subcategory;

	private Double taxRate;
	private Integer stock;
	private Double shippingCost;
	private String deliveryTime;

	private Map<String, Set<String>> options = new HashMap<>();
	private Map<String, String> specifications = new HashMap<>();

	private Set<String> paragraphs = new HashSet<>();

	private Set<String> images = new HashSet<>();
	private String image0;

	public Product() {
		
	}
	
	@SuppressWarnings("unchecked")
	public Product(ProductInput input) {

		this.title = input.getTitle();
		this.originalPrice = input.getOriginalPrice();
		this.discountedPrice = input.getDiscountedPrice();

		this.shortDescription = input.getShortDescription();

		this.category = input.getCategory();
		this.subcategory = input.getSubcategory();
		this.taxRate = input.getTaxRate();
		this.stock = input.getStock();

		this.shippingCost = input.getShippingCost();
		this.deliveryTime = input.getDeliveryTime();

		this.options = new Gson().fromJson(input.getOptions(), Map.class);
		this.specifications = new Gson().fromJson(input.getSpecifications(), Map.class);

		addParagraphs(input.getParagraph1(), input.getParagraph2(), input.getParagraph3(), input.getParagraph4());
	}

	private void addParagraphs(String... paragraphs) {
		for (String p : paragraphs) {
			if (p != null && !p.isBlank()) {
				this.paragraphs.add(p);
			}
		}
	}

	@DynamoDbPartitionKey
	@DynamoDbAttribute(value = "id")
	public Integer getId() {
		return id;
	}

	@DynamoDbAttribute(value = "title")
	public String getTitle() {
		return title;
	}

	@DynamoDbAttribute(value = "shortDescription")
	public String getShortDescription() {
		return shortDescription;
	}

	@DynamoDbAttribute(value = "category")
	public String getCategory() {
		return category;
	}

	@DynamoDbAttribute(value = "subCategory")
	public String getSubcategory() {
		return subcategory;
	}

	@DynamoDbAttribute(value = "originalPrice")
	public Double getOriginalPrice() {
		return originalPrice;
	}

	@DynamoDbAttribute(value = "discountedPrice")
	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	@DynamoDbAttribute(value = "taxRate")
	public Double getTaxRate() {
		return taxRate;
	}

	@DynamoDbAttribute(value = "stock")
	public Integer getStock() {
		return stock;
	}

	@DynamoDbAttribute(value = "shippingCost")
	public Double getShippingCost() {
		return shippingCost;
	}

	@DynamoDbAttribute(value = "deliveryTime")
	public String getDeliveryTime() {
		return deliveryTime;
	}

	@DynamoDbAttribute(value = "paragraphs")
	public Set<String> getParagraphs() {
		return paragraphs;
	}

	@DynamoDbAttribute(value = "options")
	public Map<String, Set<String>> getOptions() {
		return options;
	}

	@DynamoDbAttribute(value = "specifications")
	public Map<String, String> getSpecifications() {
		return specifications;
	}

	@DynamoDbAttribute(value = "images")
	public Set<String> getImages() {
		return images;
	}

	@DynamoDbAttribute(value = "image0")
	public String getImage0() {
		return image0;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setOptions(Map<String, Set<String>> options) {
		this.options = options;
	}

	public void setSpecifications(Map<String, String> specifications) {
		this.specifications = specifications;
	}

	public void setParagraphs(Set<String> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public void setImages(Set<String> images) {
		this.images = images;
	}

	public void setImage0(String image0) {
		this.image0 = image0;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", originalPrice=" + originalPrice + ", discountedPrice="
				+ discountedPrice + ", shortDescription=" + shortDescription + ", category=" + category
				+ ", subcategory=" + subcategory + ", taxRate=" + taxRate + ", stock=" + stock + ", shippingCost="
				+ shippingCost + ", deliveryTime=" + deliveryTime + ", options=" + options + ", specifications="
				+ specifications + ", paragraphs=" + paragraphs + ", images=" + images + ", image0=" + image0 + "]";
	}

}
