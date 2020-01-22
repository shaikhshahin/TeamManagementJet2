package com.shahin.teammanagement.ui.activity.details


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.bumptech.glide.Glide

import butterknife.BindView
import butterknife.ButterKnife
import com.shahin.teammanagement.R
import com.shahin.teammanagement.data.network.model.UserData

/**
 * Created by Shahin on 22/01/2020.
 */
class DetailsActivity : AppCompatActivity() {

    internal var image: AppCompatImageView? = null
    internal var title: TextView? = null
    internal var desc: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        image = findViewById(R.id.image) as AppCompatImageView
        title = findViewById(R.id.title) as TextView
        desc = findViewById(R.id.desc) as TextView

        val viewModel = createViewModel()

       // viewModel.movie.observe(this, MovieObserver())

        viewModel.loadMovieData(intent)
    }

    private fun createViewModel(): DetailsViewModel {
        return ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }


    private inner class MovieObserver : Observer<UserData> {
        override fun onChanged(userData: UserData?) {
            if (userData == null) return

            title?.text = userData.title
            desc?.text = userData.description
            image?.let { Glide.with(applicationContext).load(userData.image).into(it) }
        }
    }

}

