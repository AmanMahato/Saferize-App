package com.saferize.interview.services;

import com.saferize.interview.model.SaferizeInfo;

import java.util.List;

/**
 * @author Aman Mahato
 */
public interface PathInfoToDbService {

    public void addUrlToDb(SaferizeInfo saferizeInfo);
    public List<SaferizeInfo> listAllUrls();
}
