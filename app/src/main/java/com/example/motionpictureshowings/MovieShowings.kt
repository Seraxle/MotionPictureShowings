package com.example.motionpictureshowings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.motionpictureshowings.databinding.MovieShowingsBinding

class MovieShowings : Fragment(), RecyclerAdapter.onShowingClickListener {

    private var _binding: MovieShowingsBinding? = null
    private val showingViewModel: ShowingViewModel by activityViewModels()

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

        //HOW MANY BINDINGS?
        //binding.recyclerView.adapter = RecyclerAdapter(showingViewModel.getShowingItemList(), this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}