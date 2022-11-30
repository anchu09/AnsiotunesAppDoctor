package db.GUI;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import java.util.logging.Level;
import java.util.logging.Logger;
import db.pojos.Doctor;
import db.pojos.users.Role;
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

	PrintWriter printWriter = null;
	InputStream inputStream = null;
	BufferedReader bufferedReader = null;
	OutputStream outputStream = null;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.labelDate.setText("" + Date.valueOf(LocalDate.now()));
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

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

			Desktop.getDesktop().browse(new URI("https://github.com/manolin20/AnsiotunesAppDoctor"));

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
		releaseResourcesClient(printWriter, bufferedReader, outputStream, inputStream, Main.getSocket());
		System.exit(0);
	}

	@FXML
	void OnEnterUser(ActionEvent event) {
		
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		String user = UserTextField.getText();
		String password = PasswordTextField.getText();

		
		
		printWriter.println("checkPassword");
		printWriter.println(user);

		printWriter.println(password);
		
		
		
		String mail = null;
		String rolename = null;
		String idText = null;
		Integer id = null;
		User u = null;
		String control=null;

		try {
			control = bufferedReader.readLine();
if(control.contains("null")) {
	System.out.println("es null");
}else {
	rolename = bufferedReader.readLine();
	Role r = new Role(rolename);

	String userText = bufferedReader.readLine();
	System.out.println(userText);
	u = new User(userText, r);
}
		

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException ex2) {
			// TODO Auto-generated catch block

			u = null;
		}
		
		
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

				
				
				
				
				
				
				Parent root;

				root = loader.load();

				controller = loader.getController();
				
				

				try {

					printWriter.println("getDoctorbyUser");
					printWriter.println(u.getRole().getName());
					printWriter.println(u.toString());


					
					
					
					String dString = bufferedReader.readLine();
			

					Doctor d = new Doctor(dString);

					controller.setD(d);

					controller.setDoctorName(d.getName());
				} catch (NullPointerException ex) {
					System.out.println("holaaa");
				}
				
				
				
				
				
			

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
	
	private static void releaseResourcesClient(PrintWriter printWriter, BufferedReader bufferedReader,
			OutputStream outputStream, InputStream inputStream, Socket socket) {
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

		try {
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
