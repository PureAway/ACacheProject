package com.haierubic.acacheproject

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast

import com.zcy.acache.ACache
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var cache: ACache? = null
    private val USER = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cache = ACache.get(this)
        btnPut.setOnClickListener {
            if (checkInput()) {
                put()
            }
        }
        btnGet.setOnClickListener { get() }
    }

    private fun put() {
        val user = User()
        user.name = edName.text.toString().trim { it <= ' ' }
        user.age = Integer.parseInt(edAge.text.toString().trim { it <= ' ' })
        user.hobby = edHobby.text.toString().trim { it <= ' ' }
        // 保存一周
        cache?.put(USER, user, ACache.TIME_DAY * 7)
    }


    private fun get() {
        val user = cache!!.getAsObject(USER) as User?
        if (null != user) {
            tvShow!!.text = user.toString()
        }
    }

    private fun checkInput(): Boolean {
        if (TextUtils.isEmpty(edName.text.toString().trim { it <= ' ' })) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(edAge.text.toString().trim { it <= ' ' })) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(edHobby.text.toString().trim { it <= ' ' })) {
            Toast.makeText(this, "请输入爱好", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
