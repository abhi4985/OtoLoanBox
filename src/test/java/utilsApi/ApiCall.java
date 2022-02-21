package utilsApi;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiCall {
	
	public static Response postApiMethod(Map<String, Object> bodyParams,String apiPath){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.headers(ApiPaths.getHeaders())
				.body(bodyParams)
				.when().post(apiPath);

		return response;

	}
	public static Response postApiMethod(String requestBody,String apiPath){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.headers(ApiPaths.getHeaders())
				.body(requestBody)
				.when().post(apiPath);

		return response;

	}
	public static Response postApiWithHeader(Map<String, Object> bodyParams,String apiPath,String token){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given().log().all()
				.headers(ApiPaths.getHeaders())
				.headers("Authorization",token)
				.body(bodyParams)
				.when().post(apiPath);

		return response;

	}
/*	public static Response postApiWithHeader(String bodyParams,String apiPath,String token){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given().log().all()
				.headers(ApiPaths.getHeaders())
				.headers("Authorization",token)
				.body(bodyParams)
				.when().post(apiPath);

		return response;

	}*/
	public static Response postApiWithHeader(String requestBody,String apiPath,String token){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given().log().all()
				.headers(ApiPaths.getHeaders())
				.headers("Authorization",token)
				.body(requestBody)
				.when().post(apiPath);

		return response;

	}
	public static Response postApiWithBaseUrl(String requestBody,String baseUrl){

		RestAssured.baseURI=ApiPaths.sfa_base_path;
		Response response=RestAssured
				.given().log().all()
				.contentType(ContentType.JSON)
				//.headers(ApiPaths.getHeaders())
				.headers("source",ApiPaths.source_sfa)
				.headers("key",ApiPaths.key_sfa)
				.body(requestBody)
				.when().post(baseUrl);

		return response;

	}
	//get http method
	public static Response getApiMethod(String requestBody,String apiPath){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.queryParam(requestBody)
				.headers(ApiPaths.getHeaders())
				.when().get(apiPath);

		return response;

	}
	public static Response getApiMethod(String requestBody,String apiPath,String token){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.queryParam(requestBody)
				.headers(ApiPaths.getHeaders())
				.headers("Authorization",token)
				.when().get(apiPath);

		return response;

	}

}
