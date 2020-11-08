package au.usyd.nexus.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;


import au.usyd.nexus.dao.EventDAO;
import au.usyd.nexus.dao.HobbyDAO;
import au.usyd.nexus.domain.Article;
import au.usyd.nexus.domain.Hobby;
import au.usyd.nexus.domain.User;
import au.usyd.nexus.domain.Usereventmap;
import au.usyd.nexus.domain.Event;



@Component
@Transactional
public class EventService{
	
	@Autowired
	private EventDAO EventDao;
	
	@Autowired
	private HobbyDAO HobbyDao;
	
	public List<User> getMembers(int event_id) {
		String hql="select user from User as user,Usereventmap as uem where uem.user_id=user.user_id and event_id=:event_id";
	
		
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
	
	public Hobby getHobbyId(Integer hobby_id) {
		return HobbyDao.get(hobby_id, Hobby.class);
	}
	
	
	private static Integer init_event_id =5;
	private Map<Integer, Event> addNewEvent =null;
	@Resource
	private SessionFactory sessionFactory;
	
	public void saveUserEventMap(Usereventmap uem) {
		Session session = sessionFactory.openSession();
		session.save(uem);
	}
	
	public void save(Event event){
		Session sess = sessionFactory.openSession();
		Event even =new Event();
		even.setEvent_id(init_event_id++);
		even.setHobby_id(2);
		even.setEvent_name(event.getEvent_name());
		even.setEvent_desc(event.getEvent_desc());
		even.setSkill_level_limit(event.getSkill_level_limit());
		even.setNumber_limit(event.getNumber_limit());
		even.setAttandance(1);
		even.setLocation(event.getLocation());
		even.setEvent_date(event.getEvent_date());
		even.setCreate_time("2020-11-04 12:34:50");
		System.out.println(even);
		sess.save(even);
	}
	public void updates(Event event) {
		sessionFactory.getCurrentSession().merge(event);
	}
	
	public List getAllEvent() {
		List list = new ArrayList();
		String hql = "from Event as e ";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list= query.list(); //return a list
		return list;
	}
	
	public List getAllHobby() {
		List list = new ArrayList();
		String hql = "from Hobby as h ";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		list= query.list(); //return a list
		return list;
	}
} 