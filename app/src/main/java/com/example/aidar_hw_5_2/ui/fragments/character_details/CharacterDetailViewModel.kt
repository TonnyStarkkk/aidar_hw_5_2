package com.example.aidar_hw_5_2.ui.fragments.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aidar_hw_5_2.data.model.characters.Character
import com.example.aidar_hw_5_2.data.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailViewModel : ViewModel() {

    private val api = RetrofitInstance.api

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> get() = _character

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCharacterById(id: Int) {
        api.getSingleCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _character.postValue(it)
                    }
                } else {
                    _error.postValue("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Character>, thr: Throwable) {
                _error.postValue(thr.localizedMessage ?: "Unknown error")
            }
        })
    }
}