package com.shahin.teammanagement.data.network.services


import com.shahin.teamamanagement.data.network.model.UserResponse
import com.shahin.teammanagement.utils.AppConstants

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Shahin on 19/11/2019.
 */
class UserService private constructor() {

    val userApi: UserApi

    init {
        val mRetrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL)
                .build()
        userApi = mRetrofit.create(UserApi::class.java)
    }

    interface UserApi {
        @get:GET("facts.json")
        val allUser: Call<UserResponse>
    }

    companion object {

        private val URL = AppConstants.BASE_URL

        private var instance: UserService? = null


        fun getInstance(): UserService {
            if (instance == null) {
                instance = UserService()
            }
            return instance as UserService
        }
    }

}


