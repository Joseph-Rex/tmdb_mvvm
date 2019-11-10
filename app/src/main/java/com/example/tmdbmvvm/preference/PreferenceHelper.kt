package com.example.tmdbmvvm.preference

import android.content.Context
import java.lang.Exception

class PreferenceHelper(private val ctx: Context) {

    fun getOfflineCount(): Int{

        var count : Int
        val preference=ctx.getSharedPreferences("TMDB", Context.MODE_PRIVATE)
        try{
            count = preference.getInt("count",0)
        }catch (exe : Exception){
            count = 0
        }

        return  count
    }

    fun setOfflineCount(count : Int): Int{

        var status : Int
        val preference=ctx.getSharedPreferences("TMDB", Context.MODE_PRIVATE)
        try{
            val editor=preference.edit()
            editor.putInt("count",count)
            editor.commit()
            status = 1

        }catch (exe : Exception){
            status = 0
        }

        return  status
    }

}