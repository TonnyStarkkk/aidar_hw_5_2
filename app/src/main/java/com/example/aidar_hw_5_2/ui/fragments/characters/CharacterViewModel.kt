package com.example.aidar_hw_5_2.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.aidar_hw_5_2.data.api.ApiService
import com.example.aidar_hw_5_2.data.model.characters.Character
import com.example.aidar_hw_5_2.data.paging.CharactersPagingSource
import com.example.aidar_hw_5_2.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    fun getAllCharacters(): LiveData<Resource<PagingData<Character>>> {
        return liveData {
            emit(Resource.Loading())
            try {
                val pager = Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 15,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { CharactersPagingSource(api) }
                ).liveData

                emitSource(pager.map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Error("Failed to load: ${e.message}"))
            }
        }
    }
}