package com.shahin.teammanagement.ui.fragment



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.shahin.teammanagement.data.DataManager
import com.shahin.teammanagement.ui.activity.main.MainViewModelFactory
import com.shahin.teammanagement.R
import com.shahin.teammanagement.data.network.model.UserData
import com.shahin.teammanagement.ui.activity.details.DetailsActivity
import com.shahin.teammanagement.ui.activity.main.MainViewModel
import com.shahin.teammanagement.ui.activity.main.UserAdapter
import com.shahin.teammanagement.utils.NetworkUtils


/**
 * Created by Shahin on 22/01/2020.
 */

 class HomeFragment : Fragment(), UserAdapter.OnMovieAdapter {



    internal lateinit var userAdapter: UserAdapter

    internal var recyclerView: RecyclerView? = null

    @BindView(R.id.progress_bar)
    internal var progressBar: ProgressBar? = null

    @BindView(R.id.empty_view)
    internal var emptyView: TextView? = null

    internal lateinit var viewModel: MainViewModel


    private fun createViewModel(): MainViewModel {
        val factory = MainViewModelFactory(DataManager.instance.userService)
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)

        //activity?.let { ButterKnife.bind(it) }


        progressBar = view.findViewById(R.id.progress_bar)
        emptyView = view.findViewById(R.id.empty_view)

        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView



        recyclerView?.layoutManager = LinearLayoutManager(activity)


        viewModel = createViewModel()

        activity?.let { viewModel.loadingStatus.observe(it, LoadingObserver()) }
        activity?.let { viewModel.userDatas.observe(it, UserObserver()) }

        var networkStatus : Boolean = activity?.let { NetworkUtils.isNetworkConnected(it) }!!

        if(networkStatus==true)
        {
            viewModel.loadUserDatasNetwork()
        }
        else {
            viewModel.loadUserDataLocal()
        }
        //viewModel.loadUserDatasNetwork()


        userAdapter = UserAdapter(this)

        recyclerView?.adapter = userAdapter




        return  view
    }



     override fun onMovieClicked(userData: UserData) {

        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("user", userData)
        startActivity(intent)

    }

     fun onPointerCaptureChanged(hasCapture: Boolean) {

    }

    //Observer
    private inner class LoadingObserver : Observer<Boolean> {

        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) return

            if (isLoading) {
                progressBar?.visibility = View.VISIBLE
            } else {
                progressBar?.visibility = View.GONE
            }
        }
    }

    private inner class UserObserver : Observer<List<UserData>> {

        override fun onChanged(userData: List<UserData>?) {
            if (userData == null) return
            userAdapter.setItems(userData)

            if (userData.isEmpty()) {
                emptyView?.visibility = View.VISIBLE
            } else {
                emptyView?.visibility = View.GONE
            }
        }
    }
}
