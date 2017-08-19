package com.saferize.interview.model;

import javax.persistence.*;

/**
 * @author Aman Mahato
 */
@Entity
@Table(name = "saferizeInfo")
public class SaferizeInfo {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
