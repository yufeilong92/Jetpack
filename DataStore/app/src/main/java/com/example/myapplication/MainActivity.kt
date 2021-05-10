package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btn_save.setOnClickListener {
            GlobalScope.async {
                DataStoreUtils.writaDataStore(this@MainActivity, getData())
            }
        }
        btn_read.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                readStore()
            }
        }
    }

    private suspend fun readStore() {
        val readDataStore = DataStoreUtils.readDataStore(this)
        readDataStore.collect {
            tv_show.text = "$it"
        }
    }

    private fun getData(): String {
        val msg = et_input.text.toString()
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(this, "输入内容为空", Toast.LENGTH_SHORT).show()
        }
        return msg
    }
}