package com.example.dynamicmodule

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DynamicMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @SuppressWarnings("unused")
    fun add(a:Int,b:Int):Int{
        return a+b
    }

    fun toast(context: Context){
        Toast.makeText(context,"Test Toast",Toast.LENGTH_SHORT).show()
    }
}