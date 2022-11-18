package db.GUI;

import javafx.fxml.FXML;
import db.pojos.Patient;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScoreControllerController {
	@FXML
	private Label scoreLabel;
	Patient p=null;
	
	

	public Patient getP() {
		return p;
	}



	public void setP(Patient p) {
		this.p = p;
	}



	public Label getScoreLabel() {
		return scoreLabel;
	}



	public void setScoreLabel(String string) {
		scoreLabel.setText(string);
	}



	// Event Listener on Button.onAction
	@FXML
	public void goBack(ActionEvent event) {
		
		Stage stage= (Stage)scoreLabel.getScene().getWindow();
		stage.close();

	}
	
	
	
	
}
