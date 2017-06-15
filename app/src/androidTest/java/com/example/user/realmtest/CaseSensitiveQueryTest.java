package com.example.user.realmtest;


import com.example.user.realmtest.model.Dog;

import org.junit.Test;

import io.realm.Case;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class CaseSensitiveQueryTest extends RealmTestBase {

    @Test
    public void testCaseSensitiveQuery() throws Exception {

        realm.beginTransaction();
        Dog dog1 = realm.copyToRealm(Dog.create("ROVER"));
        Dog dog2 = realm.copyToRealm(Dog.create("Rover"));
        realm.commitTransaction();

        assertThat(realm.where(Dog.class).equalTo("name", "ROVER", Case.SENSITIVE).findAll(),
                containsInAnyOrder(dog1));

        assertThat(realm.where(Dog.class).equalTo("name", "Rover", Case.SENSITIVE).findAll(),
                containsInAnyOrder(dog2));

        assertThat(realm.where(Dog.class).equalTo("name", "rover", Case.INSENSITIVE).findAll(),
                containsInAnyOrder(dog1, dog2));
    }
}
