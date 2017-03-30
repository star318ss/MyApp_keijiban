package com.example.a14rt0.myapp1;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;




public class MainActivity extends AppCompatActivity {
    Realm realm;
    RealmResults<Board> bresult;
    Board a;
    View v;
    ListView listView;
    ArrayList<String> boardname_list = new ArrayList<String>();
    String boardname;
    int ii=0;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_board_main);
        //realm の準備
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);

       //初期データの読み込み
        bresult = realm.where(Board.class).findAll();
        if (bresult.size()==0){
            String [] bdata = getResources().getStringArray(R.array.board_datas);
            for (String name:bdata){
                Add_Data(v,name);
            }
        }

        //Boardの名前データをArrayで取得
        boardname_list=Gain_BoardName_Last(realm);
        //板の表示
        Show_Category();

        //listを選ぶと画面遷移
        List_Click_Listener();
    }

    protected void onRestart(){
        super.onRestart();

        //realm の準備
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //Realm.deleteRealm(config);
        realm = Realm.getInstance(config);

        //listを選ぶと画面遷移
        List_Click_Listener();
    }

    protected void onDestory(){
        super.onDestroy();
        realm.close();
    }


    public ArrayList<String> Gain_BoardName_Last(Realm realm){
        bresult = realm.where(Board.class).findAll();

        int bresult_size=bresult.size();
        for(int i =0;i<bresult_size;i++){
            //Board board1 = new Board();
            bresult = realm.where(Board.class).findAll();
            //board1.setBoard_id(bresult.get(i).getBoard_id());
            String name=bresult.get(i).getBoard_name();

            // board1の名前のみをArrayListに入れる
            boardname_list.add(name);

        }


        return boardname_list;
    }

    public void Show_Category(){
        listView = (ListView) findViewById(R.id.list_board);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,boardname_list);
        listView.setAdapter(arrayAdapter);
        listView.getLayoutParams().height=boardname_list.size()*150;
    }

    public void List_Click_Listener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // リストビューの項目を取得
                ListView listview = (ListView) parent;
                boardname = (String)listview.getItemAtPosition(position);
                // id を取得
                RealmResults<Board> bid = realm.where(Board.class).equalTo("board_name",boardname).findAll();

                // インテントのインスタンス生成
                Intent intent = new Intent(MainActivity.this, Thrd_Activity.class);
                // 次画面のアクティビティ起動
                intent.putExtra("BoardName",boardname);
                String bb = bid.get(0).getBoard_id();
                intent.putExtra("BoardId",bb);
                //realm.close();
                startActivity(intent);

            }
        });
    }
    public void Add_Data(View v,String name){


        ii=realm.where(Board.class).findAll().size();
        realm.beginTransaction();
        a = realm.createObject(Board.class);
        a.setBoard_id(String.valueOf(ii));
        a.setBoard_name(name);
        realm.commitTransaction();
       // realm.close();
    }
}