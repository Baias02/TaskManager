package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {
    private val pref = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean{
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun userSeen(){
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun editName(): String?{
        return  pref.getString(EDIT_NAME, "")
    }

    fun userName(name: String){
        return pref.edit().putString(EDIT_NAME, name).apply()
    }

    fun profImage(): String? {
        return pref.getString(PROF_IMAGE, "")
    }

    fun userImage(profile: String){
        return pref.edit().putString(PROF_IMAGE, profile).apply()
    }
    companion object{
        const val SHARED_NAME = "task_app"
        const val SEEN_KEY = "isSeen"
        const val EDIT_NAME = "edit_name"
        const val PROF_IMAGE = "prof_image"
    }
}