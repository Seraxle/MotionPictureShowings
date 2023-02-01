package com.example.motionpictureshowings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.motionpictureshowings.databinding.ShowingDetailsBinding
import com.example.motionpictureshowings.model.MovieResults
import com.example.motionpictureshowings.model.TvResults
import com.example.motionpictureshowings.viewmodel.ShowingViewModel
import com.squareup.picasso.Picasso
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
        val url = StringBuilder("https://image.tmdb.org/t/p/w500")

        if (showingViewModel.isMovieResults()) {
            var movie: MovieResults? = showingViewModel.getChosenMovie()

            var movieImage = view.chosenShowingImage
            url.append(movie?.backdrop_path)
            Picasso.get().load(url.toString()).into(movieImage)

            var movieName = view.chosenShowingName
            movieName.text = movie?.title

            var movieScore = view.chosenShowingScore
            val score = StringBuilder(movie?.vote_average.toString())
            score.append("/10")
            movieScore.text = score.toString()

            var movieReviews = view.chosenShowingReviews
            val reviews = StringBuilder(movie?.vote_count.toString())
            reviews.append(" Reviews")
            movieReviews.text = reviews.toString()

            var movieOverview = view.chosenShowingOverview
            movieOverview.text = movie?.overview

        }
        else if (showingViewModel.isTvShowResults()) {
            var tvShow: TvResults? = showingViewModel.getChosenTvShow()

            var tvImage = view.chosenShowingImage
            url.append(tvShow?.backdrop_path)
            Picasso.get().load(url.toString()).into(tvImage)

            var tvName = view.chosenShowingName
            tvName.text = tvShow?.name

            var tvScore = view.chosenShowingScore
            val score = StringBuilder(tvShow?.vote_average.toString())
            score.append("/10")
            tvScore.text = score.toString()

            var tvReviews = view.chosenShowingReviews
            val reviews = StringBuilder(tvShow?.vote_count.toString())
            reviews.append(" Reviews")
            tvReviews.text = reviews.toString()

            var tvOverview = view.chosenShowingOverview
            tvOverview.text = tvShow?.overview

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}