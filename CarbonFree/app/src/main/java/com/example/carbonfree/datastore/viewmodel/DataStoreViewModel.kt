package com.example.carbonfree.datastore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carbonfree.datastore.model.CarbonFreeDataStore
import com.example.carbonfree.datastore.repo.CarbonFreeDatastoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val carbonFreeDatastoreRepo: CarbonFreeDatastoreRepo): ViewModel() {

    var finished: MutableLiveData<Boolean> = MutableLiveData()

    var dataStoreValue: MutableLiveData<CarbonFreeDataStore> = MutableLiveData()

    ///// me
    fun showTheData(): LiveData<CarbonFreeDataStore> {
        return dataStoreValue
    }

    /////
    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            carbonFreeDatastoreRepo.saveDataStore(
                CarbonFreeDataStore(
                    finished = finished.value!!
                )
            )
        }
    }

    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            carbonFreeDatastoreRepo.getDataStore().collect {
                dataStoreValue.postValue(it)
            }
        }
    }
}