package com.shahin.teammanagement.data.db.database

import android.content.Context
import androidx.room.Room

/**
 */


class DatabaseClient private constructor(private var mCtx: Context) {

    //our app database object
    val appDatabase: UserDatabase

    init {
this.mCtx=mCtx
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, UserDatabase::class.java!!, "UserDB").build()
    }

    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance as DatabaseClient
        }
    }
}