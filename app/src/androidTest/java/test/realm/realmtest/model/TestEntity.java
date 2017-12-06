package test.realm.realmtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class TestEntity extends RealmObject {
    @PrimaryKey
    private String id;
}
