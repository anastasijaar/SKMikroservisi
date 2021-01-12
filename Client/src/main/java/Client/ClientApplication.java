package Client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import Client.client.MainViewManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

@SpringBootApplication
public class ClientApplication extends Application{
	
	protected ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		launch(ClientApplication.class);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(ClientApplication.class);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("LogIn");
    	MainViewManager mainView = springContext.getBean(MainViewManager.class);    	
    	primaryStage.setScene(mainView.createScene());
    	primaryStage.show();
	}

	@Override
	public void stop() {
		springContext.close();
		Platform.exit();
	}
}
