package Client.utils;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import Client.forms.Avion_Form;
import Client.forms.Kartica_Form;
import Client.forms.Let_Form;
import Client.forms.SpisakAviona;
import Client.forms.SpisakLetova;
import Client.forms.UrediProfil_Form;

public class UtilsMethods {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private static HttpHeaders headers = new HttpHeaders();
	static {
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
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
	
	public static UrediProfil_Form sendGetUredi(String url)
			throws RestClientException {

		HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);

		ResponseEntity<UrediProfil_Form> response = restTemplate.exchange(url, HttpMethod.GET, entity, UrediProfil_Form.class);

		return response.getBody();
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
	
	public static List<Let_Form> sendGetListLet(String url) {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<SpisakLetova> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpisakLetova.class);

		return response.getBody().getSpisakLetova();
	}
	
	public static List<Avion_Form> sendGetListAvion(String url) {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<SpisakAviona> response = restTemplate.exchange(url, HttpMethod.GET, entity, SpisakAviona.class);

		return response.getBody().getSpisakAviona();
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
	
	public static Kartica_Form sendPostObject(String url, Object body) throws RestClientException{

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		
		ResponseEntity<Kartica_Form> response = restTemplate.exchange(url, HttpMethod.POST, entity, Kartica_Form.class);
	
		return response.getBody();
	}
	
	public static Let_Form sendPostLet(String url, Object body) throws RestClientException{

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		
		ResponseEntity<Let_Form> response = restTemplate.exchange(url, HttpMethod.POST, entity, Let_Form.class);
	
		return response.getBody();
	}
	
	public static Let_Form sendPostBrisanjeLeta(String url, Object body) throws RestClientException{

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		
		ResponseEntity<Let_Form> response = restTemplate.exchange(url, HttpMethod.POST, entity, Let_Form.class);
	
		return response.getBody();
	}
	
	
	public static UrediProfil_Form sendPutString(String url, Object body)
			throws RestClientException {

		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<UrediProfil_Form> response = restTemplate.exchange(url, HttpMethod.PUT, entity, UrediProfil_Form.class);

		return response.getBody();
	}
	
	
	public static void setToken(String token) {
		headers.add("Authorization", token);
	}
}
