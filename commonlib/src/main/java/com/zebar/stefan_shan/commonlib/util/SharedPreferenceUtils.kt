package com.zebar.stefan_shan.commonlib.util

import android.content.Context
import com.zebar.stefan_shan.commonlib.BaseApplication

/**
 * @author: Stefan_Shan
 * @data: 2018/8/31
 * @desc: SharedPreference工具类
 */
object SharedPreferenceUtils {

    fun setString(name: String, key: String, value: String) {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun setBoolean(name: String, key: String, value: Boolean) {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun getString(name: String, key: String): String {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getString(key, "")
    }

    fun getString(name: String, key: String, value: String): String? {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getString(key, value)
    }

    fun getBoolean(name: String, key: String, flag: Boolean): Boolean {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getBoolean(key, flag)
    }

    fun clear(name: String, key: String) {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit().remove(key)
        edit.apply()
    }

    fun setInt(name: String, key: String, value: Int) {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getInt(name: String, key: String): Int {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getInt(key, 0)
    }

    fun getInt(name: String, key: String, f: Int): Int {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getInt(key, f)
    }

    fun setLong(name: String, key: String, value: Long) {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putLong(key, value)
        edit.apply()
    }

    fun getLong(name: String, key: String): Long {
        val sp =
            BaseApplication.application.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getLong(key, 0)
    }

    fun setString(context: Context, name: String, key: String, value: String) {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun getString(context: Context, name: String, key: String): String {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getString(key, "")
    }

    fun clear(context: Context, name: String, key: String) {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit().remove(key)
        edit.apply()
    }

    fun setInt(context: Context, name: String, key: String, value: Int) {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getInt(context: Context, name: String, key: String): Int {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getInt(key, 0)
    }

    fun setLong(context: Context, name: String, key: String, value: Long) {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putLong(key, value)
        edit.apply()
    }

    fun getLong(context: Context, name: String, key: String): Long {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getLong(key, 0)
    }

    fun getLong(context: Context, name: String, key: String, f: Long): Long {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sp.getLong(key, f)
    }
}
