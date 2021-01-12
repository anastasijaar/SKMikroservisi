package Client.client.fxmlcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import Client.forms.Avion_Form;
import Client.forms.Let_Form;
import Client.forms.SpisakAviona;
import Client.utils.UtilsMethods;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Controller
public class AddLetController {

	@FXML private TextField pocDestinacijaTf;
	@FXML private TextField krajDestinacijaTf;
	@FXML private TextField duzLetaTf;
	@FXML private TextField cenaTf;
	@FXML private ComboBox<Avion_Form> avionCb;
	
	@FXML
	protected void initialize() {
		
		String urlAvioni = "http://localhost:8762/rest-service-2/spisakAviona";
		
		List<Avion_Form> sviAvioni = UtilsMethods.sendGetListAvion(urlAvioni);
		
		avionCb.setItems(FXCollections.observableArrayList(sviAvioni));
	}
	
	public void addLet(ActionEvent event) {
		Let_Form lf = new Let_Form(pocDestinacijaTf.getText(), krajDestinacijaTf.getText(), Integer.parseInt(duzLetaTf.getText()), Integer.parseInt(cenaTf.getText()), false);
		Avion_Form idAviona = avionCb.getValue();
		lf.setIdAviona(idAviona.getIdAviona());
		
		String urlSaveLet = "http://localhost:8762/rest-service-2/dodajLet";
		
		UtilsMethods.sendPostLet(urlSaveLet, lf);
	}
}
