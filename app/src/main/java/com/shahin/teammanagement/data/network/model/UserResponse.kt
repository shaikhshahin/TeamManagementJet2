package com.shahin.teamamanagement.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shahin.teammanagement.data.network.model.UserData

/**
 * Created by Shahin on 19/11/2019.
 */

class UserResponse {

    @Expose
    @SerializedName("results")
    val userData: List<UserData>? = null
}
