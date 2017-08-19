package com.saferize.interview.daoimpl;

import com.saferize.interview.dao.PathInfoDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
