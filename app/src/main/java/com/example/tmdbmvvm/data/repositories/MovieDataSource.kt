package com.example.tmdbmvvm.data.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.tmdbmvvm.data.api.FIRST_PAGE
import com.example.tmdbmvvm.data.api.TheMovieDBInterface
import com.example.tmdbmvvm.data.domains.Movie
import com.example.tmdbmvvm.preference.PreferenceHelper
import com.example.tmdbmvvm.room.Helper.RoomMovieHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable, private val  ctx: Context)
    : PageKeyedDataSource<Int, Movie>(){

    private var page = FIRST_PAGE

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    lateinit var preferenceHelper : PreferenceHelper
    lateinit var roomHelper : RoomMovieHelper

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {

        Thread {

        networkState.postValue(NetworkState.LOADING)

         preferenceHelper = PreferenceHelper(ctx)
         roomHelper = RoomMovieHelper(ctx)

        if(preferenceHelper.getOfflineCount() > 0){
            var movies : List<Movie> = roomHelper.getMovieListByIndexValue(0)
            callback.onResult(movies, null, page+1)
            networkState.postValue(NetworkState.LOADED)

        }else{

            compositeDisposable.add(
                apiService.getPopularMovie(page)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {

                            callback.onResult(it.movieList, null, page + 1)
                            Log.i("ffff before","initial insert :"+preferenceHelper.getOfflineCount().toString())
                            roomHelper.insertMovies(it.movieList)

                            networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDataSource", it.message.toString())
                        }
                    )
            )
        }
        }.start()


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        Thread {
            networkState.postValue(NetworkState.LOADING)


            if(preferenceHelper.getOfflineCount() > params.key){
                var movies : List<Movie> = roomHelper.getMovieListByIndexValue(params.key)
                callback.onResult(movies,  params.key+1)
                networkState.postValue(NetworkState.LOADED)

            }else {
                compositeDisposable.add(
                    apiService.getPopularMovie(params.key)
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {

                                if (it.totalPages >= params.key) {
                                    callback.onResult(it.movieList, params.key + 1)
                                    roomHelper.insertMovies(it.movieList)
                                    networkState.postValue(NetworkState.LOADED)



                                } else {
                                    networkState.postValue(NetworkState.ENDOFLIST)
                                }
                            },
                            {
                                networkState.postValue(NetworkState.ERROR)
                                Log.e("MovieDataSource", it.message.toString())
                            }
                        )
                )
            }
        }.start()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}