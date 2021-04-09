package com.example.banking_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper2(context : Context) : SQLiteOpenHelper(context, "TRANSACTION", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TRANSACTION_HISTORY(SENDERS_NAME TEXT, RECEIVERS_NAME TEXT, AMOUNT_TRANSFERRED INTEGER)")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun insertDatatransaction(sendersname:String, receiversname:String, amounttransfered:String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("SENDERS_NAME", sendersname)
        cv.put("RECEIVERS_NAME", receiversname)
        cv.put("AMOUNT_TRANSFERRED", amounttransfered)
        db.insert("TRANSACTION_HISTORY", null, cv)
        //db.close()
    }

    fun viewDatatransaction(): Cursor {
        var db : SQLiteDatabase = this.readableDatabase
        var query : String = " SELECT * FROM TRANSACTION_HISTORY"
        var cursor : Cursor = db.rawQuery(query,null)
        // db.close()
        return cursor
    }


}