package com.example.banking_app

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val lview = findViewById<ListView>(R.id.listView1)
        val transfer = findViewById<Button>(R.id.transfer_money)
        val back = findViewById<Button>(R.id.back_options)

        var helpers = DBHelper2(applicationContext)
        var db = helpers.readableDatabase
        var listAdapter: ArrayAdapter<String>? = null

        var cursor: Cursor = helpers.viewDatatransaction()

        if (cursor.count == 0) {
            Toast.makeText(applicationContext, "NO DATA", Toast.LENGTH_SHORT).show()
        } else {
            var buffer = StringBuilder()

            while (cursor.moveToNext()) {
                buffer.append("SENDERS NAME : " + cursor.getString(0) + System.getProperty("line.separator"))
                buffer.append("RECEIVERS NAME : " + cursor.getString(1) + System.getProperty("line.separator"))
                buffer.append("AMOUNT : " + cursor.getString(2) + System.getProperty("line.separator"))
                buffer.append(",")
            }
            val convert_str: String = buffer.toString()
            var result: ArrayList<String> = convert_str.split(",") as ArrayList<String>

            val adapter = ArrayAdapter(this, R.layout.styles, result)
            lview.adapter = adapter



            back.setOnClickListener {
                val intent = Intent(this, OptionsActivity::class.java)
                startActivity(intent)
                finish()

            }
            transfer.setOnClickListener {
                val intent = Intent(this, TransferActivity::class.java)
                startActivity(intent)
                finish()

            }


        }
    }
}