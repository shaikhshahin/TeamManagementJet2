package com.shahin.teammanagement.ui.activity.main


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shahin.teammanagement.data.network.services.UserService


/**
 * Created by Shahin on 19/11/2019.
 */
class MainViewModelFactory(private val userService: UserService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userService) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
