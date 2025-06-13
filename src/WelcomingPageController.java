import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WelcomingPageController {
	@FXML
	private Button startBtn;

	// Event Listener on Button[#startBtn].onAction
	@FXML
	public void start(ActionEvent event) {
		loadChildFXML();
	}

	// Event Listener on Button[#startBtn].onMouseEntered
	@FXML
	public void entered(MouseEvent event) {
		double red = 183.0 / 255.0;
		double green = 178.0 / 255.0;
		double blue = 178.0 / 255.0;
		startBtn.setTextFill(new Color(red, green, blue, 1.0));
	}

	// Event Listener on Button[#startBtn].onMouseExited
	@FXML
	public void exited(MouseEvent event) {
		double red = 142.0 / 255.0;
		double green = 140.0 / 255.0;
		double blue = 112.0 / 255.0;
		startBtn.setTextFill(new Color(red, green, blue, 1.0));
	}
	private void loadChildFXML() {
	    try {
	        Stage stage = new Stage();
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("ThirdProj.fxml"));
	        Parent thirdproj = loader.load();
	        ThirdProjController cont = loader.getController(); // Move this line after loader.load()
	        
	        Scene scene = new Scene(thirdproj, Color.TRANSPARENT);
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
