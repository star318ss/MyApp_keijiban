package com.example.a14rt0.myapp1;

/**
 * Created by 14rt0 on 2017/03/24.
 */
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;



// Define you model class by extending RealmObject
public class RES extends RealmObject {
    //@PrimaryKey
    private String res_id=null;//一意

    private String thread_id;
    private String resnum; //1スレッド，1000レスまで
    private String time;
    private String name;
    private String comment;

    // ... Generated getters and setters ...

    public String getRes_id() {
        return res_id;
    }
    public void setRes_id(String res_id) {
        res_id = res_id;
    }

    public String getThread_id() {
        return thread_id;
    }
    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getResnum() {
        return resnum;
    }
    public void setResnum(String resnum) {
        this.resnum = resnum;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }


}
