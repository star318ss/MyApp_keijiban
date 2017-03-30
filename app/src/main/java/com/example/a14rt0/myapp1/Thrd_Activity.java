package com.example.a14rt0.myapp1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by 14rt0 on 2017/03/28.
 */

public class Thrd_Activity extends Activity {
    String category_name ,bid;
    Realm realm;
    RealmResults<Thread> tresult;
    View v;
    ArrayList<String> threadname_list= new ArrayList<String>();
    ListView tlistView;
    Thread t;
    int ii=0,res;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_threads_show);
        // realm の用意
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);

        // id,nameをMainActivityから受け取る
        Intent intent = getIntent();
        bid = intent.getStringExtra("BoardId");
        category_name = intent.getStringExtra("BoardName");

        // 掲示板の名前をセット
        TextView tv = (TextView) findViewById(R.id.tv_category_name);
        tv.setText(category_name);

        //初期データThreadの読み込み
        ////Boardのどのposが選ばれたのか
        Where_Board_Pos(bid);

        String[] bdata = getResources().getStringArray(res);
        for (String name:bdata){
            Add_Data(v,name);
        }


        //スレッドたちの名前を得る
        Gain_TreadName_Last(realm);
        //スレッドの表示
        tlistView = (ListView) findViewById(R.id.list_thread);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,threadname_list);
        tlistView.setAdapter(arrayAdapter);
        tlistView.getLayoutParams().height=threadname_list.size()*150;

        //listを選ぶと画面遷移
        tlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // リストビューの項目を取得
                ListView listview = (ListView) parent;
                String titem = (String)listview.getItemAtPosition(position);

                //realm.close();
                // インテントのインスタンス生成
                Intent intent = new Intent(Thrd_Activity.this, Res_activity.class);
                // 次画面のアクティビティ起動
                intent.putExtra("TreadTitle",titem);

                startActivity(intent);

            }
        });

        //戻るボタンが押されたとき
        Button btn = (Button) findViewById(R.id.button_back_board);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 次画面のアクティビティ終了
                finish();
            }
        });

    }

    public void onRestart(){
        super.onRestart();
        //realm の準備
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);

    }
    public void Gain_TreadName_Last(Realm realm) {
        tresult = realm.where(Thread.class).equalTo("board_id",bid).findAll();

        int bresult_size = tresult.size();
        for (int i = 0; i < bresult_size; i++) {
            //Board board1 = new Board();
            tresult = realm.where(Thread.class).findAll();
            //board1.setBoard_id(bresult.get(i).getBoard_id());
            String name = tresult.get(i).getTread_name();

            // board1の名前のみをArrayListに入れる
            threadname_list.add(name);

        }
    }
    public void Where_Board_Pos(String bid) {
        //tresult = realm.where(Thread.class).findAll();
        //if (tresult.size() == 0) {
            int pos = Integer.valueOf(bid);
            switch (pos) {
                case 0:
                    res = R.array.t0;
                    break;
                case 1:
                    res = R.array.t1;
                    break;
                case 2:
                    res = R.array.t2;
                    break;
                case 3:
                    res = R.array.t3;
                    break;
                case 4:
                    res = R.array.t4;
                    break;
            //}
        }
    }
    public void Add_Data(View v,String name){

        ii=realm.where(Thread.class).findAll().size();
        realm.beginTransaction();
        t = realm.createObject(Thread.class);
        t.setTread_id(String.valueOf(ii));
        t.setTread_name(name);
        t.setBoard_id(bid);
        realm.commitTransaction();
        // realm.close();
    }
}