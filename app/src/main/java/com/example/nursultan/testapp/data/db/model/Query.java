package com.example.nursultan.testapp.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "queries")
public class Query {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "queryText")
    private String queryText;

    @Property(nameInDb = "created_at")
    private String createdAt;


    @Generated(hash = 617069742)
    public Query(Long id, String queryText, String createdAt) {
        this.id = id;
        this.queryText = queryText;
        this.createdAt = createdAt;
    }

    @Generated(hash = 1837957505)
    public Query() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

}
