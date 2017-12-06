package test.realm.realmtest.repository;


import org.junit.Test;

import io.realm.RealmObject;
import test.realm.realmtest.RealmTestBase;
import test.realm.realmtest.model.MainEntity;
import test.realm.realmtest.model.TestEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RepositoryTest extends RealmTestBase {

    @Test
    public void testTestEntity() throws Exception {
        realm.beginTransaction();
        RealmObject e = realm.copyToRealm(new TestEntity());
        realm.commitTransaction();

        assertThat(realm.where(TestEntity.class).findFirst(), is(equalTo(e)));
    }

    @Test
    public void testMainEntity() throws Exception {
        realm.beginTransaction();
        RealmObject e = realm.copyToRealm(new MainEntity());
        realm.commitTransaction();

        assertThat(realm.where(MainEntity.class).findFirst(), is(equalTo(e)));
    }
}
