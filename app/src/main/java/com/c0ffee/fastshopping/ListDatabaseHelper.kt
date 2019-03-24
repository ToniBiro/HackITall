package com.c0ffee.fastshopping

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ListDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE DATABASE IF NOT EXISTS ShoppingLists;" +
                "CREATE TABLE \"Lists\" ( \n" +
                "\t\"__id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"name\" VARCHAR(127) NOT NULL UNIQUE,\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE \"Lists_Contents\" ( \n" +
                "\t\"__id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"list_fk\" INTEGER NOT NULL,\n" +
                "\t\"name\" VARCHAR(127),\n" +
                "\tCONSTRAINT \"fk_list\" FOREIGN KEY(\"list_fk\") REFERENCES \"Lists\"(\"__id\")\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS \"Lists_Contents\";DROP TABLE IF EXISTS \"Lists\";")
        onCreate(db)
    }

    fun onUpdate(db: SQLiteDatabase, newLists: ArrayList<ShoppingList>) {
        if (newLists.size < 1) return
        val oldLists = db.rawQuery("SELECT name FROM LISTS", null)
        val newListsNames: ArrayList < String> = arrayListOf()
        val oldListsNames: ArrayList < String> = arrayListOf()
        var name = ""
        for (i in newLists) {
            newListsNames.add(i.title)
        }
        if (oldLists.count > 0) {
            oldLists.moveToFirst()
            do {
                name = oldLists.getString(oldLists.getColumnIndex("name"))
                oldListsNames.add(name)
                if(newListsNames.indexOf(name) < 0) db.execSQL("DELETE FROM Lists WHERE name = " + name + "\";")
            } while (oldLists.moveToNext())
            oldLists.close()
        }
        for (i in newListsNames) {
            if (oldListsNames.indexOf(i) < 0) db.execSQL("INSERT INTO Lists (\"name\") VALUES (\"" + i + "\");")
        }
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ShoppingLists.db"
    }
}