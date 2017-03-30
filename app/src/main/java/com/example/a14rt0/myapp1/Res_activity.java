package com.example.a14rt0.myapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * Created by 14rt0 on 2017/03/28.
 */

public class Res_activity extends Activity{
    String thread_title;
    Realm realm;
    RealmResults<RES> rresult;
    RES res1;
    View v;
    EditText edt_comment,edt_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_res_show);
        BackButton_Listener();
       ///realmの準備
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);

        // Thrd_Activityからタイトルを受け取る
        Intent intent = getIntent();
        thread_title = intent.getStringExtra("TreadTitle");
        //スレッドタイトルをセット
        TextView tv=(TextView) findViewById(R.id.thread_title);
        tv.setText(thread_title);


        //レスをセット
        Show_Last_Info(v);
        // 書き込み部分を表示
        Add_WriteBox();

        //戻るボタンが押されたとき
        Button btn = (Button) findViewById(R.id.button_back_thd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                finish();
            }
        });


    }

    public void BackButton_Listener() {
        Button btn = (Button) findViewById(R.id.button_back_thd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                //realm.close();
                finish();
            }
        });
    }
    public void onClickAction(View v){
        // push send_button
        // writing DB
        Add_Data(v);
        // show last data
        Show_Last_Info(v);
        //  書き込み部分を表示
        Add_WriteBox();
    }

    public void Add_Data(View v){
        edt_comment = (EditText) findViewById(R.id.edt_comment);
        edt_name = (EditText) findViewById(R.id.edt_name);
        //　　未入力の時はデータの更新だけする
        if(edt_comment .length() != 0){
            // 入力有の時

            rresult = realm.where(RES.class).findAll();
            realm.beginTransaction();

            // write
            //String id =AutoIncrement.newIdWithIdName(realm,RES.class,"res_id");
            res1 = realm.createObject(RES.class);
            res1.setRes_id("ccc");

            res1.setName(edt_name.getText().toString());
            res1.setResnum(String.valueOf(rresult.size()));//queryは０から始まる
            res1.setTime(getNowDate());

            res1.setComment(edt_comment.getText().toString());
            realm.commitTransaction();
            realm.close();

        }

    }
    public static String getNowDate(){
        final Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日' kk'時'mm'分'ss'秒'");
        return sdf.format(date);
    }
    public void Show_Last_Info(View v){
        rresult = realm.where(RES.class).findAll();
        setContentView(R.layout.actv_res_show);
        BackButton_Listener();

        for(int i = 0; i < rresult.size(); i++){
            //  レス番号
            addTextView(String.valueOf(i));
            //  Time
            addTextView(rresult.get(i).getTime());
            //  Name
            addTextView(rresult.get(i).getName());
            //  Comment
            addTextView(rresult.get(i).getComment());
        }
    }
    public void addTextView(String message) {
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.root_layout);
        // TextViewを作成してテキストを設定
        TextView tv = new TextView(this);
        tv.setText(message);
        layout.addView(tv);
    }
    public void Add_WriteBox(){
        LinearLayout layout = (LinearLayout)this.findViewById(R.id.root_layout);
        View view = getLayoutInflater().inflate(R.layout.write_box, null);
        layout.addView(view);
        //setContentView(layout);
    }

}
