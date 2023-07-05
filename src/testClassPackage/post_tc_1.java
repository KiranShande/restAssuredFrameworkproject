package testClassPackage;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctionPackage.API_Common_Fucntion;
import commonFunctionPackage.utility_CommonFunctions;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_respository;

public class post_tc_1 {
	@Test
	
	
	
	public static void execute() throws IOException {
		for(int i=0 ; i<5 ; i ++) 
		{
			String baseURI=post_req_respository.base_URI();
			String requestBody=post_req_respository.post_request_1();
			String resource=post_req_respository.post_resource();
			int statusCode= API_Common_Fucntion.response_statusCode(baseURI, resource, requestBody);
			if(statusCode== 201) 
			{
			 String responseBody=API_Common_Fucntion.response_Body(baseURI, resource, requestBody);
			 System.out.println(responseBody);
         	post_tc_1.validator(responseBody, statusCode, requestBody);
         	utility_CommonFunctions.evidencecreater("post_tc_1", responseBody, responseBody);
         	break;
			}
			else 
			{
				System.out.println("correct status is not found hence retrying the API");
			}
			
			}
		 }
	
		public static void validator(String responsebody, int statusCode, String requestBody) {
			 
		// step 3 : parse the response Body
		
	
	JsonPath jsp = new JsonPath(responsebody);
	String res_name =jsp.getString("name");
	String res_job =jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=jsp.getString("createdAt");
	String trim_date = res_createdAt.substring(0,10);
	System.out.println(trim_date);
	//System.out.println(res_body);
	
	String expectedDate = LocalDateTime.now().toString().substring(0, 10);
	//System.out.println(expectedDate);
		
	// 	step 4 : validate the response body parameters 
	Assert.assertEquals(res_name, "morpheus");
	Assert.assertEquals(res_job, "leader");
	Assert.assertNotNull(res_id);
	Assert.assertEquals(trim_date, expectedDate);
	
	}

    }
 