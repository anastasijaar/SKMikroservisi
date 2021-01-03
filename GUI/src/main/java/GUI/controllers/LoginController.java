package GUI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import GUI.client.MainViewManager;
import GUI.forms.Login_Form;
import GUI.services.GUIService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class LoginController {
	
	@Autowired
	MainViewManager mainViewManager;
	
	@Autowired
	GUIService guiService;
	
	@Autowired
	Login_Form login_form;
	
	@FXML private TextField emailTF;
	
	@FXML private TextField passwordTF;
	
	public LoginController(Login_Form login_Form) {
		this.login_form = login_Form;
	}
	
	public void proveraLogIn(ActionEvent event) {
		System.out.println(emailTF.getText());
		login_form.setEmail(emailTF.getText());
		login_form.setPassword(passwordTF.getText());
		System.out.println("Imejl je: "+login_form.getEmail()+", password je: "+ login_form.getPassword());
		//guiService.checkLogIn(login_form);
	}
	
	

}
