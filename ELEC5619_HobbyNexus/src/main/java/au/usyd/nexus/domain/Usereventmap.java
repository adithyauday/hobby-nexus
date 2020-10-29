package au.usyd.nexus.domain;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_event_map")
public class Usereventmap implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column private int user_event_id;
	@Column private int user_id;
	@Column private int event_id;
	@Column private Date create_time;
	
	
	public int getUser_event_id() {
		return user_event_id;
	}
	public void setUser_event_id(int user_event_id) {
		this.user_event_id = user_event_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "Usereventmap [user_event_id=" + user_event_id + ", user_id=" + user_id + ", event_id=" + event_id
				+ ", create_time=" + create_time + "]";
	}
	
	
}