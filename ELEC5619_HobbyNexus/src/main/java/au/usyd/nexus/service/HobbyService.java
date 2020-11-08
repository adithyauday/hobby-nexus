package au.usyd.nexus.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.usyd.nexus.dao.HobbyDAO;
import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.UserhobbyMay;


@Component
public class HobbyService {

	@Autowired
	private HobbyDAO HobbyDao;
	
	public List<User> getMembers(int hobby_id) {
		String hql="select user from User as user,UserhobbyMay as uhm  where uhm.user_id=user.user_id and hobby_id=:hobby_id";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("hobby_id", hobby_id);
		return HobbyDao.findList(hql, params);
	}

	public List<Article> getArticles(int hobby_id) {
		String hql="from Article where  hobby_id=:hobby_id";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("hobby_id", hobby_id);
		return HobbyDao.findList(hql, params);
	}

	public Hobby getHobby(Integer hobby_id) {
		return HobbyDao.get(hobby_id, Hobby.class);
	}



	public User getUser(Integer id) {
		return HobbyDao.get(id, User.class);
	}

	public Serializable save(Hobby hobby, InputStream in) throws IOException {
		return HobbyDao.save(hobby,in);
	}

	
	
	public void save(Object obj) {
		HobbyDao.save(obj);
		
	}


}