package Client.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Client.client.MainViewManager;
import Client.forms.Avion_Form;
import Client.forms.Let_Form;
import Client.utils.UtilsMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

@Controller
public class AdminListaController {
	
	@Autowired
	MainViewManager mainViewManager;

	///Letovi
	@FXML private TableView<Let_Form> adminListaLetovaTable;
	
	private ObservableList<Let_Form> sviLetovi;
	
	///Avioni
	
	@FXML private TableView<Avion_Form> listaAvionaTV;
	
	private ObservableList<Avion_Form> sviAvioni;
	
	private Let_Form selektvonaLet;
	
	@FXML
	protected void initialize() {
		///Letovi
		String url = "http://localhost:8762/rest-service-2/spisakLetova";
		
		List<Let_Form> response = UtilsMethods.sendGetListLet(url);
		sviLetovi = FXCollections.observableArrayList(response);
		
		adminListaLetovaTable.setItems(sviLetovi);
		
		///Avioni
		String urlAvioni = "http://localhost:8762/rest-service-2/spisakAviona";
		
		List<Avion_Form> responseAvion = UtilsMethods.sendGetListAvion(urlAvioni);
		
		sviAvioni = FXCollections.observableArrayList(responseAvion);
		listaAvionaTV.setItems(sviAvioni);
	}
	
	public void addLetFlight(ActionEvent event) {
		mainViewManager.openModal("addLet");
	}
	public Let_Form getSelectedFlight() {
		Let_Form let = adminListaLetovaTable.getSelectionModel().getSelectedItem();
		return let;
	}
	
}
