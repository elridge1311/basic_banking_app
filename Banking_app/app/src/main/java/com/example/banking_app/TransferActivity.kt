package com.example.banking_app

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class TransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        var senders_name = findViewById<AutoCompleteTextView>(R.id.senders_name)
        var receivers_name = findViewById<AutoCompleteTextView>(R.id.receivers_name)
        var image1 = findViewById<ImageView>(R.id.imageView5)
        var image2 = findViewById<ImageView>(R.id.imageView6)
        var amount = findViewById<EditText>(R.id.amount)
        var transfer = findViewById<Button>(R.id.transfer)

        var helper = DBHelper(applicationContext)
        var db = helper.readableDatabase

        var helpers = DBHelper2(applicationContext)
        var db1 = helper.readableDatabase

        var listAdapter: ArrayAdapter<String>? = null
        var senders_list = ArrayList<String>()
        var receivers_list = ArrayList<String>()

        var cursor : Cursor = helper.viewData()


        if(cursor.count == 0)
        {
            Toast.makeText(applicationContext, "NO DATA", Toast.LENGTH_SHORT).show()
        }
        else
        {
            while(cursor.moveToNext())
            {
                senders_list.add(cursor.getString(1))
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, senders_list)
            senders_name.setAdapter(adapter)
            receivers_name.setAdapter(adapter)

        }

        image1.setOnClickListener {
            senders_name.showDropDown()
        }
        image2.setOnClickListener {
            receivers_name.showDropDown()
        }

        transfer.setOnClickListener {
            try {
                helpers.insertDatatransaction(senders_name.text.toString(),receivers_name.text.toString(), amount.text.toString())
                helper.updateDatasenders(senders_name.text.toString(),amount.text.toString())
                helper.updateDatareceivers(receivers_name.text.toString(),amount.text.toString())
                Toast.makeText(applicationContext,"AMOUNT TRANSFERRED",Toast.LENGTH_SHORT).show()
                senders_name.setText("")
                receivers_name.setText("")
                amount.setText("")
                senders_name.requestFocus()
                val intent = Intent(this, OptionsActivity::class.java)
                startActivity(intent)
                finish()

            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }


    }
}