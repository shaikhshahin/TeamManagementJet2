package com.shahin.teammanagement.data


import com.preference.PowerPreference
import com.preference.Preference
import com.shahin.teammanagement.App
import com.shahin.teammanagement.data.db.database.LogDatabase
import com.shahin.teammanagement.data.network.services.UserService

/**
 * Created by Shahin on 19/11/2019.
 */

class DataManager private constructor()// This class is not publicly instantiable
{

    val prefs: Preference
        get() = PowerPreference.defult()

    val logDatabse: LogDatabase?
        get() = App.getInstance()?.let { LogDatabase.getInstance(it) }

    val userService: UserService
        get() = UserService.getInstance()

    companion object {

        private var sInstance: DataManager? = null

        val instance: DataManager
            @Synchronized get() {
                if (sInstance == null) {
                    sInstance = DataManager()
                }
                return sInstance!!
            }
    }

}
