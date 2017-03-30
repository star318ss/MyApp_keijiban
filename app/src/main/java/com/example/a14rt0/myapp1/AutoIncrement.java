package com.example.a14rt0.myapp1;

/**
 * Created by 14rt0 on 2017/03/28.
 */
import io.realm.Realm;
import io.realm.RealmObject;

public class AutoIncrement {
    public static String newId(Realm realm, Class<? extends RealmObject> clazz) {
        return newIdWithIdName(realm, clazz, "id");
    }

    public static String  newIdWithIdName(Realm realm, Class<? extends RealmObject> clazz, String idName) {
        return String.valueOf(realm.where(clazz).max(idName).intValue() + 1);
    }
}