package in.creativekitten.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class AwsS3Service {

	public void uploadFile(String bucket, String fileName, InputStream inputStream)
			throws S3Exception, AwsServiceException, SdkClientException, IOException {

		S3Client client = S3Client.builder().build();

		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).build();

		client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
	}

	public void deleteImages(String bucketName, String productId) {

		String prefix = "assets/product-images/" + productId;
		List<String> images = listBucketObjects(bucketName, prefix);
		S3Client client = S3Client.builder().build();

		for (String image : images) {
			System.out.println("deleting image " + image);
			DeleteObjectRequest request = DeleteObjectRequest
					.builder()
					.bucket(bucketName)
					.key(image)
					.build();
			client.deleteObject(request);
		}

	}

	public List<String> listBucketObjects(String bucketName, String prefix) {

		S3Client client = S3Client.builder().build();

		ListObjectsRequest query = ListObjectsRequest
				.builder()
				.bucket(bucketName)
				.prefix(prefix)
				.build();

		ListObjectsResponse response = client.listObjects(query);
		List<String> list = response.contents().stream().map(e -> e.key()).collect(Collectors.toList());
		return list;

	}

}
