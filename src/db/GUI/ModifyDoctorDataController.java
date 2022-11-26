package db.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	
	PrintWriter printWriter = null;
	InputStream inputStream = null;
	BufferedReader bufferedReader = null;
	OutputStream outputStream = null;

	@FXML
	void OnAcceptModifyCollegiate(ActionEvent event) {
		
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		
		String new_colnum = ModifyColNumTextField.getText();
		if (!new_colnum.equals("")) {
		
			dmodif.setCollegiate_number(new_colnum);
			OldColNum.setText(new_colnum);
			ModifyColNumTextField.setText("");

			printWriter.println("modifyDoctor");

			printWriter.println("collegiate_number");
			printWriter.println(new_colnum);

			printWriter.println(dmodif.toString());

			
			
			
			
			
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
		
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		
		String new_hospital = ModifyHospitalTextField.getText();
		if (!new_hospital.equals("")) {
		
			dmodif.setHospital(new_hospital);
			OldHospi.setText(new_hospital);
			ModifyColNumTextField.setText("");

			printWriter.println("modifyDoctor");

			printWriter.println("hospital");
			printWriter.println(new_hospital);

			printWriter.println(dmodif.toString());
		
		
		
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}
	}

	@FXML
	void OnAcceptModifyName(ActionEvent event) {
		
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		

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

			
				
				printWriter.println("modifyDoctor");

				printWriter.println("name");
				printWriter.println(new_name);

				printWriter.println(dmodif.toString());
			
			
			
			
			
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
		
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		

		if (!newpasstf.getText().equals("") && !oldpastf.getText().equals("")) {
			String newpass = newpasstf.getText();
			boolean catchdone = false;

			printWriter.println("modifyDPASSWORD");
			printWriter.println(newpass);
			printWriter.println(oldpastf.getText());
			printWriter.println(oldusername.getText());
			
			try {
				catchdone = Boolean.parseBoolean(bufferedReader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
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
		
		
		try {
			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
			inputStream = Main.getSocket().getInputStream();

			outputStream = Main.getSocket().getOutputStream();

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		
		String newUserName = modifyusername.getText();
		String oldUName = oldusername.getText();
		
		try {
	
		if (!newUserName.equals("")) {
			
			
			
			printWriter.println("checkEmail");
			printWriter.println(newUserName);

			if (Boolean.parseBoolean(bufferedReader.readLine())) {
				JOptionPane.showMessageDialog(null, "Email already used, try to log in");

			} else {	
				

				oldusername.setText(newUserName);

				printWriter.println("modifyDUSER");
				printWriter.println(newUserName);
				printWriter.println(oldUName);


				modifyusername.setText("");

				JOptionPane.showMessageDialog(null, "Username changed");
			}



		
		
		
		} else {
			JOptionPane.showMessageDialog(null, "Empty field");

		}

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
