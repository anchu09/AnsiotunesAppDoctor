package db.GUI;

import java.io.IOException;

import db.pojos.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatMenuController {
	Patient p = null;

	public Patient getP() {
		return p;
	}

	public void setP(Patient p) {
		this.p = p;
	}

	@FXML
	private Label PatName;

	public void setPatName(String patName) {
		PatName.setText(patName);
	}

	@FXML
	void Modify_Patient(ActionEvent event) {
		String name = "ModifyPatientDataView.fxml";
		ModifyPatientDataController controller = null;
		try {
			Pane root0 = (Pane) this.PatName.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();
			controller = loader.getController();
			controller.setOldUserName(Main.getInter().getUserMailbyPat(p));
			controller.setoldName(p.getName());
			controller.setOldID(p.getID());
			controller.setOldSong(p.getFavSong());
			controller.setPmodif(p);

			controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Modify patient");
			stage.setResizable(false);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

			stage.showAndWait();

			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void outfromPat(ActionEvent event) {
		Stage stage = (Stage) this.PatName.getScene().getWindow();
		stage.close();

	}

	@FXML
	void startMeasuringData(ActionEvent event) {
		String name = "StartMeasurementsView.fxml";
		StartMeasurementsController controller = null;
		String title = "Record measurements";
		try {
			Pane root0 = (Pane) this.PatName.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			controller.setPcurrent(p);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle(title);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
