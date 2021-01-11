package Client.client.fxmlcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.forms.RegistrationForm;
import Client.utils.UtilsMethods;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class UserUrediProfilController {
	
	@FXML TextField nameTf;
	
	@FXML TextField lastNameTf;
	
	@FXML TextField emailTf;
	
	@FXML TextField passwordTf;
	
	@FXML TextField brPasosaTf;
	
	@FXML
	Label actionTarget;
	
	@FXML
	protected void initialize() {
		
		String url = "http://localhost:8762/rest-service-1/urediProfil";
		
		String name = nameTf.getText();
		String lastName = lastNameTf.getText();
		String email = emailTf.getText();
		String password = passwordTf.getText();
		String brPasosa = brPasosaTf.getText();
		
		RegistrationForm body = new RegistrationForm(name, lastName, email, password, brPasosa);
		
		ResponseEntity<String> response = UtilsMethods.sendPutString(url, body);
		
	}

}
