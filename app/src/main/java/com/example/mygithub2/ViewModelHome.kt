package com.example.mygithub2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import com.example.mygithub2.ApiService
import com.example.mygithub2.userGit
import retrofit2.Response

class ViewModelHome : ViewModel() {
        // TODO: Implement the ViewModel

    private val _response = MutableLiveData<String>()

    var valor:String=""
    var bandera = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<userGit>()

    val property: LiveData<userGit>
        get() = _property
//In your network successfull response


    // The external immutable LiveData for the response String
    val resp: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {

    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    fun getuserGithubProperties() {
        ApiServices.retrofitService.getProperties(valor).enqueue( object: retrofit2.Callback<userGit> {
            override fun onFailure(call: Call<userGit>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            //una vez tiene la respuesta de la pagina
            override fun onResponse(call: Call<userGit>, response: Response<userGit>){

                //muestra el nombre del usuario
                _response.value = "Usuario: "+response.body()?.login
                if (response.body()?.login!=null){
                    _response.value = "Usuario: "+response.body()?.login
                    _property.value=response.body()
                    bandera.value=false


                }else{//de lo contrario deice que no lo encontro
                    _response.value = "No bandera"
                    bandera.value = true
                }
            }
        })
    }
}
