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
import javafx.scene.control.TableView;
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
		ResponseEntity<String> response = null;
		try {
			if (emailTf.getText().equals("") || passwordTf.getText().equals("")) {
				actionTarget.setText("Svi podaci moraju biti popunjeni !");
			}

			String url = "http://localhost:8762/rest-service-1/login";

			Login_Form loginForm = new Login_Form(emailTf.getText(), passwordTf.getText());
			
			System.out.println("Login forma je: " + loginForm.getEmail() + " " + loginForm.getPassword());
			
			response = UtilsMethods.sendPostString(url, loginForm);
			
			String token = response.getHeaders().get("Authorization").get(0);
			
			UtilsMethods.setToken(token);
			
			if(token.startsWith("Admin ")) {
				mainViewManager.changeRoot("admin");
			}
			else if(token.startsWith("Basic ")) {
				mainViewManager.changeRoot("userUrediProfil");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void handleRegister(ActionEvent actionEvent) {
		
		mainViewManager.openModal("registration");
		
	}

}