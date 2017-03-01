package com.chao.springhbn.dao;

import com.chao.springhbn.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository("userDao")
public class UserDao extends BaseDao implements UserDaoInterface{

	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	public User getUser(int id) {
		return getHibernateTemplate().get(User.class,id);
	}

	public List<User> findUserByName(String name) {
		return (List<User>)getHibernateTemplate().find("from User u where u.name like ?",name + "%");
	}

	public long getUserNum() {
	 	return	(Long) getHibernateTemplate().iterate("select count(u.id) from User u").next();
	}
	
	public long geUserNum2() {
		Long forumNum = getHibernateTemplate().execute(new HibernateCallback<Long>() {

			public Long doInHibernate(Session session)
					throws HibernateException, SQLException {
				return (Long) session.createQuery("select count(u.id) from User u").list().iterator().next();
			}
		});
		return forumNum;
	}

	
	
}
