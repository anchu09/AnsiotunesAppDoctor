package db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



public class Patient implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String ID;
	private String favSong;
	private float score;
	private ArrayList<Doctor> doctors;
	
	
	
	
	

	public Patient(int id, String name, String ID, String favSong, float score) {
		super();
		this.id = id;
		this.name = name;
		this.ID = ID;
		this.favSong = favSong;
		this.score = score;
	}	
	public Patient(int id, String name, String ID, String favSong) {
		super();
		this.id = id;
		this.name = name;
		this.ID = ID;
		this.favSong = favSong;
	}	
	public Patient( String name, String ID, String favSong, float score) {
		super();
		this.name = name;
		this.ID = ID;
		this.favSong = favSong;
		this.score = score;
	}
	public Patient( String toString) {
		super();
		
		this.id= Integer.parseInt(toString.substring(toString.indexOf("id=")+3,toString.indexOf(", na")));
		this.name=toString.substring(toString.indexOf("me=")+3,toString.indexOf(", ID"));
		this.ID=toString.substring(toString.indexOf("ID=")+3,toString.indexOf(", fa"));
		this.favSong=toString.substring(toString.indexOf("ng=")+3,toString.indexOf(", sc"));
		this.score= Float.parseFloat(toString.substring(toString.indexOf("re=")+3,toString.indexOf(", do")));

			
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", ID=" + ID + ", favSong=" + favSong + ", score=" + score
				+ ", doctors=" + doctors + "]";
	}
	public Patient(int id, String patientname, String id_real, float score) {
		
		this.id=id;
		this.name = patientname;
		this.ID = id_real;
		this.score = score;

		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setDNI(String dNI) {
		ID = ID;
	}
	public String getFavSong() {
		return favSong;
	}
	public void setFavSong(String favSong) {
		this.favSong = favSong;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	
	
	
	
}
