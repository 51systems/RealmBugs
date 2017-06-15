package com.example.user.realmtest;


import org.junit.After;
import org.junit.Before;

import io.realm.Realm;

public abstract class RealmTestBase {
    protected Realm realm;

    @Before
    public void setUp() throws Exception {
        realm = RealmTestHelper.getTestRealm();
    }

    @After
    public void tearDown() throws Exception {
        if (realm != null && !realm.isClosed()) {

            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }

            realm.close();
        }

        int globalInstances = RealmTestHelper.getGlobalInstanceCount();
        if (globalInstances != 0) {
            throw new AssertionError("Realm leak! Remaining instances: " + globalInstances);
        }
    }
}
