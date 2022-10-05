package in.creativekitten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;

@SpringBootTest
class CkadminSpringApplicationTests {

	@Test
	void test1() {
		
		String json = "{\n"
				+ "							\"colors\" : [\"Red\", \"Green\", \"Blue\"],\n"
				+ "							\"capacity\" : [\"64GB\", \"128GB\"]\n"
				+ "							}";
		Gson gson = new Gson();
		Map<String, Set<String>> map = gson.fromJson(json, Map.class);
		
//		Map<String, Set<String>> options = new HashMap<>();
		

		System.out.println(map.toString());
		
		
	}
	
}
