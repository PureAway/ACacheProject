package com.haierubic.acacheproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zcy.acache.ACache;

public class MainActivity extends AppCompatActivity {

    private EditText edName, edAge, edHobby;
    private Button btnPut, btnGet;
    private TextView tvShow;
    private ACache cache;
    private final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cache = ACache.get(this);
        setContentView(R.layout.activity_main);
        edName = (EditText) findViewById(R.id.edName);
        edAge = (EditText) findViewById(R.id.edAge);
        edHobby = (EditText) findViewById(R.id.edHobby);
        btnPut = (Button) findViewById(R.id.btnPut);
        btnGet = (Button) findViewById(R.id.btnGet);
        tvShow = (TextView) findViewById(R.id.tvShow);
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    put();
                }
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
    }

    private void put() {
        User user = new User();
        user.setName(edName.getText().toString().trim());
        user.setAge(Integer.parseInt(edAge.getText().toString().trim()));
        user.setHobby(edHobby.getText().toString().trim());
        // 保存一周
        cache.put(USER, user, ACache.TIME_DAY * 7);
    }


    private void get() {
        User user = (User) cache.getAsObject(USER);
        if (null != user) {
            tvShow.setText(user.toString());
        }
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(edName.getText().toString().trim())) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(edAge.getText().toString().trim())) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(edHobby.getText().toString().trim())) {
            Toast.makeText(this, "请输入爱好", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
