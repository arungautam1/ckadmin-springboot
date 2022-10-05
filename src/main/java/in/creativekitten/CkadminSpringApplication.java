package in.creativekitten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import in.creativekitten.repository.AppConfig;

@SpringBootApplication
public class CkadminSpringApplication {

	@Autowired
	private AppConfig config;

	public static void main(String[] args) {
		SpringApplication.run(CkadminSpringApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println("environment: " + config.getEnv());
		System.out.println("bucketName: " + config.getBucketName());
		System.out.println("productsTable: " + config.getProductsTable());
//	    AwsS3Service s3 = new AwsS3Service();
//	    s3.listBucketObjects("creativekitten.in");
	}

}
