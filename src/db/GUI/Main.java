package db.GUI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import db.interfaces.Ans_manager;
import db.interfaces.UserManager;
import db.jdbc.JDBCManagment;
import db.jpa.JPAUserManagment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Main extends Application {
	private static UserManager userman = new JPAUserManagment();
	private static Connection c;

	private static Ans_manager inter = new JDBCManagment();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	/* POSIBLE ERROR :
	 
	 Error: Could not find or load main class db.GUI.Main
	Caused by: java.lang.NoClassDefFoundError: javafx/application/Application
	
	SE SOLUCIONA CON: CLICK DERECHO EN MAIN - RUN AS - RUN CONFIGURATIONS - ARGUMENTS
	PEGAMOS: --module-path ".\JavaFX\lib" --add-modules javafx.controls,javafx.fxml
	EN VM MODULE (RECUADRO GRANDE DE ABAJO)
	APPLY AND CLOSE
	
	
	*/
	
	public static void connect() {
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/ansiotunes.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			inter.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}

	public static UserManager getUserman() {
		return userman;
	}

	public static void setUserman(UserManager userman) {
		Main.userman = userman;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// getUserman().connect();

			getInter().connect();

			Pane root = (Pane) FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setIconified(false);

			primaryStage.setScene(scene);

			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrdhgX8t_xOqHLNFJTcdUaEMCyc5-NwN7dhQ&usqp=CAU"));

			primaryStage.setTitle("Ansiotunes");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Ans_manager getInter() {
		return inter;
	}

	public static void setInter(Ans_manager inter) {
		Main.inter = inter;
	}
}
