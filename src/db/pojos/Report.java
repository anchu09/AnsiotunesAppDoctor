package db.pojos;

import java.sql.Date;

public class Report {
private int id;
private String direction;
private Date reportDate;
private int patientID;


public Report(int id, String direction, Date reportDate, int patientID) {
	super();
	this.id = id;
	this.direction = direction;
	this.reportDate = reportDate;
	this.patientID = patientID;
}



public Report(String direction, Date reportDate, int patientID) {
	super();
	this.id = id;
	this.direction = direction;
	this.reportDate = reportDate;
	this.patientID = patientID;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDirection() {
	return direction;
}
public void setDirection(String direction) {
	this.direction = direction;
}
public Date getReportDate() {
	return reportDate;
}
public void setReportDate(Date reportDate) {
	this.reportDate = reportDate;
}
public int getPatientID() {
	return patientID;
}
public void setPatientID(int patientID) {
	this.patientID = patientID;
}
@Override
public String toString() {
	return "reports [id=" + id + ", direction=" + direction + ", reportDate=" + reportDate + ", patientID=" + patientID
			+ ", getId()=" + getId() + ", getDirection()=" + getDirection() + ", getReportDate()=" + getReportDate()
			+ ", getPatientID()=" + getPatientID() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
			+ ", toString()=" + super.toString() + "]";
}
	
	
}
