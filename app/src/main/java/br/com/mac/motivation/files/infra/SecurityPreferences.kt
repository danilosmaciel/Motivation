package br.com.mac.motivation.files.infra

import android.content.Context

class SecurityPreferences(val context: Context) {

    private val mSharedPreferences = context.getSharedPreferences("motivationApp", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String){
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String) : String{
        return mSharedPreferences.getString(key,"") ?: ""
    }
}