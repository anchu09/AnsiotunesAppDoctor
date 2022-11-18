
package db.GUI;

import java.io.IOException;

import db.pojos.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DoctorMenuController {
	Doctor d;

	public Doctor getD() {
		return d;
	}

	public void setD(Doctor d) {
		this.d = d;
	}

	@FXML
	public Label DoctorName;

	public Label getDoctorName() {
		return DoctorName;
	}

	public void setDoctorName(String doctorName) {
		DoctorName.setText(doctorName);
	}

	@FXML
	private Button SearchModify_Patient;

	@FXML
	private Button ModifyData;

	@FXML
	void OnModifyData(ActionEvent event) {
		String name = "ModifyDoctorDataView.fxml";
		ModifyDoctorDataController controller = null;
		try {
			Pane root0 = (Pane) this.ModifyData.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			controller.setDmodif(d);
			controller.setOldName(d.getName());
			controller.setOldColNum(d.getCollegiate_number());
			controller.setOldHospi(d.getHospital());
			controller.setOldusername(Main.getInter().getUserMailbydoctor(d));
			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("Modify doctor");

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

		this.DoctorName.setText(Main.getInter().getDoctor(d.getId()).getName());
	}

	@FXML
	void OnSearchPatient(ActionEvent event) {
		String name = "SearchPatientView.fxml";
		SearchPatientController controller = null;
		String title = "Search patient";

		try {
			Pane root0 = (Pane) this.DoctorName.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));
			controller.setD(d);
			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnSignOutClick(ActionEvent event) {
		Stage stage = (Stage) this.DoctorName.getScene().getWindow();
		stage.close();
	}

	void openWindow(String name, Object controller, String title) {
		try {
			Pane root0 = (Pane) this.DoctorName.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
			Parent root;

			root = loader.load();

			controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle(title);
			stage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

			stage.showAndWait();
			root0.setEffect(null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Button getSearchModify_Patient() {
		return SearchModify_Patient;
	}

	public Button getModifyData() {
		return ModifyData;
	}

	public void setDoctorName(Label doctorName) {
		DoctorName = doctorName;
	}

	public void setSearchModify_Patient(Button searchModify_Patient) {
		SearchModify_Patient = searchModify_Patient;
	}

	public void setModifyData(Button modifyData) {
		ModifyData = modifyData;
	}

}
