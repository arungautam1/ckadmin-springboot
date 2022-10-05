package in.creativekitten.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${environment}")
	private String environment;

	@Value("${dev.bucketName}")
	private String devBucketName;

	@Value("${dev.productsTable}")
	private String devProductsTable;

	@Value("${prod.bucketName}")
	private String prodBucketName;

	@Value("${prod.productsTable}")
	private String prodProductsTable;

	public String getEnv() {
		return environment;
	}

	public String getBucketName() {
		return getEnv().equals("prod") ? prodBucketName : devBucketName;
	}

	public String getProductsTable() {
		return getEnv().equals("prod") ? prodProductsTable : devProductsTable;
	}
}
