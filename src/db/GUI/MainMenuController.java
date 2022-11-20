package db.GUI;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import db.pojos.users.User;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
	@FXML
	private PasswordField PasswordTextField;

	@FXML
	private TextField UserTextField;
	@FXML
	private Label labelDate;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.labelDate.setText("" + Date.valueOf(LocalDate.now()));

	}

	@FXML
	void OnSignUpClick(ActionEvent event) {

		String name = "SignUpDoctorView.fxml";
		SignUpDoctorController controller = null;
		openWindow(name, controller, "Choose user");

	}

	@FXML
	void opengit(MouseEvent event) {
		try {

			Desktop.getDesktop().browse(new URI("https://github.com/anchu09/Ansiotunes"));

		} catch (URISyntaxException ex) {
			JOptionPane.showMessageDialog(null, "Error.");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error.");

		}
	}

	@FXML
	void onClose(ActionEvent event) {
		Stage stage = (Stage) this.PasswordTextField.getScene().getWindow();

		stage.close();

		Main.getInter().disconnect();
		System.exit(0);
	}

	@FXML
	void OnEnterUser(ActionEvent event) {
		Main.getUserman().connect();
		String user = UserTextField.getText();
		String password = PasswordTextField.getText();
		User u = Main.getUserman().checkPassword(user, password);

		if (user.equals(" ") || password.equals(" ")||user.equals("")||password.equals("")) {
			JOptionPane.showMessageDialog(null, "Empty fields");
			return;
		}

		else if (u == null) {
			JOptionPane.showMessageDialog(null, "Wrong user name or password.");

			return;

		} else if (u.getRole().getName().equalsIgnoreCase("doctor")) {
			String name = "DoctorMenuView.fxml";
			DoctorMenuController controller = null;
			try {
				Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

				ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				GaussianBlur blur = new GaussianBlur(10);
				adj.setInput(blur);
				root0.setEffect(adj);

				FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
				Doctor d = Main.getInter().getDoctorbyUser(u);

				Parent root;

				root = loader.load();

				controller = loader.getController();
				controller.setD(d);

				controller.setDoctorName(d.getName());

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setTitle("Doctor menu");
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setResizable(false);
				// stage.setTitle(name);
				stage.getIcons().add(new Image(
						"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

				stage.showAndWait();
				root0.setEffect(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (u.getRole().getName().equalsIgnoreCase("patient")) {

			// Doctor can't login as a patient, but we should not tell the doctor that this
			// password and user exist, perhaps
			// is not the doctor account as a patient, but another patient account->
			// privacy.
			JOptionPane.showMessageDialog(null, "Wrong user name or password.");

		}
	}

	void openWindow(String name, Object controller, String title) {
		try {

			Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

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

	@FXML
	void onDeleteAccountPress(ActionEvent event) {

		String name = "deleteacView.fxml";
		deleteacController controller = null;
		try {

			Pane root0 = (Pane) this.PasswordTextField.getScene().getRoot();

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
			stage.setTitle("Ansiotunes");

			stage.setTitle("Delete account");
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
