package db.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import db.pojos.Patient;

public class ConsultDataController {

	Patient pCurrent;

	public Patient getpCurrent() {
		return pCurrent;
	}

	public void setpCurrent(Patient pCurrent) {
		this.pCurrent = pCurrent;
	}

	ObservableList<String> listaArchivos = FXCollections.observableArrayList();

	String actualLink;

	public ObservableList<String> getListaArchivos() {
		return listaArchivos;
	}

	public void setListaArchivos(ObservableList<String> listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	@FXML
	private ChoiceBox<String> choiceBoxReports;

	public ChoiceBox<String> getlistBox() {
		return choiceBoxReports;
	}

	@FXML
	private Button backfromstatistics;

	@FXML
	private Button usarPantallaBut;

	@FXML
	private CategoryAxis daysAxis;

	@FXML
	private LineChart<?, ?> lineChart;

	@FXML
	private NumberAxis numAxis;

	@FXML
	private Button ecgbut;

	@FXML
	private Button edabut;
	XYChart.Series series;
	@FXML
	private Label reportsLabel;

	@FXML
	private Label scoreLabel;

	public Label getScoreLabel() {
		return scoreLabel;
	}
	
	
	 PrintWriter printWriter =null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		OutputStream outputStream = null;

	public void setScoreLabel(String content) {
		scoreLabel.setText(content);
	}

	@FXML
	void ECGButton(ActionEvent event) {
		
		
		
		
		try {
	    	printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
	    	inputStream = Main.getSocket().getInputStream();
//			objectInputStream = new ObjectInputStream(inputStream);

			outputStream = Main.getSocket().getOutputStream();
//			objectOutputStream = new ObjectOutputStream(outputStream);


			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		

		// plotTittle.setText("ECG for " + choiceBoxReports.getValue());
		lineChart.setTitle("ECG for " + choiceBoxReports.getValue());
//		ArrayList<Integer> datos = leerfichero("../AnsiotunesAppDoctor/reports/" + choiceBoxReports.getValue(), "ecg");
		
		printWriter.println("leerfichero");
		printWriter.println("../AnsioTunesServer/reports/" + choiceBoxReports.getValue());
		printWriter.println("ecg");
		int len =0;
		ArrayList<Integer> datos = new ArrayList<Integer>();

		try {
			 len = 		Integer.parseInt(bufferedReader.readLine());
			 
			 for (int i=0;i<len;i++) {
			
			datos.add(Integer.parseInt(bufferedReader.readLine()));

		}
			 
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		series = new XYChart.Series();
		series.getData().clear();
		lineChart.getData().clear();

//		 int tam= datos.size();
		int tam = 300;

		for (int i = 0; i < tam; i++) {
			series.getData().add(new XYChart.Data("" + i, datos.get(i)));

		}

		lineChart.getData().addAll(series);
	}

	@FXML
	void EDAButton(ActionEvent event) {

		
		try {
	    	printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);
	    	inputStream = Main.getSocket().getInputStream();
//			objectInputStream = new ObjectInputStream(inputStream);

			outputStream = Main.getSocket().getOutputStream();
//			objectOutputStream = new ObjectOutputStream(outputStream);


			printWriter = new PrintWriter(Main.getSocket().getOutputStream(), true);

			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
		
		
		lineChart.setTitle("EDA for " + choiceBoxReports.getValue());

		// plotTittle.setText("EDA for " + choiceBoxReports.getValue());
		
		
		
		
//		ArrayList<Integer> datos = leerfichero("../AnsiotunesAppDoctor/reports/" + choiceBoxReports.getValue(), "eda");
		

		printWriter.println("leerfichero");
		printWriter.println("../AnsioTunesServer/reports/" + choiceBoxReports.getValue());
		printWriter.println("eda");
		int len =0;
		ArrayList<Integer> datos = new ArrayList<Integer>();

		try {
			 len = 		Integer.parseInt(bufferedReader.readLine());
			 
			 for (int i=0;i<len;i++) {
			
			datos.add(Integer.parseInt(bufferedReader.readLine()));

		}
			 
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
		series = new XYChart.Series();
		lineChart.getData().clear();
		series.getData().clear();
//		 int tam= datos.size();
		int tam = 300;
		for (int i = 0; i < tam; i++) {
			series.getData().add(new XYChart.Data("" + i, datos.get(i)));

		}

		lineChart.getData().addAll(series);

	}


	@FXML
	void backfromstatistics(ActionEvent event) {
		Stage stage = (Stage) this.scoreLabel.getScene().getWindow();
		stage.close();
	}

	@FXML
	void usarPantalla(ActionEvent event) {

		loadData();
		choiceBoxReports.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
					edabut.setDisable(false);
					ecgbut.setDisable(false);

				});

		choiceBoxReports.setVisible(true);

		reportsLabel.setVisible(true);

		scoreLabel.setVisible(true);

		edabut.setVisible(true);

		ecgbut.setVisible(true);

		lineChart.setVisible(true);

		usarPantallaBut.setVisible(false);
	}

	private void loadData() {
		choiceBoxReports.getItems().clear();

		for (int i = 0; i < listaArchivos.size(); i++) {
			choiceBoxReports.getItems().add(listaArchivos.get(i).toString().substring(28));
		}

	}


}