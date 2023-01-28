package com.example.motionpictureshowings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionpictureshowings.databinding.MovieShowingsBinding
import com.example.motionpictureshowings.model.MovieItem
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
        getMovieData()

        //HOW MANY BINDINGS?
        binding.movieRecyclerView.adapter = MovieRecyclerAdapter(showingViewModel.getMovieShowingItemList(), this)
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.movieRecyclerView.setHasFixedSize(true)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMovieData() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val showingsAPI = retrofit.create(ShowingsAPI::class.java)
        val movieCall: Call<ArrayList<MovieItem>> = showingsAPI.getMovieDetails()

        movieCall.enqueue(object: Callback<ArrayList<MovieItem>> {
            override fun onResponse(
                call: Call<ArrayList<MovieItem>>,
                response: Response<ArrayList<MovieItem>>
            ) {
                if (!response.isSuccessful) {
                    Log.e("FAIL", "Retrofit Failure")
                    return
                }

                val movieList: ArrayList<MovieItem>? = response.body()
                val movieItemList: ArrayList<MovieItem> = ArrayList()

                if (movieList != null) {
                    for (i in 0 until movieList.size) {
                        movieItemList.add(movieList[i])
                    }
                }
                showingViewModel.setMovieShowingItemList(movieItemList)
            }

            override fun onFailure(call: Call<ArrayList<MovieItem>>, t: Throwable) {
                t.message?.let{ Log.e("FAILURE", it) }
            }

        })
    }
}