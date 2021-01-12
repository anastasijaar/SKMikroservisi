package Client.client.fxmlcontrollers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Client.forms.Let_Form;
import Client.utils.UtilsMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

@Controller
public class AdminListaController {

	@FXML private TableView<Let_Form> adminListaLetovaTable;
	
	private ObservableList<Let_Form> sviLetovi;
	
	@FXML
	protected void initialize() {
		System.out.println("Udje u initialize");
		
		ObjectMapper objectMaper = new ObjectMapper();
		String url = "http://localhost:8762/rest-service-2/spisakLetova";
		//ResponseEntity<String> response = UtilsMethods.sendGetList(url);
		
		List<Let_Form> response = UtilsMethods.sendGetList(url);
		sviLetovi = FXCollections.observableArrayList(response);
		adminListaLetovaTable.setItems(sviLetovi);
		
		System.out.println("Nesto");
		System.out.println("Response je: "+response);
		
	}
}
