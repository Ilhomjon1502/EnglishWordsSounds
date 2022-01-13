package com.x.a_technologies.dictionary.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.x.a_technologies.dictionary.models.MyWord
import com.x.a_technologies.dictionary.retrofit.ApiClient
import com.x.a_technologies.dictionary.viewmodels.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){

    private val liveData = MutableLiveData<Resource<List<MyWord>>>()

    fun getWord(word:String): LiveData<Resource<List<MyWord>>> {
        val apiService1 = ApiClient.apiService()

        viewModelScope.launch {

            liveData.postValue(Resource.loading(null))

            try {
                coroutineScope {
                    val async1 = async { apiService1.getMyWord(word)}

                    val await1 = async1.await()

                    liveData.postValue(Resource.success(await1))
                }

            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message ?: "Error", null))
            }

        }
        return liveData
    }
}