package au.usyd.nexus.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private int artice_id;
	@Column
	private int hobby_id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private Date create_time;
	@Column
	private int comment_num;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=(CascadeType.ALL))
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(cascade = { CascadeType.ALL},fetch=FetchType.EAGER )
	@JoinColumn(name = "artice_id")
	private Set<Comment> comments = new HashSet<Comment>();

	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public int getArtice_id() {
		return artice_id;
	}

	public void setArtice_id(int artice_id) {
		this.artice_id = artice_id;
	}

	public int getHobby_id() {
		return hobby_id;
	}

	public void setHobby_id(int hobby_id) {
		this.hobby_id = hobby_id;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
