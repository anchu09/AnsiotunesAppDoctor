package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;




public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String name;
	private String collegiate_number;
	private String hospital;
	private ArrayList<Patient>patients;
	

	public Doctor() {
		super();
	}
	
	public Doctor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Doctor(String name,  String collegiate_number,
			String hospital) {
		super();
		this.name = name;
		this.collegiate_number = collegiate_number;
		this.hospital = hospital;
	}


	public Doctor(Integer id, String name, String collegiate_number,
			String hospital) {
		super();
		this.id = id;
		this.name = name;
		this.collegiate_number = collegiate_number;
		this.hospital = hospital;
	}
	public Doctor(String toString) {
		super();
		
	this.id= Integer.parseInt(toString.substring(toString.indexOf("id=")+3,toString.indexOf(", na")));
	this.name=toString.substring(toString.indexOf("me=")+3,toString.indexOf(", co"));
	this.collegiate_number=toString.substring(toString.indexOf("er=")+3,toString.indexOf(", ho"));
	this.hospital=toString.substring(toString.indexOf("al=")+3,toString.indexOf(", pa"));

		
	}
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", collegiate_number=" + collegiate_number + ", hospital="
				+ hospital + ", patients=" + patients + "]";
		
		
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collegiate_number == null) ? 0 : collegiate_number.hashCode());
		result = prime * result + ((hospital == null) ? 0 : hospital.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		
		if (collegiate_number == null) {
			if (other.collegiate_number != null)
				return false;
		} else if (!collegiate_number.equals(other.collegiate_number))
			return false;
		if (hospital == null) {
			if (other.hospital != null)
				return false;
		} else if (!hospital.equals(other.hospital))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		
		
		return true;
	}




	


	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	

	


	public String getCollegiate_number() {
		return collegiate_number;
	}

	public void setCollegiate_number(String collegiate_number) {
		this.collegiate_number = collegiate_number;
	}

	



	
	public String getHospital() {
		return hospital;
	}

	
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}


	


	
	
}
