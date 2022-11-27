package db.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Main extends Application {

	 static Socket socket= null;

	
	/* POSIBLE ERROR :
	 
	 Error: Could not find or load main class db.GUI.Main
	Caused by: java.lang.NoClassDefFoundError: javafx/application/Application
	
	SE SOLUCIONA CON: CLICK DERECHO EN MAIN - RUN AS - RUN CONFIGURATIONS - ARGUMENTS
	PEGAMOS: --module-path ".\JavaFX\lib" --add-modules javafx.controls,javafx.fxml
	EN VM MODULE (RECUADRO GRANDE DE ABAJO)
	APPLY AND CLOSE
	
	
	*/
		public static Socket getSocket() {

			return socket;
	
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void start(Stage primaryStage) {
		try {
			 socket = new Socket("localHost",9000);


			Pane root = (Pane) FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setIconified(false);

			primaryStage.setScene(scene);

			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

			primaryStage.setTitle("Ansiotunes DOCTOR");

			primaryStage.show();
		} catch (Exception e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Server inactive.");
System.exit(0);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

