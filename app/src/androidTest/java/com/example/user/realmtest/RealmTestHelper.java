package com.example.user.realmtest;

import android.support.test.InstrumentationRegistry;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@SuppressWarnings("WeakerAccess")
public class RealmTestHelper {

    private static RealmConfiguration REALM_CONFIGURATION;

    static {
        Realm.init(InstrumentationRegistry.getInstrumentation().getTargetContext());

        REALM_CONFIGURATION = new RealmConfiguration.Builder()
                .name("android-test")
                .inMemory()
                .build();
    }

    /**
     * Returns an instance of an in-memory realm for testing
     */
    public static Realm getTestRealm() {
        return Realm.getInstance(REALM_CONFIGURATION);
    }

    public static int getGlobalInstanceCount() {
        return Realm.getGlobalInstanceCount(REALM_CONFIGURATION);
    }

}