package db.interfaces;


import java.util.*;

import db.pojos.*;
import db.pojos.users.User;
import javafx.collections.ObservableList;

public interface Ans_manager {
	// Connects with the database and, if needed, performs necessary setup
		public void connect();
		public void connectWithNoPrint();
		// Closes the connection with the database
		public void disconnect();
		public void creatTables();
	void addReport(Patient p, Report r);

	void addPatientUser(Patient p, User u);

	void addDoctor(Doctor d);

	void addDoctorUser(Doctor d, User u);

	void modifyDoctor(int iden, String atrib, String value);

	void modifyPatient(int iden, String atrib, String value);

	Doctor getDoctor(int id);

	Doctor getLastDoctor();

	Doctor getDoctorbyUser(User u);

	String getUserMailbydoctor(Doctor d);

	String getUserMailbyPat(Patient l);

	Patient getPat(int id);

	Patient getPatByUser(User u);

	List<Doctor> getAllDoctors();

	ObservableList getAllReportsbyPatient(Patient p);

	boolean checkDoctor(Doctor doc);

	List<Patient> getAllPatientsTableDoctors();

	void addPatient(Patient p);
	List<Patient> searchPatientGeneric(String feature, String type);
	void deletePersonByUserId(List<Integer> lista);
	Patient getPatByUserID(int userId);
	void deleteReportbyLink(String link);




}
