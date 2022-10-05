package in.creativekitten.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Service
public class AwsDynamodbService {

	public <T> void putObject(String tableName, T object, Class<T> clazz) {
		DynamoDbEnhancedClient client = DynamoDbEnhancedClient.builder().build();
		DynamoDbTable<T> table = client.table(tableName, TableSchema.fromBean(clazz));
		System.out.print("putting object in table " + tableName);
		System.out.println(object.toString());
		table.putItem(object);
	}

	public void deleteProduct(String tableName, String productId) {

		HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();

		keyToGet.put("id", AttributeValue.builder()
				.n(productId)
				.build());

		DeleteItemRequest deleteReq = DeleteItemRequest.builder()
				.tableName(tableName)
				.key(keyToGet)
				.build();

		try {
			DynamoDbClient client = DynamoDbClient.builder().build();
			client.deleteItem(deleteReq);
		} catch (DynamoDbException e) {
			throw new RuntimeException("Error deleting productId " + productId + ": " + e.getMessage());
		}
	}

}
