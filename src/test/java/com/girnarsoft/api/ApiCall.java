package com.girnarsoft.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiCall {
	
	public static Response postApiMethod(String requestBody,String apiPath){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.headers(ApiPaths.getHeaders())
				.body(requestBody)
				.when().post(apiPath);

		return response;

	}
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
	public static Response getApiMethod(String requestBody,String apiPath){

		RestAssured.baseURI=ApiPaths.base_path;
		Response response=RestAssured
				.given()
				.queryParam(requestBody)
				.headers(ApiPaths.getHeaders())
				.when().get(apiPath);

		return response;

	}

}
