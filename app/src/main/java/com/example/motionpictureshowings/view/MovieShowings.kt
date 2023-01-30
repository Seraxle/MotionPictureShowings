package com.example.motionpictureshowings.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionpictureshowings.R
import com.example.motionpictureshowings.model.ShowingsAPI
import com.example.motionpictureshowings.databinding.MovieShowingsBinding
import com.example.motionpictureshowings.model.MovieItem
import com.example.motionpictureshowings.model.MovieResults
import com.example.motionpictureshowings.viewmodel.ShowingViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieShowings : Fragment(), MovieRecyclerAdapter.onMovieClickListener {

    private var _binding: MovieShowingsBinding? = null
    private val showingViewModel: ShowingViewModel by activityViewModels()
    private val baseUrl: String = "https://api.themoviedb.org/3/"

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MovieShowingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val appBarConfiguration = AppBarConfiguration(setOf(R.id.movieShowings, R.id.tvShowings))
        binding.bottomNavigationView.setupWithNavController(findNavController(), appBarConfiguration)*/

        getMovieData()

        //HOW MANY BINDINGS?
        showingViewModel.getMovieShowingItemList().observe(viewLifecycleOwner) {
            binding.movieRecyclerView.adapter = MovieRecyclerAdapter(showingViewModel.getMovieShowingItemList().value, this)
            binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.movieRecyclerView.setHasFixedSize(true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        showingViewModel.destroy()
        _binding = null
    }

    private fun getMovieData() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val showingsAPI = retrofit.create(ShowingsAPI::class.java)
        val movieCall: Call<MovieItem> = showingsAPI.getMovieDetails()

        movieCall.enqueue(object: Callback<MovieItem> {
            override fun onResponse(
                call: Call<MovieItem>,
                response: Response<MovieItem>
            ) {
                if (!response.isSuccessful) {
                    Log.e("FAIL", "Retrofit Failure")
                    return
                }

                val movieList: MovieItem? = response.body()
                val movieItemList: ArrayList<MovieResults> = ArrayList()

                if (movieList != null) {
                    for (i in 0 until movieList.results.size) {
                        movieItemList.add(movieList.results[i])
                    }
                }
                //showingViewModel.setMovieShowingItemList(movieItemList)
                response.body()?.let { showingViewModel.setMovieShowingItemList(it.results) }
            }

            override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                t.message?.let{ Log.e("FAILURE", it) }
            }

        })
    }

    override fun onMovieClickListener(position: Int) {
        var clickedMovie = showingViewModel.getMovieShowingItemList().value?.get(position)
        showingViewModel.setChosenMovie(clickedMovie)
        findNavController().navigate(R.id.action_movieShowings_to_showingDetails)
    }
}