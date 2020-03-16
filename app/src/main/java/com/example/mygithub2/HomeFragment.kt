package com.example.mygithub2


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mygithub2.databinding.FragmentHomeBinding




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
        binding.searchButton.setOnClickListener {
            viewModel.valor =binding.userGit.text.toString()
            viewModel.getuserGithubProperties()

        }

        viewModel.bandera.observe(viewLifecycleOwner, Observer { bandera ->
            bandera?.let {
                viewModel.bandera.value = null
                if(bandera==false){
                    binding.buttonRepos.visibility=View.VISIBLE
                    binding.imgs.visibility=View.VISIBLE
                }else if(bandera==true){
                    Toast.makeText(activity,"No existe",Toast.LENGTH_SHORT).show()
                    binding.buttonRepos.visibility=View.GONE
                    binding.imgs.visibility=View.GONE
                }


            }
        })

        return binding.root

    }
}