package com.example.mygithub2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navigation controler para cmabiar las pestañas
        val Controllernav=this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,Controllernav)
    }

    //para poder regresar al momento de cmabiar pestañas
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
