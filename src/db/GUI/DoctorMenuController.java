
package db.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	
	PrintWriter printWriter = null;
	InputStream inputStream = null;
	BufferedReader bufferedReader = null;
	OutputStream outputStream = null;

	@FXML
	void OnModifyData(ActionEvent event) {
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		
		
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
			
			
			
			printWriter.println("setOlduserDoc");
			printWriter.println(d.toString());
								
			
			String username= bufferedReader.readLine();
			
			controller.setOldusername(username);			
			
			
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
	
	
	
	private static void releaseResourcesClient(PrintWriter printWriter, BufferedReader bufferedReader,
			OutputStream outputStream, InputStream inputStream ) {
		try {
			printWriter.close();
		} catch (Exception ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			bufferedReader.close();
		} catch (Exception ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			outputStream.close();
		} catch (IOException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			inputStream.close();
		} catch (IOException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
