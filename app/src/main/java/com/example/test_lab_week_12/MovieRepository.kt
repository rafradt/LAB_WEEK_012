package com.example.test_lab_week_12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "5f2a6337319819e2fe92773701c79f4d"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            val popularMovies = movieService.getPopularMovies(apiKey)
            movieLiveData.postValue(popularMovies.results)
        } catch (e: Exception) {
            errorLiveData.postValue("An error occurred: ${e.message}")
        }
    }
}
