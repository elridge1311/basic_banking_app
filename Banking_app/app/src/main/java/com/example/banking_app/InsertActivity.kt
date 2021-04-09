package com.example.banking_app

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val accno = findViewById<EditText>(R.id.acc_no)
        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val amount = findViewById<EditText>(R.id.amount)
        val insert = findViewById<Button>(R.id.insert)
        val back = findViewById<Button>(R.id.back)

        back.setOnClickListener{
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
            finish()

        }

        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM CUSTOMERS",null)


        insert.setOnClickListener {
            try {
                helper.insertData(accno.text.toString().toUpperCase(),name.text.toString().toUpperCase(), email.text.toString().toUpperCase(),amount.text.toString())
                Toast.makeText(applicationContext,"DATA SAVED",Toast.LENGTH_SHORT).show()
                accno.setText("")
                name.setText("")
                email.setText("")
                amount.setText("")
                accno.requestFocus()
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}