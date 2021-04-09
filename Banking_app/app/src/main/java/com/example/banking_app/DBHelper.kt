package com.example.banking_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context, "BANK", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUSTOMERS(ACC_NO INTEGER, NAME TEXT, EMAIL TEXT, AMOUNT INTEGER)")

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(accno: String, name:String, email:String, amount:String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ACC_NO", accno)
        cv.put("NAME", name)
        cv.put("EMAIL", email)
        cv.put("AMOUNT", amount)
        db.insert("CUSTOMERS", null, cv)

    }


    fun updateData(accno: String, name:String, email:String, amount:String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ACC_NO", accno)
        cv.put("NAME", name)
        cv.put("EMAIL", email)
        cv.put("AMOUNT", amount)
        db.update("CUSTOMERS", cv, "ACC_NO = ?", arrayOf(accno))
        //db.close()
        return true
    }

   fun viewData(): Cursor {
       var db : SQLiteDatabase = this.readableDatabase
       var query : String = " SELECT * FROM CUSTOMERS"
       var cursor : Cursor = db.rawQuery(query,null)
      // db.close()
       return cursor
   }


    fun updateDatasenders(name:String, amount:String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        var amount : Int = amount.toInt()
        var amount1 : Int ?= getuserDetails(name)
        if (amount1 != null) {
            amount1 -= amount
        }
        cv.put("NAME", name)
        cv.put("AMOUNT", amount1)
        db.update("CUSTOMERS", cv, "NAME = ?", arrayOf(name))
        //db.close()
        return true
    }

    fun updateDatareceivers(name:String, amount:String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        var amount : Int = amount.toInt()
        var amount1 : Int ?= getuserDetails(name)
        if (amount1 != null) {
            amount1 += amount
        }
        cv.put("NAME", name)
        cv.put("AMOUNT", amount1)
        db.update("CUSTOMERS", cv, "NAME = ?", arrayOf(name))
       // db.close()
        return true
    }

    private fun getuserDetails(name: String) : Int? {
        val db = this.writableDatabase
        var names = ArrayList<String>()
        var amounts =  ArrayList<String>()
        var query : String = " SELECT * FROM CUSTOMERS"
        val cursor = db.rawQuery(query, null)
        while(cursor.moveToNext())
        {
            names.add(cursor.getString(1))
            amounts.add(cursor.getString(3))
        }
        var indexing : Int = names.indexOf(name)
        var amount : String = amounts.get(indexing)
        return amount.toInt()
    }

}