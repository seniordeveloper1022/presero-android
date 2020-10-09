package com.rapidzz.presero.Utils.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rapidzz.presero.Models.Source.Repository.DataRepository
import com.rapidzz.presero.ViewModels.*

class ViewModelFactory private constructor(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(dataRepository)
                isAssignableFrom(BaseAndroidViewModel::class.java) ->
                    BaseAndroidViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                    INSTANCE
                        ?: ViewModelFactory(
                            DataRepository(
                                application.applicationContext
                            )
                        )
                            .also { INSTANCE = it }
                }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
