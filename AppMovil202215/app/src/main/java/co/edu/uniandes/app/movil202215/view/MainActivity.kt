package co.edu.uniandes.app.movil202215.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.edu.uniandes.app.movil202215.R
import co.edu.uniandes.app.movil202215.Vinilos
import co.edu.uniandes.app.movil202215.Colecciones
import co.edu.uniandes.app.movil202215.Artistas
import co.edu.uniandes.app.movil202215.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Vinilos())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.vinilos -> replaceFragment(Vinilos())
                R.id.artistas -> replaceFragment(Artistas())
                R.id.colecciones -> replaceFragment(Colecciones())

                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}
