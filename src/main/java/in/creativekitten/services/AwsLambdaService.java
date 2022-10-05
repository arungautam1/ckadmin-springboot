package in.creativekitten.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import in.creativekitten.entity.AwsLambdaResponse;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@Service
public class AwsLambdaService {

	public int getNextSequence(String sequenceKey) {
		LambdaClient awsLambda = LambdaClient.builder().build();
		Map<String, String> payloadMap = new HashMap<>();
		payloadMap.put("sequence_key", sequenceKey);
		String payloadJson = new Gson().toJson(payloadMap);
		InvokeRequest request = InvokeRequest.builder().functionName("prod_ck_next_sequence")
				.payload(SdkBytes.fromUtf8String(payloadJson)).build();

		InvokeResponse res = awsLambda.invoke(request);
		AwsLambdaResponse response = new Gson().
				fromJson(res.payload().asUtf8String(), AwsLambdaResponse.class);
		
		int nextSequence = Integer.valueOf(response.getBody());
		
		System.out.println("next sequence for " + sequenceKey + ": " + nextSequence);
		return nextSequence;
	}

}
