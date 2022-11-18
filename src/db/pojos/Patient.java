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
	public String toString() {
		// TODO Auto-generated method stub
		return "Name"+this.name;
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
	
	
	
	
	
}
