package com.haierubic.acacheproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zcy.acache.ACache;


/**
 * @author zcy
 */
public class MainActivity extends AppCompatActivity {

    private ACache cache;
    private EditText edName, edAge, edHobby;
    private TextView tvShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.edName);
        edAge = findViewById(R.id.edAge);
        edHobby = findViewById(R.id.edHobby);
        tvShow = findViewById(R.id.tvShow);
        cache = ACache.get(this);
        findViewById(R.id.btnPut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    put();
                }
            }
        });
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });

    }

    private void put() {
        User user = new User();
        user.setAge(Integer.parseInt(edAge.getText().toString()));
        user.setName(edName.getText().toString());
        user.setHobby(edHobby.getText().toString());
        // 保存一周
        cache.put("USER", user, ACache.TIME_DAY * 7);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(edName.getText().toString())) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(edAge.getText().toString())) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(edHobby.getText().toString())) {
            Toast.makeText(this, "请输入爱好", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void get() {
        User user = (User) cache.getAsObject("USER");
        if (null != user) {
            tvShow.setText(user.toString());
        }
    }
}
