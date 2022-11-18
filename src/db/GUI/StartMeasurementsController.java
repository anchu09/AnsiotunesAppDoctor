package db.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;
import javax.swing.JOptionPane;
import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.Frame;
import db.pojos.Patient;
import db.pojos.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class StartMeasurementsController {
	public static Frame[] frame;
	Patient pcurrent = null;
	public static boolean dataExtraction;
	

	@FXML
	private TextField macTF;

	public Patient getPcurrent() {
		return pcurrent;
	}

	public void setPcurrent(Patient pcurrent) {
		this.pcurrent = pcurrent;
	}

	@FXML
	private Button buttonStartMeasurement;

	@FXML
	void OnCancelPatient(ActionEvent event) {
		Stage stage = (Stage) this.macTF.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onStartMeasurement(ActionEvent event) {
		
		dataExtraction=false;
		buttonStartMeasurement.setVisible(false);

		if (!(macTF.getText().compareTo("") == 0)) {
			

			RunBita(pcurrent, macTF.getText());
			
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Put a correct MAC adress.");

		}
		buttonStartMeasurement.setVisible(true);
		JOptionPane.showMessageDialog(null, "Recording finished.");
		
		
		if(dataExtraction) {
		try {
			Pane root0 = (Pane) this.buttonStartMeasurement.getScene().getRoot();

			ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

			GaussianBlur blur = new GaussianBlur(10);
			adj.setInput(blur);
			root0.setEffect(adj);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreController.fxml"));
			Parent root;

			root = loader.load();

			ScoreControllerController controller = loader.getController();
			
			String text=Float.toString(pcurrent.getScore());
		
			controller.setScoreLabel(text);
		
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setResizable(false);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Showing score");
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

	public static void RunBita(Patient pcurrent, String mac) {
		
		String s1 = "";
		String s2 = "";

		BITalino bitalino = null;
		try {
			bitalino = new BITalino();
			// Code to find Devices
			// Only works on some OS
			Vector<RemoteDevice> devices = bitalino.findDevices();
			
			// You need TO CHANGE THE MAC ADDRESS
			// You should have the MAC ADDRESS in a sticker in the Bitalino

			String macAddress = mac;
			

			// String macAddress = "98:D3:31:FD:3B:92";
			 //String macAddress = "98:D3:41:FD:4E:E8";
		

			// Sampling rate, should be 10, 100 or 1000
			int SamplingRate = 100;
			bitalino.open(macAddress, SamplingRate);

			// Start acquisition on analog channels A2 and A6
			// For example, If you want A1, A3 and A4 you should use {0,2,3}
			int[] channelsToAcquire = { 1, 2 };
			bitalino.start(channelsToAcquire);

			// Read in total 10000000 times
			
			//1.5 minutes no music, 1.5 minutes music=3minutes total
			//f=100==> 0.01seconds per sample
			//block_size=100==> each j=1s. ==> j=180==> 3minutes
			int length = 180;
			for (int j = 0; j < length; j++) {
				
				if (j == length / 2) {
					Desktop.getDesktop().browse(new URI(pcurrent.getFavSong()));

				}

				int block_size = 100;
				frame = bitalino.read(block_size);

				// s1+="size block: " + frame.length+"\n";
				s1 += "";
				for (int i = 0; i < frame.length; i++) {

					s2 += (j * block_size + i) + " seq: " + frame[i].seq + " " + frame[i].analog[0] + " "
							+ frame[i].analog[1] + " " + "\n";

				}
				s1 = s1 + s2;

			}

			// System.out.println(s1);

			// crear un archivo// ojo archivos con el mismo nombre se sobreescriben
			
			int numAleatorio = (int) (Math.random() * 100000);
		

			File f = new File("../Ansiotunes/reports/" + pcurrent.getName() + "_" + pcurrent.getID() + "_"
					+ numAleatorio + "_" + LocalDate.now() + ".txt");
			
			Report r = new Report("../Ansiotunes/reports/" + pcurrent.getName() + "_" + pcurrent.getID() + "_"
					+ numAleatorio + "_" + LocalDate.now() + ".txt", Date.valueOf(LocalDate.now()), pcurrent.getId());
			
			//File f= new File("../Ansiotunes2910v2/reports/"+pcurrent.getName()+"_"+pcurrent.getID()+"_"+LocalDate.now()+".txt");
	

			Main.getInter().addReport(pcurrent, r);
			
			//ArrayList<Integer>eda=ConsultDataController.leerfichero("../Ansiotunes/reports/" + pcurrent.getName() + "_" + pcurrent.getID() + "_"
				//	+ numAleatorio + "_" + LocalDate.now() + ".txt", "eda");
		
	
			

			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// imprimir s1 en el archivo
			PrintWriter p = new PrintWriter(f);
			p.println(s1);
			p.close();

			// guardar, cerrar el archivo ?????

			// stop acquisition
			bitalino.stop();
			
			
			//ArrayList<Integer>eda=ConsultDataController.leerfichero("../Ansiotunes2910v2/reports/"+pcurrent.getName()+"_"+pcurrent.getID()+"_"+LocalDate.now()+".txt", "eda");
			ArrayList<Integer> eda=null;
			try {
				eda = signalProc.readData.readEda("../Ansiotunes/reports/" + pcurrent.getName() + "_" + pcurrent.getID() + "_"
						+ numAleatorio + "_" + LocalDate.now() + ".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			int score=signalProc.dataAnalysis.score(eda);
		
		
			
			Main.getInter().modifyPatient(pcurrent.getId(), "score", Integer.toString(score));
			
			
			dataExtraction=true;
			
		} catch (BITalinoException ex) {
			JOptionPane.showMessageDialog(null, "Connection with bitalino failed. Please try again.");
		} catch (Throwable ex) {
			JOptionPane.showMessageDialog(null, "Connection with bitalino failed. Please try again.");
		} finally {
			try {
				// close bluetooth connection
				if (bitalino != null) {
					bitalino.close();
				}
			} catch (BITalinoException ex) {
				JOptionPane.showMessageDialog(null, "Connection with bitalino failed. Please try again.");
			}
		}
		
		
	}

}
