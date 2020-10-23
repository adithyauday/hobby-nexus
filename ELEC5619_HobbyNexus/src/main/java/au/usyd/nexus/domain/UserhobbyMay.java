package au.usyd.nexus.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_hobby_may")
public class UserhobbyMay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private int user_hobby_id;
	@Column
	private int hobby_id;
	@Column
	private int skill_level;
	@Column
	private int user_id;

	public int getUser_hobby_id() {
		return user_hobby_id;
	}

	public void setUser_hobby_id(int user_hobby_id) {
		this.user_hobby_id = user_hobby_id;
	}

	public int getHobby_id() {
		return hobby_id;
	}

	public void setHobby_id(int hobby_id) {
		this.hobby_id = hobby_id;
	}

	public int getSkill_level() {
		return skill_level;
	}

	public void setSkill_level(int skill_level) {
		this.skill_level = skill_level;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



}
