package Client.client.fxmlcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.entities.Letovi;
import Client.forms.Login_Form;
import Client.utils.UtilsMethods;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

@Controller
public class AdminListaController {

	@FXML TableView<Letovi> listaLetovi;
	
	private ObservableList<Letovi> sviLetovi;
	
	@FXML
	protected void initialize() {
		
		String url = "http://localhost:8762/rest-service-2/spisakLetova";
		String token = "";
		
		ResponseEntity<Object> response = UtilsMethods.sendGetObject(url, token);
		System.out.println(response);
		
	}
	
}
