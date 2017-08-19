package com.saferize.interview.dao;

import com.saferize.interview.model.SaferizeInfo;

import java.util.List;

/**
 * @author Aman Mahato
 */
public interface PathInfoDao {
    public void persist(SaferizeInfo saferizeInfo);
    public List<SaferizeInfo> findAll();
}
