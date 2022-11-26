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
public Report(String toString) {
	this.id= Integer.parseInt(toString.substring(toString.indexOf("id=")+3,toString.indexOf(", di")));
	this.direction=toString.substring(toString.indexOf("on=")+3,toString.indexOf(", re"));
//	this.reportDate=Date.parse(toString.substring(toString.indexOf("te="),toString.indexOf(", pat")));
	this.patientID= Integer.parseInt(toString.substring(toString.indexOf("ID=")+3,toString.indexOf("]")));

}


@Override
public String toString() {
	return "Report [id=" + id + ", direction=" + direction + ", reportDate=" + reportDate + ", patientID=" + patientID
			+ "]";
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

	
	
}
