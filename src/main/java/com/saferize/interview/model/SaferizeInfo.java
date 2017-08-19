package com.saferize.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aman Mahato
 */
@Entity
@Table(name = "saferizeInfo")
public class SaferizeInfo {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    public Integer id;

    @Column(name="path_url")
    public String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
