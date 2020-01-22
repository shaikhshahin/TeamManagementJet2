package com.shahin.teammanagement.data.db.database

import android.content.Context

import androidx.annotation.WorkerThread
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shahin.teammanagement.data.LogDAO
import com.shahin.teammanagement.data.db.entity.LogClass


/**
 */

@Database(entities = [LogClass::class], version = 2, exportSchema = false)
abstract class LogDatabase : RoomDatabase() {

    @WorkerThread

    abstract fun logDao(): LogDAO

    companion object {

        private var sInstance: LogDatabase? = null

        private fun initialize(context: Context): LogDatabase {
            sInstance = Room.databaseBuilder(
                context.applicationContext,
                LogDatabase::class.java,
                "log-database"
            ).fallbackToDestructiveMigration().build()
            return sInstance as LogDatabase
        }

        fun getInstance(context: Context): LogDatabase? {
            return if (sInstance == null) {
                initialize(context)
            } else {
                sInstance
            }
        }

        fun destroyInstance() {
            sInstance = null
        }

        fun addLog(db: LogDatabase, log: LogClass) {
            val thread = object : Thread() {
                override fun run() {
                    super.run()
                    db.logDao().insertAll(log)

                }
            }
            thread.start()
        }


        fun dropTable(db: LogDatabase) {
            db.logDao().dropTable()
        }
    }
}
