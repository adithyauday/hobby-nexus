package au.usyd.nexus.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "user")
public class User implements Serializable {

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private static String SALT = "123456";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private int user_id;
	@Field
	@Column
	private String user_name;
	@Column
	private String user_type;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String location;
	@Column(columnDefinition = "LONGBLOB")
	private Blob photo;

	public User() {
	}

	public User(String user_name, String email, String password) {
		this.user_name = user_name;
		this.email = email;
		this.password = password;
	}

	public Blob getPhoto() throws IOException, SQLException, SQLException {
		if (photo == null) {
			FileInputStream file = new FileInputStream("default.png");
			byte[] bytes = IOUtils.toByteArray(file);
			Blob blob = new SerialBlob(bytes);
			return blob;
		}
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

	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	@Column
	private String changePassword;

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

	// code from
	// https://stackoverflow.com/questions/20832008/jsp-simple-password-encryption-decryption
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	// code from
	// https://stackoverflow.com/questions/20832008/jsp-simple-password-encryption-decryption
	public String hashPassword(String in) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(SALT.getBytes()); // <-- Prepend SALT.
			md.update(in.getBytes());

			byte[] out = md.digest();
			return bytesToHex(out);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
