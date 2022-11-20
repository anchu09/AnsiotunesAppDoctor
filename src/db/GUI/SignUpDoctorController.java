package db.GUI;

import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.pojos.*;
import db.pojos.users.Role;
import db.pojos.users.User;

public class SignUpDoctorController implements Initializable {

	@FXML
	private TextField ColNumTextField;

	@FXML
	private TextField EmaDcotorText;

	@FXML
	private Label EmailLabel;

	@FXML
	private TextField HospitalTextField;

	@FXML
	private TextField NameTextField;

	@FXML
	private PasswordField RepeatPassword;

	@FXML
	private Label collabel;

	@FXML
	private Button createUserbutton;

	@FXML
	private Label hoslabel;

	@FXML
	private Label namelabel;

	@FXML
	private PasswordField pasDoctorTxtF;

	@FXML
	private Label passwordlabel;

	@FXML
	private Label repeatLabel;

	User u;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void OnAcceptClick(ActionEvent event) {

		String name = NameTextField.getText();

		String colnum = ColNumTextField.getText();
		String hospital = HospitalTextField.getText();

		int id = 0;
		byte[] image = null;
		Doctor d_new = new Doctor(name, colnum, hospital);
		Main.getInter().addDoctorUser(d_new, u);

		Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();
	}

	public boolean validateName(String name) {
		boolean correctData = true;
		List<Integer> ascciNumsName = ConvertAscii(NameTextField.getText());

		for (int i = 0; i < ascciNumsName.size(); i++) {
			if (!(ascciNumsName.get(i) >= 65 && ascciNumsName.get(i) <= 122)) {
				correctData = false;
				if (ascciNumsName.get(i) == 32) {
					correctData = true;

				}

			}

		}
		if (correctData == false) {
			JOptionPane.showMessageDialog(null, "Wrong name.");

		}
		return correctData;
	}

	private List<Integer> ConvertAscii(String StringtoConvert) {
		List<Integer> ascciNumsName = new ArrayList<>();

		try {
			for (int i = 0; i < StringtoConvert.length(); i++) {
				char chartoconvert = StringtoConvert.charAt(i);
				int asciinum = (int) chartoconvert;
				ascciNumsName.add(asciinum);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Wrong data, put it again.");

		}

		return ascciNumsName;

	}

	@FXML
	void oncreateuser(ActionEvent event) {
		String doctoremail = EmaDcotorText.getText();
		String doctorPassword = pasDoctorTxtF.getText();
		
		if (!doctoremail.equals("") && !doctorPassword.equals("") && !RepeatPassword.getText().equals("")
				&& !NameTextField.getText().equals("") && !ColNumTextField.getText().equals("")
				&& !HospitalTextField.getText().equals("")) {
			boolean correctData = validateName(NameTextField.getText());

			if (doctorPassword.equals(RepeatPassword.getText()) && correctData == true) {

				Main.getUserman().connect();

				if (Main.getUserman().checkEmail(doctoremail)) {
					JOptionPane.showMessageDialog(null, "Email already used, try to log in");

				} else {
					try {
						Main.getInter().disconnect();
						Role role = Main.getUserman().getRole(1);

						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(doctorPassword.getBytes());
						byte[] hash = md.digest();
						User u = new User(doctoremail, hash, role);

						Main.getUserman().newUser(u);
						Main.getInter().connect();

						String name = NameTextField.getText();
						String colnum = ColNumTextField.getText();
						String hospital = HospitalTextField.getText();

						int id = 0;
						Doctor d_new = new Doctor(name, colnum, hospital);
						Main.getInter().addDoctorUser(d_new, u);
						Doctor dlast = Main.getInter().getLastDoctor();

						JOptionPane.showMessageDialog(null, "Doctor registered. ");
						Stage stage = (Stage) this.NameTextField.getScene().getWindow();
						stage.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} else {
				if (correctData == true) {
					JOptionPane.showMessageDialog(null, "Passwords are not equal");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty fields");

		}
	}

	@FXML
	void OnCancelClick(ActionEvent event) {
		Stage stage = (Stage) this.NameTextField.getScene().getWindow();
		stage.close();
	}

}
