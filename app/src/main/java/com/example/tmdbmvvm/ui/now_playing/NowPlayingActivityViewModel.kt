package com.example.tmdbmvvm.ui.now_playing

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.tmdbmvvm.data.repositories.NetworkState
import com.example.tmdbmvvm.data.domains.Movie
import io.reactivex.disposables.CompositeDisposable

class NowPlayingActivityViewModel(private val movieRepository : MoviePagedListRepository, ctx: Context) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  moviePagedList : LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable, ctx)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}