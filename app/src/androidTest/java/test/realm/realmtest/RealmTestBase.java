package test.realm.realmtest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import io.realm.Realm;
import test.realm.realmtest.model.RealmFactory;

public abstract class RealmTestBase {
    protected RealmFactory realmFactory;
    protected Realm realm;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        private boolean failed;

        @Override
        protected void failed(Throwable e, Description description) {
            failed = true;
        }

        @Override
        protected void finished(Description description) {
            tearDownRealm(failed);
        }
    };

    @Before
    public void setUpRealm() throws Exception {
        realmFactory = RealmTestHelper.getTestRealmFactory();
        realm = realmFactory.get();
    }

    private void tearDownRealm(boolean testFailed) {
        if (realm != null && !realm.isClosed()) {

            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }

            realm.close();
        }

        int globalInstances = RealmTestHelper.getGlobalInstanceCount();
        if (globalInstances != 0) {
            if (!testFailed) {
                throw new AssertionError("Realm leak! Remaining instances: " + globalInstances);
            }
        }
    }
}
