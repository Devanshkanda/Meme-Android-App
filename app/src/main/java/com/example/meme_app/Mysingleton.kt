package com.example.meme_app

import android.app.Application
import android.content.Context
import android.os.strictmode.InstanceCountViolation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import java.security.AccessControlContext

class Mysingleton constructor(context: Context){
/*
    companion object{
        @Volatile
        private var INSTANCE: Mysingleton? = null
        fun getinstance(context: Context) =
            INSTANCE ?: synchronized(this){
                INSTANCE ?:Mysingleton(context).also { INSTANCE = it }
            }
    }
}

private val requestQueue: RequestQueue by lazy {

    Volley.newRequestQueue(context.applicationcontext)
}

fun <T> addtoRequestQueue(req: Request<T>){
    requestQueue.add(req)
}
*/
