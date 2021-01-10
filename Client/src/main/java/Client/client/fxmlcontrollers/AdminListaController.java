package Client.client.fxmlcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import Client.entities.Letovi;
import Client.utils.UtilsMethods;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

@Controller
public class AdminListaController {

	@FXML private TableView<Letovi> adminListaLetovaTable;
	
	private ObservableList<Letovi> sviLetovi;
	
	@FXML
	protected void initialize() {
		
		String url = "http://localhost:8762/rest-service-2/spisakLetova";

		ResponseEntity<Object> response = UtilsMethods.sendGetObject(url);
		
		System.out.println("Respons je: "+response);
		
	}
	
}
