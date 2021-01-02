package app.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UtilsMethods {
	
	
	public static ResponseEntity<String> sendPost(String url, Object body) {
		System.out.println("Udje u post");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return response;
	}
	
	
	public static ResponseEntity<String> sendGet(String url) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		return response;
	}
	
	public static ResponseEntity<String> sendGet(String url, HttpHeaders token) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = token;

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		return response;
	}

}
