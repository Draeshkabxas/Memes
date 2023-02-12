package com.example.memes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memes.model.Meme
import com.example.memes.model.MemeApi
import com.example.memes.utlis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val memeApi: MemeApi):ViewModel() {
    private val _memes = MutableLiveData<Resource<List<Meme>>>()
    val meme: LiveData<Resource<List<Meme>>> get() = _memes

    init{
        getMeme()
    }
    fun getMeme() {
        viewModelScope.launch {
            _memes.value = memeApi.getMemes()
        }
    }
}