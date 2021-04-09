package com.example.banking_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)


        val insertpage = findViewById<CardView>(R.id.insert_page)
        val detailspage = findViewById<CardView>(R.id.details_page)
        val updatepage = findViewById<CardView>(R.id.update_page)
        val transferpage = findViewById<CardView>(R.id.transfer_page)
        val transactionpage = findViewById<CardView>(R.id.transaction_page)

        insertpage.setOnClickListener{
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
            finish()

        }
        detailspage.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
            finish()

        }
        updatepage.setOnClickListener{
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
            finish()

        }
        transferpage.setOnClickListener{
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
            finish()

        }
        transactionpage.setOnClickListener{
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
            finish()


        }

    }
}