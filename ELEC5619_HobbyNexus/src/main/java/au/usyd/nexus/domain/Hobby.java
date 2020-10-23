package au.usyd.nexus.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="hobby")
public class Hobby implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column private int hobby_id;
	@Column private String hobby_name;
	@Column private String hobby_desc;
	@Column private Date create_time;
	@Column private Blob photo;
	
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public int getHobby_id() {
		return hobby_id;
	}
	public void setHobby_id(int hobby_id) {
		this.hobby_id = hobby_id;
	}
	public String getHobby_name() {
		return hobby_name;
	}
	public void setHobby_name(String hobby_name) {
		this.hobby_name = hobby_name;
	}
	public String getHobby_desc() {
		return hobby_desc;
	}
	public void setHobby_desc(String hobby_desc) {
		this.hobby_desc = hobby_desc;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "hobby [hobby_id=" + hobby_id + ", hobby_name=" + hobby_name
				+ ", hobby_desc=" + hobby_desc + ", create_time=" + create_time
				+ "]";
	}
	
	
	
}
