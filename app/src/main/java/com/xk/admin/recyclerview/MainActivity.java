package com.xk.admin.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xk.admin.recyclerview.ItemDecoration.GridViewDecoration;
import com.xk.admin.recyclerview.ItemDecoration.LinearLayoutDecoration;
import com.xk.admin.recyclerview.ItemDecoration.RedItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mStrings = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 9; i++) {
            mStrings.add("测试数据" + i);
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
     //   mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new GridViewDecoration(this,R.drawable.linearlayoutdecoration));
       // mRecyclerView.addItemDecoration(new DividerItemDecoration());
        mRecyclerView.setAdapter(new MyAdapter());
    }
    private class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(MainActivity.this).inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.list_item.setText(mStrings.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrings.size();
        }
        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView list_item;
            public ViewHolder(View itemView) {
                super(itemView);
                list_item = itemView.findViewById(R.id.list_item);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.grid_view:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
