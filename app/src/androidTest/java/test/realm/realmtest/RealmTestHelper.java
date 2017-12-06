package test.realm.realmtest;


import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import test.realm.realmtest.model.RealmFactory;

@SuppressWarnings("WeakerAccess")
public class RealmTestHelper {
    private static boolean mRealmInit;

    private static RealmFactory REALM_FACTORY = new RealmFactory() {
        @NonNull
        @Override
        public Realm get() {
            return getTestRealm();
        }
    };

    private static Supplier<RealmConfiguration> REALM_CONFIGURATION = Suppliers.memoize(new Supplier<RealmConfiguration>() {
        @Override
        public RealmConfiguration get() {
            initRealm();

            return new RealmConfiguration.Builder()
                    .name("android-test")
                    .inMemory()
                    .build();
        }
    });

    /**
     * Returns an instance of an in-memory realm for testing
     */
    public static Realm getTestRealm() {
        return Realm.getInstance(REALM_CONFIGURATION.get());
    }

    public static RealmFactory getTestRealmFactory() {
        return REALM_FACTORY;
    }

    public static int getGlobalInstanceCount() {
        return Realm.getGlobalInstanceCount(REALM_CONFIGURATION.get());
    }

    private static void initRealm() {
        if (!mRealmInit) {
            Realm.init(InstrumentationRegistry.getInstrumentation().getTargetContext());
            mRealmInit = true;
        }
    }
}