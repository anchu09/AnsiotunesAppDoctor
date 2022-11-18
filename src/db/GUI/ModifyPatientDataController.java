package db.GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import db.pojos.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyPatientDataController {
	Patient pmodif;

	public Patient getPmodif() {
		return pmodif;
	}

	public void setPmodif(Patient pmodif) {
		this.pmodif = pmodif;
	}

	@FXML
	private TextField ModifyNameTextField;

	@FXML
	private Label OldID;

	@FXML
	private Label OldName;

	@FXML
	private Label OldSong;

	@FXML
	private TextField idtextf;

	@FXML
	private TextField modifyusername;

	@FXML
	private PasswordField newpasstf;

	@FXML
	private PasswordField oldpastf;

	@FXML
	private Label oldusername;

	@FXML
	private TextField showNew;

	@FXML
	private TextField showold;

	@FXML
	private TextField songtf;

	@FXML
	void OnAcceptModifyName(ActionEvent event) {

		String new_name = ModifyNameTextField.getText();
		boolean correctData = true;
		if (!new_name.equals("")) {
			List<Integer> ascciNumsName = ConvertAscii(new_name);

			for (int i = 0; i < ascciNumsName.size(); i++) {
				if (!(ascciNumsName.get(i) >= 65 && ascciNumsName.get(i) <= 122)) {
					correctData = false;

				}

			}
			if (correctData == false) {
				JOptionPane.showMessageDialog(null, "Wrong name.");

			} else {
				OldName.setText(new_name);

				pmodif.setName(new_name);
				OldName.setText(new_name);
				ModifyNameTextField.setText("");
				Main.getInter().modifyPatient(this.pmodif.getId(), "name", new_name);

			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void OnAcceptSong(ActionEvent event) {
		String newLink = songtf.getText();
		if (!newLink.equals("")) {

			try {
				if (newLink.substring(0, 24).compareTo("https://www.youtube.com/") == 0) {
					pmodif.setFavSong(newLink);
					OldSong.setText(newLink);
					songtf.setText("");
					Main.getInter().modifyPatient(this.pmodif.getId(), "favourite_music", newLink);
				} else {
					JOptionPane.showMessageDialog(null, "Put a valid Youtube video");

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Put a valid Youtube video");

			}

		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}

	}

	@FXML
	void OnBackModifyData(ActionEvent event) {
		Stage stage = (Stage) this.ModifyNameTextField.getScene().getWindow();
		stage.close();
	}

	@FXML
	void modifypass(ActionEvent event) {

		if (!newpasstf.getText().equals("") && !oldpastf.getText().equals("")) {
			String newpass = newpasstf.getText();
			boolean catchdone = false;

			Main.getInter().disconnect();
			Main.getUserman().connect();
			catchdone = Main.getUserman().updateUserPassword(oldusername.getText(), newpass, oldpastf.getText(),
					catchdone);
			Main.getUserman().disconnect();
			Main.getInter().connect();
			if (catchdone == false) {
				JOptionPane.showMessageDialog(null, "Password changed");
			}
			newpasstf.setText("");
			oldpastf.setText("");

		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void modifyuser(ActionEvent event) {

		String newUserName = modifyusername.getText();
		String oldUName = oldusername.getText();
		
		try {
		Main.getInter().disconnect();
		Main.getUserman().connect();
		if (!newUserName.equals("")) {
			if (Main.getUserman().checkEmail(newUserName)) {
				JOptionPane.showMessageDialog(null, "Email already used, try to log in");

			} else {
			oldusername.setText(newUserName);

			
			Main.getUserman().updateUserMailWithoutpass(newUserName, oldUName);

			

			modifyusername.setText("");

			JOptionPane.showMessageDialog(null, "Username changed");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
		Main.getUserman().disconnect();
		Main.getInter().connect();
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Error, try again.");

	}
		
	}

	@FXML
	void onacceptID(ActionEvent event) {

		String newID = idtextf.getText();
		if (!newID.equals("")) {
			pmodif.setDNI(newID);
			OldID.setText(newID);
			idtextf.setText("");
			Main.getInter().modifyPatient(this.pmodif.getId(), "DNI", newID);

		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}

	}

	@FXML
	void visualizepassword7(MouseEvent event) {
		showold.setText(oldpastf.getText());
		showNew.setText(newpasstf.getText());
		showold.setVisible(true);
		showNew.setVisible(true);
	}

	@FXML
	void visualizepassword8(MouseEvent event) {
		showold.setVisible(false);
		showNew.setVisible(false);
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

	public void setOldUserName(String oldUserName) {
		oldusername.setText(oldUserName);
	}

	public void setoldName(String oldName) {
		OldName.setText(oldName);
	}

	public void setOldID(String oldID) {
		OldID.setText(oldID);
	}

	public void setOldSong(String oldSong) {
		OldSong.setText(oldSong);

	}

}
