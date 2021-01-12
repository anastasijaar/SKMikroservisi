package Client.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import Client.forms.Let_Form;
import Client.forms.SpisakLetova;

public class UtilsMethods {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private static HttpHeaders headers = new HttpHeaders();
	static {
		headers.add("Content-Type", "application/json");
	}
	
	public static ResponseEntity<String> sendGet(String url) {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		return response;
	}
	
	
	public static ResponseEntity<Object> sendGetObject(String url) {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);

		return response;
	}
	
	/*public static ResponseEntity<String> sendGetList(String url) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<List<Letovi>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Letovi>>() {
		});

		return (ResponseEntity<List<Letovi>>) response.getBody();
		
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<String>() {});
		
		return res;
	}*/
	
	public static List<Let_Form> sendGetList(String url) {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<SpisakLetova> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpisakLetova.class);

		return response.getBody().getSpisakLetova();
	}


	public static ResponseEntity<Integer> sendPostInteger(String url, Object body) {

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.POST, entity, Integer.class);

		return response;
	}
	
	public static ResponseEntity<String> sendPostString(String url, Object body) throws RestClientException{

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		return response;
	}
	
	
	public static ResponseEntity<String> sendPutString(String url, Object body)
			throws RestClientException {

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return response;
	}
	
	
	public static void setToken(String token) {
		headers.add("Authorization", token);
	}
}
