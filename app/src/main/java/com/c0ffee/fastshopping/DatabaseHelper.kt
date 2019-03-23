package com.c0ffee.fastshopping

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream



class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val db: SQLiteDatabase

    init {
        val input = context.assets.open(DATABASE_NAME)

        File(DATABASE_PATH).mkdirs()

        val destPath = DATABASE_PATH + DATABASE_NAME
        val output = FileOutputStream(destPath)
        val buffer = ByteArray(1024)
        while (true) {
            val len = input.read(buffer)
            if (len == -1) {
                break
            }
            output.write(buffer, 0, len)
        }
        output.flush()
        output.close()
        input.close()

        db = SQLiteDatabase.openDatabase(destPath, null, SQLiteDatabase.OPEN_READONLY)
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    override fun close() {
        db.close()
        super.close()
    }

    private companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_PATH = "/data/data/com.c0ffee.fastshopping/databases/"
        const val DATABASE_VERSION = 1
    }

    fun selectList (name: String) : MutableList<Pair<String, Int> >  {
        val q = "SELECT __id, DISTINCT name FROM " + name + " ORDER BY name"
        val c =  db.rawQuery( q, null)
        val l: MutableList <Pair<String, Int> > = arrayListOf()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                l.add(Pair(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("__id"))))
            } while (c.moveToNext())
            c.close()
        }
        return l
    }

    fun findShops (product_id: Int) : MutableList <Pair<String, Int> > {
        val q = ""
        val c =  db.rawQuery( q, null)
        val l: MutableList <Pair<String, Int> > = arrayListOf()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                l.add(Pair(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("__id"))))
            } while (c.moveToNext())
            c.close()
        }
        return l
    }
}
