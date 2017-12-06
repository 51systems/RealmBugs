package test.realm.realmtest.model;

import android.support.annotation.NonNull;

import com.google.common.base.Supplier;

import javax.inject.Provider;

import io.realm.Realm;

public interface RealmFactory extends Provider<Realm>, Supplier<Realm> {
    /**
     * Return a realm instance.
     * @return {@link Realm} instance
     */
    @NonNull
    @Override
    Realm get();
}