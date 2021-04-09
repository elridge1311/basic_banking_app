package com.example.banking_app

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val accno = findViewById<EditText>(R.id.acc_no_update)
        val name = findViewById<EditText>(R.id.name_update)
        val email = findViewById<EditText>(R.id.email_update)
        val amount = findViewById<EditText>(R.id.amount_update)
        val update = findViewById<Button>(R.id.update)
        val back = findViewById<Button>(R.id.back_update)

        back.setOnClickListener{
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
            finish()

        }

        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM CUSTOMERS",null)

        update.setOnClickListener{
            try {
                val isUpdate = helper.updateData(accno.text.toString().toUpperCase(), name.text.toString().toUpperCase(), email.text.toString().toUpperCase(), amount.text.toString())
                if (isUpdate == true){
                    Toast.makeText(applicationContext,"DATA UPDATED",Toast.LENGTH_SHORT).show()
                    accno.setText("")
                    name.setText("")
                    email.setText("")
                    amount.setText("")
                    accno.requestFocus()
                }
                else
                    Toast.makeText(applicationContext,"DATA NOT UPDATED",Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}