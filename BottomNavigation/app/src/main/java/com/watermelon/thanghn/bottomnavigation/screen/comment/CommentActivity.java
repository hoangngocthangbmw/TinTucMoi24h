package com.watermelon.thanghn.bottomnavigation.screen.comment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.watermelon.thanghn.bottomnavigation.R;
import com.watermelon.thanghn.bottomnavigation.screen.detailnewsonline.DetailNewsActivity;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBack;
    private ListView listView;
    private EditText edtComment;
    private ImageButton imgSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_comment);
        initView();
        initData();
        initAction();
    }

    private void initAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void initData() {
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);
        listView=(ListView)findViewById(R.id.listView);
        edtComment=(EditText)findViewById(R.id.editTextCmt);
        imgSend=(ImageButton)findViewById(R.id.buttonSend);
        imgSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBack:
                Intent intent=new Intent(CommentActivity.this, DetailNewsActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonSend:
                Toast.makeText(CommentActivity.this,"click",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
