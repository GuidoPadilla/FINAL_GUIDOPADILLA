package com.example.mygithub2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class ViewModelHome : ViewModel() {
        // TODO: Implement the ViewModel

    private val _response = MutableLiveData<String>()

    var valor:String=""
    var bandera = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<userGit>()

    val property: LiveData<userGit>
        get() = _property

    val resp: LiveData<String>
        get() = _response


    init {

    }


    fun getuserGithubProperties() {
        ApiServices.retrofitService.getProperties(valor).enqueue( object: retrofit2.Callback<userGit> {
            override fun onFailure(call: Call<userGit>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            override fun onResponse(call: Call<userGit>, response: Response<userGit>){

                _response.value = "USER: "+response.body()?.login
                if (response.body()?.login!=null){
                    _response.value = "USER: "+response.body()?.login
                    _property.value=response.body()
                    bandera.value=false


                }else{
                    _response.value = "No existe"
                    bandera.value = true
                }
            }
        })
    }
}
