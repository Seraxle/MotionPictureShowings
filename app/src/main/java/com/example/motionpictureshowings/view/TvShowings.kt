package com.example.motionpictureshowings.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionpictureshowings.R
import com.example.motionpictureshowings.model.ShowingsAPI
import com.example.motionpictureshowings.databinding.TvShowingsBinding
import com.example.motionpictureshowings.model.TvItem
import com.example.motionpictureshowings.model.TvResults
import com.example.motionpictureshowings.viewmodel.ShowingViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvShowings : Fragment(), TvRecyclerAdapter.onTvClickListener {

    private var _binding: TvShowingsBinding? = null
    private val showingViewModel: ShowingViewModel by activityViewModels()
    private val baseUrl: String = "https://api.themoviedb.org/3/"

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = TvShowingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTvData()

        //HOW MANY BINDINGS?
        //binding.recyclerView.adapter = RecyclerAdapter(showingViewModel.getShowingItemList(), this)

        showingViewModel.getTvShowingItemList().observe(viewLifecycleOwner) {
            binding.tvRecyclerView.adapter = TvRecyclerAdapter(showingViewModel.getTvShowingItemList().value, this)
            binding.tvRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.tvRecyclerView.setHasFixedSize(true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        showingViewModel.destroy()
        _binding = null
    }

    private fun getTvData() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val showingsAPI = retrofit.create(ShowingsAPI::class.java)
        val tvCall: Call<TvItem> = showingsAPI.getTvDetails()

        tvCall.enqueue(object: Callback<TvItem> {
            override fun onResponse(
                call: Call<TvItem>,
                response: Response<TvItem>
            ) {
                if (!response.isSuccessful) {
                    Log.e("FAIL", "Retrofit Failure")
                    return
                }

                val tvList: TvItem? = response.body()
                val tvItemList: ArrayList<TvResults> = ArrayList()

                if (tvList != null) {
                    for (i in 0 until tvList.results.size) {
                        tvItemList.add(tvList.results[i])
                    }
                }
                //showingViewModel.setTvShowingItemList(tvItemList)
                response.body()?.let { showingViewModel.setTvShowingItemList(it.results) }
            }

            override fun onFailure(call: Call<TvItem>, t: Throwable) {
                t.message?.let{ Log.e("FAILURE", it) }
            }

        })
    }

    override fun onTvClickListener(position: Int) {
        var clickedTvShow = showingViewModel.getTvShowingItemList().value?.get(position)
        showingViewModel.setChosenTvShow(clickedTvShow)
        findNavController().navigate(R.id.action_tvShowings_to_showingDetails)
    }
}