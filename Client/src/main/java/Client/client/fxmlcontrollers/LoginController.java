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
		ResponseEntity<String> response = null;
		try {
			if (emailTf.getText().equals("") || passwordTf.getText().equals("")) {
				actionTarget.setText("Svi podaci moraju biti popunjeni !");
			}

			String url = "http://localhost:8762/rest-service-1/login";

			Login_Form loginForm = new Login_Form(emailTf.getText(), passwordTf.getText());
			
			response = UtilsMethods.sendPostString(url, loginForm);
			
			String token = response.getHeaders().get("Authorization").get(0);
			
			if(token.startsWith("Basic ")) {
				System.out.println("Prefiks je: ");
				mainViewManager.openModal("adminListaLetova");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


/*
 <VBox prefHeight="200.0" prefWidth="100.0" BorderPane.alignment="CENTER">
          <padding>
            <Insets bottom="10.0" left="10.0" right="10.0" top="10.0"/>
        </padding>
         <children>
            <TableView fx:id="listaLetovi">
              <columns>
                  <TableColumn prefWidth="100.0" text="Pocetna Destinacija">
                 <cellValueFactory><PropertyValueFactory property="pocetnaDestinacija" />
                </cellValueFactory>
                </TableColumn>
                <TableColumn prefWidth="100.0" text="Krajnja Destinacija">
                 <cellValueFactory><PropertyValueFactory property="krajnjaDestinacija" />
                </cellValueFactory>
                </TableColumn>
                <TableColumn prefWidth="150.0" text="Duzina Leta">
                 <cellValueFactory><PropertyValueFactory property="duzinaLeta" />
                </cellValueFactory>
                </TableColumn>
                <TableColumn prefWidth="150.0" text="Cena">
                 <cellValueFactory><PropertyValueFactory property="cena" />
                </cellValueFactory>
                </TableColumn>
                <TableColumn prefWidth="100.0" text="Avion">
                 <cellValueFactory><PropertyValueFactory property="avion" />
                </cellValueFactory>
                </TableColumn>
              </columns>
            </TableView>
         </children>
      </VBox>*/
