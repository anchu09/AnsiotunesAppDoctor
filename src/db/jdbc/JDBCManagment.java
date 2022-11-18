package db.jdbc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.text.html.ImageView;

import db.GUI.Main;
import db.interfaces.Ans_manager;
import db.pojos.*;
import db.pojos.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBCManagment implements Ans_manager {

	private Connection c;
	private static boolean showImage = true;

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/ansiotunes.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			this.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}

	@Override
	public void connectWithNoPrint() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./database/ansiotunes.database");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			this.creatTables();
		} catch (SQLException E) {
			System.out.println("There was a database exception.");
			E.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}

	}

	@Override
	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("There was a problem while closing the database connection.");
			e.printStackTrace();
		}
	}

	@Override
	public void creatTables() {
		try {
			int a;
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE doctors " + "(id       			INTEGER  	 	PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 	NOT NULL,"

					+ " collegiate_number	TEXT	 	 	NOT NULL,"
					+ " hospital  			TEXT		 	NOT NULL,"
					+ " user_id				INTEGER			REFERENCES users(id))";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE patients " + "(id       			INTEGER  	 PRIMARY KEY AUTOINCREMENT,"
					+ " name     			TEXT    	 NOT NULL," + " DNI   	TEXT  	 	 NOT NULL,"
					+ " score 				float   	 NOT NULL,"
					+ " favourite_music 				TEXT   	 NOT NULL,"

					+ " user_id			INTEGER			REFERENCES users(id))";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE reports " + "(id       		INTEGER  	PRIMARY KEY AUTOINCREMENT,"
					+ " link  		TEXT	 	NOT NULL," + " date			DATE		NOT NULL,"
					+ " id_pat			INTEGER		REFERENCES patients(id))";
			stmt3.executeUpdate(sql3);
			stmt3.close();

		} catch (Exception e) {
			if (!e.getMessage().contains("already exist")) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addPatient(Patient p) {
		try {
			String sql = "INSERT INTO patients (name, DNI, score, favourite_music)" + "VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getID());
			prep.setFloat(3, p.getScore());
			prep.setString(4, p.getFavSong());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addReport(Patient p, Report r) {
		try {
			String sql = "INSERT INTO reports (link, date, id_pat)" + "VALUES (?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, r.getDirection());
			prep.setDate(2, r.getReportDate());
			prep.setFloat(3, r.getPatientID());

			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPatientUser(Patient p, User u) {
		try {

			String sql = "INSERT INTO patients (name, DNI, score, favourite_music,user_id)" + "VALUES (?, ?, ?, ?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getID());
			prep.setFloat(3, p.getScore());
			prep.setString(4, p.getFavSong());
			prep.setInt(5, u.getId());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addDoctor(Doctor d) {
		try {

			String sql = "INSERT INTO doctors (name, collegiate_number, hospital) VALUES (?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(4, d.getCollegiate_number());
			prep.setString(6, d.getHospital());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addDoctorUser(Doctor d, User u) {
		try {

			String sql = "INSERT INTO doctors (name,  collegiate_number,  hospital, user_id) VALUES (?, ?, ?, ?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getCollegiate_number());
			prep.setString(3, d.getHospital());
			prep.setInt(4, u.getId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyDoctor(int iden, String atrib, String value) {
		try {
			String sql;
			Date fecha = null;
			if (atrib.equalsIgnoreCase("birth_date")) {
				fecha = Date.valueOf(value);
				sql = "UPDATE doctors SET " + atrib + " = ? WHERE id = " + iden;
			} else {
				sql = "UPDATE doctors SET " + atrib + " = ? WHERE id = " + iden;
			}
			PreparedStatement prep = c.prepareStatement(sql);
			if (atrib.equalsIgnoreCase("birth_date")) {
				prep.setDate(1, fecha);
			} else {
				prep.setString(1, value);
			}
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void modifyPatient(int iden, String atrib, String value) {
		try {
			String sql;

			sql = "UPDATE patients SET " + atrib + " = ? WHERE id = " + iden;

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setString(1, value);

			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Doctor getDoctor(int id) {
		try {
			String sql = "SELECT * FROM doctors WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String doctorName = rs.getString("name");
				String numCol = rs.getString("collegiate_number");
				String hos = rs.getString("hospital");

				return new Doctor(id, doctorName, numCol, hos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Doctor getLastDoctor() {
		try {
			String sql = "SELECT * FROM doctors WHERE id = (SELECT MAX(id) FROM doctors)";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String doctorName = rs.getString("name");
				String numCol = rs.getString("collegiate_number");
				String hos = rs.getString("hospital");

				return new Doctor(id, doctorName, numCol, hos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Doctor getDoctorbyUser(User u) {
		try {
			String sql = "SELECT * FROM doctors WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, u.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");

				String doctorName = rs.getString("name");
				String numCol = rs.getString("collegiate_number");
				String hos = rs.getString("hospital");

				return new Doctor(id, doctorName, numCol, hos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserMailbydoctor(Doctor d) {
		try {
			String sql = "SELECT u.EMAIL FROM users AS u JOIN doctors AS d ON u.id= d.user_id WHERE d.id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, d.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("EMAIL");

				return mail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserMailbyPat(Patient l) {
		try {
			String sql = "SELECT u.EMAIL FROM users AS u JOIN patients AS p ON u.id= p.user_id WHERE p.id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, l.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("EMAIL");

				return mail;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient getPat(int id) {
		try {
			String sql = "SELECT * FROM patients WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				String patName = rs.getString("name");
				String IDreal = rs.getString("adress");
				String fav_song = rs.getString("cif");

				return new Patient(id, patName, IDreal, fav_song);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Patient getPatByUser(User u) {
		try {
			String sql = "SELECT * FROM patients WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, u.getId());
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String pat_name = rs.getString("name");
				String dni = rs.getString("DNI");
				float score = rs.getFloat("score");
				String favourite_music = rs.getString("favourite_music");

				return new Patient(id, pat_name, dni, favourite_music, score);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Patient getPatByUserID(int userId) {
		try {
			String sql = "SELECT * FROM patients WHERE user_id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, userId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String pat_name = rs.getString("name");
				String dni = rs.getString("DNI");
				float score = rs.getFloat("score");
				String favourite_music = rs.getString("favourite_music");

				return new Patient(id, pat_name, dni, favourite_music, score);
			}
			prep.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> docs = new ArrayList<Doctor>();
		try {

			String sql = "SELECT * FROM doctors WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%' + "" + '%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");

				String doctorName = rs.getString("name");
				String numCol = rs.getString("collegiate_number");
				String hos = rs.getString("hospital");

				Doctor doc = new Doctor(id, doctorName, numCol, hos);
				docs.add(doc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	@Override
	public ObservableList getAllReportsbyPatient(Patient p) {
			System.out.println("el paciente es "+ p.toString());
		ObservableList links = FXCollections.observableArrayList();
		try {

			String sql = "SELECT * FROM reports WHERE link LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%' + p.getName() + "_" + p.getID() + '%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");

				String link = rs.getString("link");
				System.out.println("cadalink es "+link);
				links.add(link);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return links;
	}

	@Override
	public boolean checkDoctor(Doctor doc) {
		List<Doctor> docs = getAllDoctors();
		Doctor tmp = null;
		try {
			for (int i = 0; i <= docs.size(); i++) {
				tmp = docs.get(i);
				if (tmp.getName().equalsIgnoreCase(doc.getName())
						&& tmp.getCollegiate_number().equalsIgnoreCase(doc.getCollegiate_number())
						&& tmp.getHospital().equalsIgnoreCase(doc.getHospital())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Patient> getAllPatientsTableDoctors() {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM  patients WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, '%' + "" + '%');
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				String id_real = rs.getString("DNI");
				float score = rs.getFloat("score");

				Patient patient = new Patient(id, patientname, id_real, score);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;

	}

	@Override
	public List<Patient> searchPatientGeneric(String feature, String type) {

		List<Patient> patients = new ArrayList<Patient>();

		try {
			if (feature == "ID") {
				feature = "DNI";
			}

			String sql;
			sql = "SELECT * FROM  patients WHERE " + feature + " LIKE ?";

			PreparedStatement prep = c.prepareStatement(sql);

			prep.setString(1, "%" + type + "%");

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String patientname = rs.getString("name");
				String id_real = rs.getString("DNI");
				float score = rs.getFloat("score");

				Patient patient = new Patient(id, patientname, id_real, score);
				patients.add(patient);
			}
			rs.close();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;

	}
	@Override
	public void deletePersonByUserId(List<Integer> lista) {
		

		String sql ="";
		if(lista.get(1)==1) {
		 sql = "DELETE FROM doctors WHERE user_id = ?";
		}else {
			 sql = "DELETE FROM patients WHERE user_id = ?";

		}
		PreparedStatement prep;
		try {
			
			if(lista.get(1)==2) {
				ObservableList<String> listaArchivos = Main.getInter().getAllReportsbyPatient(Main.getInter().getPatByUserID(lista.get(0)));
				System.out.println("holaaaaaaa"+listaArchivos.size());
				System.out.println("userid"+lista.get(0));

				for(int i=0; i<listaArchivos.size();i++){
					Main.getInter().deleteReportbyLink(listaArchivos.get(i));
					System.out.println("el fichero a borrar es "+listaArchivos.get(i));
					File fichero = new File(listaArchivos.get(i));
					
					fichero.delete();
				}
				
			}
			prep = c.prepareStatement(sql);
			prep.setInt(1, lista.get(0));
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void deleteReportbyLink(String link) {
		String sql = "DELETE FROM reports WHERE link = ?";
		PreparedStatement prep;
		try {
			prep = c.prepareStatement(sql);
			prep.setString(1, link);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
