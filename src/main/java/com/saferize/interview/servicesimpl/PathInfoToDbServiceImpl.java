package com.saferize.interview.servicesimpl;

import com.saferize.interview.dao.PathInfoDao;
import com.saferize.interview.model.SaferizeInfo;
import com.saferize.interview.services.PathInfoToDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Aman Mahato
 */
@Service
public class PathInfoToDbServiceImpl implements PathInfoToDbService {

    @Autowired
    private PathInfoDao pathInfoDao;

    public void setPathInfoDao(PathInfoDao pathInfoDao) {
        this.pathInfoDao = pathInfoDao;
    }

    @Override
    @Transactional
    public void addUrlToDb(SaferizeInfo saferizeInfo) {
        this.pathInfoDao.persist(saferizeInfo);
    }

    @Override
    @Transactional
    public List<SaferizeInfo> listAllUrls() {
        return this.pathInfoDao.findAll();
    }
}
