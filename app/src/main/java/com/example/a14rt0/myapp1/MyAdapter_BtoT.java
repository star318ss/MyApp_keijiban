package com.example.a14rt0.myapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 14rt0 on 2017/03/28.
 */

public class MyAdapter_BtoT extends BaseAdapter {


    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Board> boardlist;

    public void MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList boardlist) {
        this.boardlist = boardlist;
    }

    @Override
    public int getCount() {
        return boardlist.size();
    }

    @Override
    public Board getItem(int position) {
        return boardlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(boardlist.get(position).getBoard_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.listview_btot, parent, false);

        ((TextView) convertView.findViewById(R.id.board_id)).setText(String.valueOf(boardlist.get(position)));
        ((TextView) convertView.findViewById(R.id.board_name)).setText(String.valueOf(boardlist.get(position)));

        return convertView;
    }
}