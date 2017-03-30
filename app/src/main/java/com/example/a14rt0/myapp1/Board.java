package com.example.a14rt0.myapp1;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by 14rt0 on 2017/03/28.
 */

public class Board extends RealmObject {

    //@PrimaryKey
    private String board_id = null;
    private String board_name;

    public String getBoard_id() {
        return board_id;
    }
    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }
    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }
}
