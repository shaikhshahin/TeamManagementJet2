package com.shahin.teammanagement.ui.activity.details

import android.content.Intent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.teammanagement.data.network.model.UserData


/**
 * Created by Shahin on 22/01/2020.
 */
class DetailsViewModel : ViewModel() {

    var movie: MutableLiveData<UserData>
        internal set

    init {
        this.movie = MutableLiveData()
    }

    fun loadMovieData(intent: Intent) {
        assert(intent.extras != null)
        val movieExtra = intent.extras!!.getParcelable<UserData>("user")

        movie.postValue(movieExtra)
    }
}
