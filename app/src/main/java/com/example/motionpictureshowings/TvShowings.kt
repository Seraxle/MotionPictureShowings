package com.example.motionpictureshowings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionpictureshowings.databinding.TvShowingsBinding
import com.example.motionpictureshowings.model.TvItem
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

        binding.tvRecyclerView.adapter = TvRecyclerAdapter(showingViewModel.getTvShowingItemList(), this)
        binding.tvRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.tvRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTvData() {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()
        val showingsAPI = retrofit.create(ShowingsAPI::class.java)
        val tvCall: Call<ArrayList<TvItem>> = showingsAPI.getTvDetails()

        tvCall.enqueue(object: Callback<ArrayList<TvItem>> {
            override fun onResponse(
                call: Call<ArrayList<TvItem>>,
                response: Response<ArrayList<TvItem>>
            ) {
                if (!response.isSuccessful) {
                    Log.e("FAIL", "Retrofit Failure")
                    return
                }

                val tvList: ArrayList<TvItem>? = response.body()
                val tvItemList: ArrayList<TvItem> = ArrayList()

                if (tvList != null) {
                    for (i in 0 until tvList.size) {
                        tvItemList.add(tvList[i])
                    }
                }
                showingViewModel.setTvShowingItemList(tvItemList)
            }

            override fun onFailure(call: Call<ArrayList<TvItem>>, t: Throwable) {
                t.message?.let{ Log.e("FAILURE", it) }
            }

        })
    }
}