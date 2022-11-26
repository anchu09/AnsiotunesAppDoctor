package db.pojos.users;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.*; 

@Entity
@Table(name = "users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3902983480527656994L;

	@Id
	@GeneratedValue(generator = "users")
	@TableGenerator(name = "users", table = "sqlite_sequence", 
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "users")
	private Integer id;
	private String email;
	@Lob
	private byte[] password;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	public User() {
		super();
	}

	public User(String email, byte[] password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	} 
	public User(String toString, Role r) {
		super();
		this.id= Integer.parseInt(toString.substring(toString.indexOf("id=")+3,toString.indexOf(", em")));
	this.email=toString.substring(toString.indexOf("il=")+3,toString.indexOf(", pas"));
//	this.password=toString.substring(toString.indexOf("word="),toString.indexOf(", ro"));
	this.role=r;
	
	
	} 
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + Arrays.toString(password) + ", role=" + role
				+ "]";
	}
	public User(int id,String email, byte[] password, Role role) {
		super();
		this.id=id;
		this.email = email;
		this.password = password;
		this.role = role;
	} 


	public User(Integer id, String mail, Role r) {
		
		this.id= id;
		this.email=mail;
		this.role=r;
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
