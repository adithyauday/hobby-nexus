package au.usyd.nexus.domain;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column private int user_id;
	@Field
	@Column private String user_name;
	@Column private String user_type;
	@Column private String password;
	@Column private String email;
	@Column private String location;
	@Column private Blob photo;
	
	public User() {}
	
	public User(String user_name, String email, String password) {
		this.user_name = user_name;
		this.email = email;
		this.password = password;
	}
	
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID: " + user_id + ";");
        buffer.append("Name: " + user_name + ";");
        buffer.append("Email Address: " + email);
        return buffer.toString();
	}
}
