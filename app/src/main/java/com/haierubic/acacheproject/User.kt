package com.haierubic.acacheproject

import java.io.Serializable

/**
 * Created by zcy on 2017/7/7.
 */

class User : Serializable {

    var name: String? = null
    var age: Int = 0
    var hobby: String? = null

    override fun toString(): String {
        return "User{" +
                "name='" + name + '\''.toString() +
                ", age=" + age +
                ", hobby='" + hobby + '\''.toString() +
                '}'.toString()
    }
}
