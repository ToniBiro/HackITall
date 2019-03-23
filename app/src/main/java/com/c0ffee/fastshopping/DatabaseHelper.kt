package com.c0ffee.fastshopping

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val db: SQLiteDatabase

    init {
        val input = context.assets.open(DATABASE_NAME)
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
        const val DATABASE_PATH = "/data/data/com/c0ffee/fastshipping/databases/"
        const val DATABASE_VERSION = 1
    }
}
