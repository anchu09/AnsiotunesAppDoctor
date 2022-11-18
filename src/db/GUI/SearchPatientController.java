package db.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import db.pojos.Doctor;

import db.pojos.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SearchPatientController implements Initializable {
	ObservableList list = FXCollections.observableArrayList();
	@FXML
	private Label numberofpatients;
	Doctor d;
	@FXML
	private ChoiceBox<String> SearchOptions;
	@FXML
	private TextField typeTextfield;
	@FXML
	private TableView<Patient> tablePatients;
	@FXML
	private TableColumn colName;

	private boolean firsttime = true;

	@FXML
	private Button modifybutton;
	@FXML
	private Label searchbylabel;

	Patient pselected;

	@FXML
	private Button usetable;

	@FXML
	private TableColumn colID;

	@FXML
	private TableColumn colScore;

	@FXML
	private Button SearchButton;

	private ObservableList<Patient> patientsTableList;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	void BackFromModify(ActionEvent event) {
		Stage stage = (Stage) this.SearchOptions.getScene().getWindow();
		stage.close();
	}

	@FXML
	void usethetable(ActionEvent event) {
		this.tablePatients.setDisable(false);
		this.modifybutton.setDisable(false);
		this.modifybutton.setVisible(true);
		this.usetable.setDisable(true);
		this.usetable.setVisible(false);
		this.SearchOptions.setDisable(false);
		this.typeTextfield.setDisable(false);
		this.SearchButton.setDisable(false);
		this.searchbylabel.setDisable(false);

		SearchOptions.setValue("Select an option");

		patientsTableList = FXCollections.observableArrayList();
		this.colName.setCellValueFactory(new PropertyValueFactory("name"));
		this.colID.setCellValueFactory(new PropertyValueFactory("ID"));
		this.colScore.setCellValueFactory(new PropertyValueFactory("score"));

		List<Patient> allpatients = Main.getInter().getAllPatientsTableDoctors();
		this.patientsTableList.addAll(allpatients);
		this.tablePatients.setItems(patientsTableList);
	}

	@FXML
	void ModifySearch(ActionEvent event) {

		String feature = SearchOptions.getValue();
		String type = typeTextfield.getText();
		if (!type.equals("") && !feature.equals("Select an option")) {

			this.patientsTableList.clear();

			List<Patient> result = Main.getInter().searchPatientGeneric(feature, type);
			this.patientsTableList.addAll(result);
			this.tablePatients.setItems(patientsTableList);
			typeTextfield.setText("");

		} else {
			JOptionPane.showMessageDialog(null, "Empty field ");
		}

	}

	@FXML
	void ModifyPatient(ActionEvent event) {
		if (pselected == null) {
			JOptionPane.showMessageDialog(null, "Please, select the patient that you want to consult");

		} else {
			String name = "ConsultDataView.fxml";
			ConsultDataController controller = null;
			try {
				Pane root0 = (Pane) this.numberofpatients.getScene().getRoot();

				ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);

				GaussianBlur blur = new GaussianBlur(10);
				adj.setInput(blur);
				root0.setEffect(adj);
				FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
				Parent root;

				root = loader.load();
				controller = loader.getController();

				controller.setpCurrent(pselected);

				controller.getlistBox().setValue("Choose report");
				controller.setListaArchivos(Main.getInter().getAllReportsbyPatient(pselected));
				controller.setScoreLabel("Score: " + pselected.getScore());
				Scene scene = new Scene(root);
				Stage stage = new Stage();

				stage.setResizable(false);

				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.setTitle("Modify patient");
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		loadData();
		SearchOptions.setValue("Select an option");

	}

	public Doctor getD() {
		return d;
	}

	public void setD(Doctor d) {
		this.d = d;
	}

	private void loadData() {
		list.removeAll(list);
		String a = "Name";
		String b = "DNI";
		String c = "Score";

		list.addAll(a, b, c);
		SearchOptions.getItems().addAll(list);

	}

	@FXML
	void selectRow(MouseEvent event) {
		this.pselected = this.tablePatients.getSelectionModel().getSelectedItem();

	}

}
