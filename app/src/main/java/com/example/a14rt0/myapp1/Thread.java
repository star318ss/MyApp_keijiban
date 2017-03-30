package com.example.a14rt0.myapp1;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 14rt0 on 2017/03/28.
 */

public class Thread extends RealmObject {
    //@PrimaryKey
    private String tread_id;
    private String board_id;
    private String tread_name;

    public String getTread_id() {
        return tread_id;
    }
    public void setTread_id(String tread_id) {
        this.tread_id = tread_id;
    }

    public String getBoard_id() {
        return board_id;
    }
    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }

    public String getTread_name() {
        return tread_name;
    }
    public void setTread_name(String tread_name) {
        this.tread_name = tread_name;
    }
}
