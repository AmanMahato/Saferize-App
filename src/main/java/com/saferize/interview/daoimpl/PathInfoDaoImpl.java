package com.saferize.interview.daoimpl;

import com.saferize.interview.dao.PathInfoDao;
import com.saferize.interview.model.SaferizeInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aman Mahato
 */

@Repository
@Transactional
public class PathInfoDaoImpl implements PathInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(SaferizeInfo saferizeInfo) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(saferizeInfo);
    }

    @Override
    public List<SaferizeInfo> findAll() {
        Session session=sessionFactory.getCurrentSession();
        List<SaferizeInfo> infoList=session.createQuery("FROM saferizeInfo").list();
        return infoList;
    }
}
