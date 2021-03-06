package au.usyd.nexus.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.UserhobbyMay;

@Repository
public class HobbyDAO {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public <T> T get(Serializable id, Class<?> cla) {
		Session session = sessionFactory.openSession();
		T t= (T)session.get(cla, id);
		session.close();
		return t;
	}



	public <T> List<T> findList(String hql, Map<String, Object> params) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		if (params != null) {
			for (Entry<String, Object> param : params.entrySet()) {
				String key = param.getKey();
				Object value = param.getValue();
				query.setParameter(key, value);
			}
		}
		List<T> list=query.list();
		session.close();
		return list;
	}

	public <T> T get(String hql, Class<T> cla, Map<String, Object> params) {
		List<T> list = this.findList(hql, params);
		return list.isEmpty() ? null : list.get(0);
	}


	public void update(Hobby h) {
		Session session = sessionFactory.openSession();
		session.update(h);
		session.flush();		
		session.close();
	}


	public Serializable save(Hobby hobby, InputStream in) throws IOException {
		Session session = sessionFactory.openSession();
        Blob blob =session.getLobHelper().createBlob(in, in.available());
        hobby.setPhoto(blob);
        hobby.setCreate_time(new Date());
        Serializable s=  session.save(hobby);
        session.close();
        return s;
	}


	public void save(Object obj) {
		Session session = sessionFactory.openSession();
		session.save(obj);
        session.close();		
	}
	
	public void joinhobby(int hobby_id, int user_id) {
		UserhobbyMay um = new UserhobbyMay();
		um.setHobby_id(hobby_id);
		um.setUser_id(user_id);
		save(um);
	}

}
