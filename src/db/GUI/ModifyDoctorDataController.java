package db.GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.pojos.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyDoctorDataController {

	Doctor dmodif;

	public void setDmodif(Doctor dmodif) {
		this.dmodif = dmodif;
	}

	@FXML
	private TextField ModifyColNumTextField;

	@FXML
	private TextField ModifyHospitalTextField;

	@FXML
	private TextField ModifyNameTextField;

	@FXML
	private Label OldColNum;

	@FXML
	private Label OldHospi;

	@FXML
	private Label OldName;

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
	void OnAcceptModifyCollegiate(ActionEvent event) {
		String new_colnum = ModifyColNumTextField.getText();
		if (!new_colnum.equals("")) {
			dmodif.setCollegiate_number(new_colnum);
			OldColNum.setText(new_colnum);
			ModifyColNumTextField.setText("");
			Main.getInter().modifyDoctor(this.dmodif.getId(), "collegiate_number", new_colnum);

		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
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
	void OnAcceptModifyHospital(ActionEvent event) {
		String new_hospital = ModifyHospitalTextField.getText();
		if (!new_hospital.equals("")) {
			dmodif.setCollegiate_number(new_hospital);
			OldHospi.setText(new_hospital);
			ModifyHospitalTextField.setText("");
			Main.getInter().modifyDoctor(this.dmodif.getId(), "hospital", new_hospital);
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

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

				dmodif.setName(new_name);
				OldName.setText(new_name);
				
				ModifyNameTextField.setText("");
				Main.getInter().modifyDoctor(this.dmodif.getId(), "name", new_name);

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

	public Label getOldusername() {
		return oldusername;
	}

	public void setOldusername(String oldusername) {
		this.oldusername.setText(oldusername);
	}

	public void setOldName(String nametext) {
		OldName.setText(nametext);
	}

	public void setOldColNum(String oldColNum) {
		OldColNum.setText(oldColNum);
	}

	public void setOldHospi(String oldHospi) {
		OldHospi.setText(oldHospi);
	}

}
