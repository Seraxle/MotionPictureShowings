package com.example.motionpictureshowings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.motionpictureshowings.databinding.ShowingDetailsBinding
import com.example.motionpictureshowings.model.MovieResults
import com.example.motionpictureshowings.model.TvResults
import com.example.motionpictureshowings.viewmodel.ShowingViewModel
import kotlinx.android.synthetic.main.showing_details.view.*

class ShowingDetails : Fragment() {

    private var _binding: ShowingDetailsBinding? = null
    private val showingViewModel: ShowingViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = ShowingDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val showingImage = view.chosenShowingImage

        if (showingViewModel.isMovieResults()) {
            var movie: MovieResults? = showingViewModel.getChosenMovie()
            var movieName = view.chosenShowingName
            movieName.text = showingViewModel.getChosenMovie()?.title
        }
        else if (showingViewModel.isTvShowResults()) {
            var tvShow: TvResults? = showingViewModel.getChosenTvShow()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}