package com.c0ffee.fastshopping

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ListDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
                "CREATE TABLE Lists ( \n" +
                    "__id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "name VARCHAR(127) NOT NULL UNIQUE\n" +
                    ");")
        db.execSQL(
                "CREATE TABLE Lists_Contents (\n" +
                    "__id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "list_fk INTEGER NOT NULL,\n" +
                    "name VARCHAR(127),\n" +
                    "CONSTRAINT fk_list FOREIGN KEY(list_fk) REFERENCES Lists(__id)\n" +
                    ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //db.execSQL("DROP TABLE Lists_Contents; DROP TABLE Lists;")
        onCreate(db)
    }

    fun onUpdate(newLists: ArrayList < ShoppingList>) {
        if (newLists.size < 1) return
        val db = readableDatabase
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

    fun read(): ArrayList<ShoppingList> {
        val db = readableDatabase
        val lists = db.rawQuery("SELECT name, __id FROM LISTS", null)
        val l: ArrayList<ShoppingList> = arrayListOf()
        var name = ""
        var id = 0
        var product = ""
        if (lists.count > 0) {
            lists.moveToFirst()
            do {
                name = lists.getString(lists.getColumnIndex("name"))
                id = lists.getColumnIndex("__id")
                val shop = ShoppingList(name)
                val c = db.rawQuery("SELECT name FROM Lists_Contents WHERE list_fk = " + id + ";", null)
                if (c.count > 0) {
                    c.moveToFirst()
                    do {
                        product = c.getString(c.getColumnIndex("name"))
                        shop.items.add(product)
                    }while (c.moveToNext())
                }
                c.close()
                l.add(shop)
            } while (lists.moveToNext())

            lists.close()
        }
        lists.close()
        return l
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ShoppingLists.db"
    }
}