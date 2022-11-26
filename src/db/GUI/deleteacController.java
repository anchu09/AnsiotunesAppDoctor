package db.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class deleteacController {

	@FXML
	private TextField modifyusername;

	@FXML
	private PasswordField oldpastf;

	@FXML
	private PasswordField newpasstf;

	@FXML
	private TextField showOld;

	@FXML
	private TextField showNew;

	@FXML
	void backfrommodify(ActionEvent event) {
		Stage stage = (Stage) this.showNew.getScene().getWindow();
		stage.close();
	}
	
	PrintWriter printWriter = null;
	InputStream inputStream = null;
	BufferedReader bufferedReader = null;
	OutputStream outputStream = null;

	@FXML
	void delete(ActionEvent event) {
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		String uText = modifyusername.getText();
		String password = oldpastf.getText();
		
		printWriter.println("checkPassword");
		printWriter.println(uText);
		printWriter.println(password);
		
		String mail = null;
		String rolename = null;
		String idText = null;
		Integer id = null;
		User u = null;
		try {

			rolename = bufferedReader.readLine();
			Role r = new Role(rolename);

			String userText = bufferedReader.readLine();
			System.out.println(userText);
			u = new User(userText, r);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NumberFormatException ex2) {
			// TODO Auto-generated catch block

			u = null;
		}
		
		
		
		
		
		
		if(u==null ||u.getRole().getName().equals("patient")) {
			JOptionPane.showMessageDialog(null, "Wrong user name or password");
			return;
		}

		if (uText.equals(" ") || password.equals(" ")||uText.equals("")||password.equals("")) {
			JOptionPane.showMessageDialog(null, "Empty fields");
			return;
		}

		
		if (!oldpastf.getText().equals("") && !newpasstf.getText().equals("") && !modifyusername.getText().equals("")) {
			if (oldpastf.getText().equals(newpasstf.getText())) {

				printWriter.println("delete");
				printWriter.println(uText);
				printWriter.println(password);
				Stage stage = (Stage) this.showNew.getScene().getWindow();
				stage.close();

			} else {
				JOptionPane.showMessageDialog(null, "Passwords are not equal");

			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}

	}

	@FXML
	void visualizepassword7(MouseEvent event) {
		showOld.setText(oldpastf.getText());
		showNew.setText(newpasstf.getText());
		showOld.setVisible(true);
		showNew.setVisible(true);
	}

	@FXML
	void visualizepassword8(MouseEvent event) {
		showOld.setVisible(false);
		showNew.setVisible(false);
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