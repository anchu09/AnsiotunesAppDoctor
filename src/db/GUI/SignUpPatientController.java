package db.GUI;

import java.security.MessageDigest;

import javax.swing.JOptionPane;

import db.pojos.Patient;
import db.pojos.users.Role;
import db.pojos.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpPatientController {
	User u;

	@FXML
	private TextField FavSongTextField;

	@FXML
	private TextField IDTextField;

	@FXML
	private TextField NameTextField;

	@FXML
	private PasswordField PassTextf;

	@FXML
	private PasswordField confirmpastx;

	@FXML
	private TextField emailPat;

	@FXML
	void OnAcceptPatClick(ActionEvent event) {
		try {
			if (!(emailPat.getText().equals("") || PassTextf.getText().equals("") || confirmpastx.getText().equals("")
					|| NameTextField.getText().equals("") || FavSongTextField.getText().equals("")
					|| FavSongTextField.getText().substring(0, 24).compareTo("https://www.youtube.com/") != 0
					|| IDTextField.getText().equals(""))) {

				if (PassTextf.getText().equals(confirmpastx.getText())) {
					Main.getUserman().connect();

					if (Main.getUserman().checkEmail(emailPat.getText())) {
						JOptionPane.showMessageDialog(null, "Email already used, try to log in");

					} else {
						try {
							Main.getInter().disconnect();
							Role role = Main.getUserman().getRole(2);
							MessageDigest md = MessageDigest.getInstance("MD5");
							md.update(PassTextf.getText().getBytes());
							byte[] hash = md.digest();
							u = new User(emailPat.getText(), hash, role);

							Main.getUserman().newUser(u);
							Main.getInter().connect();

							String name = NameTextField.getText();
							String song_text = FavSongTextField.getText();
							String ID = IDTextField.getText();

							Patient p_new = new Patient(name, ID, song_text, 0);
							Main.getInter().addPatientUser(p_new, u);

							JOptionPane.showMessageDialog(null, "Patient registered");

							Stage stage = (Stage) this.IDTextField.getScene().getWindow();
							stage.close();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Passwords are not equal");

				}

			} else {
				JOptionPane.showMessageDialog(null, "Empty fields");

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Put a valid youtube link");
		}

	}

	@FXML
	void OnCancelPatClick(ActionEvent event) {
		Stage stage = (Stage) this.IDTextField.getScene().getWindow();
		stage.close();

	}

}
