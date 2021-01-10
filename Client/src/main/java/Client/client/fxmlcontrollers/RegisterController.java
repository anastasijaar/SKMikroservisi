package Client.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.forms.RegistrationForm;
import Client.utils.UtilsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Controller
public class RegisterController {
	
	@FXML TextField nameTf;
	
	@FXML TextField lastNameTf;
	
	@FXML TextField emailTf;
	
	@FXML TextField passwordTf;
	
	@FXML TextField brPasosaTf;
	
	
	public void handleRegistration(ActionEvent event) {
		
		RegistrationForm regstrationForm = new RegistrationForm(nameTf.getText(), lastNameTf.getText(), emailTf.getText(), passwordTf.getText(), 0, brPasosaTf.getText());
		
		String url = "http://localhost:8762/rest-service-1/register";
		try {
			ResponseEntity<String> response = UtilsMethods.sendPostString(url, regstrationForm);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
				
	}

}
