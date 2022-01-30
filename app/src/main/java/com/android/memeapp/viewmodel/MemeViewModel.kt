package com.android.memeapp.viewmodel

import androidx.lifecycle.*
import com.android.memeapp.constant.Fresh
import com.android.memeapp.constant.LoadType
import com.android.memeapp.constant.More
import com.android.memeapp.data.network.Loading
import com.android.memeapp.data.network.Resource
import com.android.memeapp.data.repository.IMemeRepository
import com.android.memeapp.db.entity.MemeObject
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemeViewModel
@Inject constructor(private val repository: IMemeRepository) : ViewModel() {

    private val _fetchMemeLiveData: MutableLiveData<Resource<ArrayList<MemeObject>>>
            by lazy { MutableLiveData() }

    val fetchMemeLiveData: LiveData<Resource<ArrayList<MemeObject>>>
        get() = _fetchMemeLiveData

    fun fetchMeme(loadType: LoadType) {
        viewModelScope.launch {
            _fetchMemeLiveData.value = Loading(loadType)
            if (loadType is More) {
                _fetchMemeLiveData.value = repository.fetchFromDB(
                    loadType.pageSize,
                    loadType.offset
                )
            } else {
                _fetchMemeLiveData.value = repository.fetchFromRemote()
            }
        }

    }
}