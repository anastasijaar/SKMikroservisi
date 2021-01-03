package GUI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import GUI.forms.Login_Form;
import GUI.utils.UtilsMethods;

@Service
public class GUIService {
	
	@Autowired
	UtilsMethods utilsMethods;
	
	public boolean checkLogIn(Login_Form login_form) {
		
		//ResponseEntity<String> response = utilsMethods.sendGet("http://localhost:8762/actuator/routes");
		//System.out.println(response);
		return true;
	}

}
