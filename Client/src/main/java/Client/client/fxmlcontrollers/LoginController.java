package Client.client.fxmlcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.client.MainViewManager;
import Client.forms.Login_Form;
import Client.utils.UtilsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class LoginController {

	@FXML
	TextField emailTf;

	@FXML
	TextField passwordTf;

	@FXML
	Label actionTarget;
	
	@Autowired
	MainViewManager mainViewManager;

	public void handleLogIn(ActionEvent event) {
		try {

			if (emailTf.getText().equals("") || passwordTf.getText().equals("")) {
				actionTarget.setText("Svi podaci moraju biti popunjeni !");
			}

			String url = "http://localhost:8762/rest-service-1/login";

			Login_Form loginForm = new Login_Form();
			loginForm.setPassword(passwordTf.getText());
			loginForm.setEmail(emailTf.getText());
			
			ResponseEntity<String> response = UtilsMethods.sendPostString(url, loginForm);
			
			String token = response.getHeaders().get("Authorization").get(0);
			
			if(token.startsWith("Admin ")) {
				System.out.println("Admin je");
				mainViewManager.changeRoot("adminListaLetova");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
