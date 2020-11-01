package au.usyd.nexus.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import au.usyd.nexus.dao.EventDAO;
import au.usyd.nexus.dao.HobbyDAO;
import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Event;

@Component
@Transactional
public class EventService{
	
	@Autowired
	private EventDAO EventDao;

	
	public List<User> getMembers(int event_id) {
		String hql="select user from User as user,Usereventmap as uem where uem.user_id=user.user_id and event_id=:event_id";
//		String hql="select event from Event as event,Usereventmap as uem where uem.event_id=event.event_id and user_id=:user_id";
		
		
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("event_id", event_id);
		return EventDao.findList(hql, params);
	}

	public List<Event> getEvents(int event_id) {
		String hql="from Event where event_id=:event_id";
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("event_id", event_id);
		return EventDao.findList(hql, params);
	}
		
	
	public Event getEvent(Integer event_id) {
		return EventDao.get(event_id, Event.class);
	}
	
	public Event getEventName(Integer event_id) {
		return EventDao.get(event_id, Event.class);
	}

	public User getUser(Integer id) {
		return EventDao.get(id, User.class);
	}
	

	
	@Resource
	private SessionFactory sessionFactory;
	
	public void save(Event event) {
		sessionFactory.getCurrentSession().persist(event);
	}
	
	public void updates(Event event) {
		sessionFactory.getCurrentSession().merge(event);
	}
	//@Transactional(propagation=Propagation.REQUIRED,readOnly=true) 
	public List getAllEvent() {
		List list = new ArrayList();
		String hql = "from Event as e ";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list= query.list(); //返回的是一个集dao合
		return list;
	}

} 