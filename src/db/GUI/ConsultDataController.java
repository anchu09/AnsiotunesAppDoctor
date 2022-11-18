package db.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	public void setScoreLabel(String content) {
		scoreLabel.setText(content);
	}

	@FXML
	void ECGButton(ActionEvent event) {

		// plotTittle.setText("ECG for " + choiceBoxReports.getValue());
		lineChart.setTitle("ECG for " + choiceBoxReports.getValue());
		ArrayList<Integer> datos = leerfichero("../Ansiotunes/reports/" + choiceBoxReports.getValue(), "ecg");
		series = new XYChart.Series();
		series.getData().clear();
		lineChart.getData().clear();

		// int tam= datos.size();
		int tam = 1000;

		for (int i = 0; i < tam; i++) {
			series.getData().add(new XYChart.Data("" + i, datos.get(i)));

		}

		lineChart.getData().addAll(series);
	}

	@FXML
	void EDAButton(ActionEvent event) {

		lineChart.setTitle("EDA for " + choiceBoxReports.getValue());

		// plotTittle.setText("EDA for " + choiceBoxReports.getValue());
		
		ArrayList<Integer> datos = leerfichero("../Ansiotunes/reports/" + choiceBoxReports.getValue(), "eda");
		series = new XYChart.Series();
		lineChart.getData().clear();
		series.getData().clear();
		// int tam= datos.size();
		int tam = 1000;
		for (int i = 0; i < tam; i++) {
			series.getData().add(new XYChart.Data("" + i, datos.get(i)));

		}

		lineChart.getData().addAll(series);

	}

	public static ArrayList<Integer> leerfichero(String ruta, String tipo) {
		ArrayList<Integer> datos = new ArrayList<Integer>();
		BufferedReader br = null;
		try {

			FileReader fr = new FileReader(ruta);
			br = new BufferedReader(fr);

			String leido = "";
			int contador = 0;
			while (leido != null) {

				leido = br.readLine();
				if (leido.length() > 5) {
					if (tipo.compareTo("ecg") == 0) {

						String ecg = leido.substring(saberespacio(leido, 3) + 1, saberespacio(leido, 4) - 1);

						datos.add(Integer.parseInt(ecg));
					} else {

						String eda = leido.substring(saberespacio(leido, 4) + 1, saberespacio(leido, 5) - 1);

						datos.add(Integer.parseInt(eda));

					}
				}

			}

			System.out.println("he salido");

		} catch (FileNotFoundException ex1) {
			System.out.println("archivo no encontrado");
		} catch (IOException ex1) {
			System.out.println("error");
		} catch (NullPointerException ex1) {
			System.out.println("");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}

		return datos;

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
			choiceBoxReports.getItems().add(listaArchivos.get(i).toString().substring(22));
		}

	}

	public static int saberespacio(String cadena, int numeroespacio) {
		int contadorespacios = 0;
		int posicion = 0;
		boolean pasonombre = false;

		for (int i = 0; i < cadena.length(); i++) {

			if (cadena.charAt(i) == ' ') {
				contadorespacios++;

			}
			if (contadorespacios == numeroespacio) {
				break;
			}

			posicion++;
		}

		return posicion;
	}

}