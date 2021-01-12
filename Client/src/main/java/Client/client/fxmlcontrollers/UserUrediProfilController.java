package Client.client.fxmlcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.forms.Kartica_Form;
import Client.forms.RegistrationForm;
import Client.forms.UrediProfil_Form;
import Client.utils.UtilsMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Controller
public class UserUrediProfilController {
	
	///Uredjivanje profila
	@FXML private TextField nameTf;
	
	@FXML private TextField lastNameTf;
	
	@FXML private TextField emailTf;
	
	@FXML private TextField passwordTf;
	
	@FXML private TextField brPasosaTf;
	
	@FXML
	Label actionTarget;
	
	///Dodavanje kartice
	
	@FXML private TextField brKarticeTf;
	
	@FXML private TextField sigurnosniBrTf;

	@FXML
	protected void initialize() {
		
		String url = "http://localhost:8762/rest-service-1/getUser";

		UrediProfil_Form response = UtilsMethods.sendGetUredi(url);
		
		nameTf.setText(response.getIme());
		lastNameTf.setText(response.getPrezime());
		emailTf.setText(response.getEmail());
		passwordTf.setText(response.getPassword());
		brPasosaTf.setText(response.getBrojPasosa());
	}

	public void handleChangeUserCredentials(ActionEvent event) {
		String url = "http://localhost:8762/rest-service-1/urediProfil";
		
		String name = nameTf.getText();
		String lastName = lastNameTf.getText();
		String email = emailTf.getText();
		String password = passwordTf.getText();
		String brPasosa = brPasosaTf.getText();
		
		RegistrationForm body = new RegistrationForm(name, lastName, email, password, brPasosa);
		
		UrediProfil_Form response = UtilsMethods.sendPutString(url, body);
		
		actionTarget.setText("Profil je promenjen!");
	}
	
	public void handleAddCard(ActionEvent event) {
		String url = "http://localhost:8762/rest-service-1/getUser";

		UrediProfil_Form response = UtilsMethods.sendGetUredi(url);
		
		String addUrl = "http://localhost:8762/rest-service-1/dodelaKreditneKartice";
		
		String brKartice = brKarticeTf.getText();
		String sigurnosniBr = sigurnosniBrTf.getText();
		
		Kartica_Form body = new Kartica_Form(response.getIme(), response.getPrezime(), brKartice, sigurnosniBr);
		
		UtilsMethods.sendPostObject(addUrl, body);
		
		actionTarget.setText("Kartica je uspesno dodata!");
	}
}
