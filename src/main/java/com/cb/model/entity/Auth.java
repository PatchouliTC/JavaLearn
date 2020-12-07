package com.cb.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Auth implements Serializable {

    private static final long serialVersionUID = -8795150434108619722L;

    private Long id;

    private String name;

    private String permission;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Auth setName(String name) {
        this.name = name;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Auth setPermission(String permission) {
        this.permission = permission;
        return this;
    }
}