package com.example.banking_app


import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val lview = findViewById<ListView>(R.id.listView)
        val transfer = findViewById<Button>(R.id.transfer)
        val back = findViewById<Button>(R.id.back_details)

        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var listAdapter: ArrayAdapter<String>? = null

        var cursor: Cursor = helper.viewData()

        if (cursor.count == 0) {
            Toast.makeText(applicationContext, "NO DATA", Toast.LENGTH_SHORT).show()
        } else {
            var buffer = StringBuilder()

            while (cursor.moveToNext()) {
                buffer.append("ACC NO : " + cursor.getString(0) + System.getProperty("line.separator"))
                buffer.append("NAME : " + cursor.getString(1) + System.getProperty("line.separator"))
                buffer.append("EMAIL : " + cursor.getString(2) + System.getProperty("line.separator"))
                buffer.append("AMOUNT : " + cursor.getString(3) + System.getProperty("line.separator"))
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


