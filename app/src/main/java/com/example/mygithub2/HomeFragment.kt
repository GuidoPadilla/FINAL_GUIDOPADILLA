package com.example.mygithub2


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.mygithub2.ApiService
import com.example.mygithub2.ViewModelHome
import com.example.mygithub2.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var api:ApiService

    private val viewModel:ViewModelHome by lazy {
        ViewModelProviders.of(this).get(ViewModelHome::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentHomeBinding.inflate(inflater)

        binding.setLifecycleOwner (this)
        binding.models=viewModel

        //al presionar el boton de buscar
        binding.searchButton.setOnClickListener {
            viewModel.valor =binding.userGit.text.toString()
            viewModel.getuserGithubProperties()

        }

        //el observer que decide si mostrar los demas botones dependiendo la respuesta
        viewModel.bandera.observe(viewLifecycleOwner, Observer { bandera ->
            bandera?.let {
                viewModel.bandera.value = null
                if(bandera==false){
                    binding.buttonRepos.visibility=View.VISIBLE
                    binding.imgs.visibility=View.VISIBLE
                }else if(bandera==true){
                    Toast.makeText(activity,"El usuario que ingreso no bandera",Toast.LENGTH_SHORT).show()
                    binding.buttonRepos.visibility=View.GONE
                    binding.imgs.visibility=View.GONE
                }


            }
        })

        return binding.root

    }
}