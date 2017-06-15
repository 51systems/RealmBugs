package com.example.user.realmtest.model;


import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.Required;

public class Dog extends RealmObject {

    @Index
    @Required
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Dog create(String name) {
        Dog entity = new Dog();
        entity.setName(name);

        return entity;
    }
}
