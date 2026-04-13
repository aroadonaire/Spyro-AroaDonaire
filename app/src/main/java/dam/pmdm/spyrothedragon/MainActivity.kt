package dam.pmdm.spyrothedragon

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dam.pmdm.spyrothedragon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("SpyroPrefs", MODE_PRIVATE)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as? NavHostFragment
        navHostFragment?.let {
            navController = it.navController

            NavigationUI.setupActionBarWithNavController(this, navController!!)

            binding.navView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_characters -> {
                        navController?.navigate(R.id.navigation_characters)
                        true
                    }

                    R.id.nav_worlds -> {
                        navController?.navigate(R.id.navigation_worlds)
                        true
                    }

                    R.id.nav_collectibles -> {
                        navController?.navigate(R.id.navigation_collectibles)
                        true
                    }

                    else -> false
                }
            }
        }

        val guiaCompletada = sharedPreferences.getBoolean("guia_vista", false)
        if (!guiaCompletada) {
            iniciarGuiaInteractiva()
        }
    }

    private fun selectedBottomMenu(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_characters ->
                navController?.navigate(R.id.navigation_characters)
            R.id.nav_worlds ->
                navController?.navigate(R.id.navigation_worlds)
            else ->
                navController?.navigate(R.id.navigation_collectibles)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_info) {
            showInfoDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.title_about)
            .setMessage(R.string.text_about)
            .setPositiveButton(R.string.accept, null)
            .show()
    }

    private fun iniciarGuiaInteractiva() {
        binding.guideContainer.visibility = android.view.View.VISIBLE
        mostrarPaso(R.layout.guia_bienvenida)
    }

    private fun mostrarPaso(layoutId: Int) {
        binding.guideContainer.removeAllViews()

        val view = layoutInflater.inflate(layoutId, binding.guideContainer, false)
        binding.guideContainer.addView(view)

        val animacionBocadillo = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.aparicion_bocadillo)
        val bocadillo = view.findViewById<android.view.View>(R.id.layoutBocadillo)
        bocadillo?.startAnimation(animacionBocadillo)

        if(bocadillo!=null){
            bocadillo.startAnimation(animacionBocadillo)
            reproducirSonido(R.raw.bocadillo)
        }

        view.findViewById<android.view.View>(R.id.btnSkip)?.setOnClickListener {
            reproducirSonido(R.raw.omitir)
            finalizarGuia()
        }

        when (layoutId) {
            R.layout.guia_bienvenida -> {
                view.findViewById<ImageView>(R.id.btnStart).setOnClickListener {
                    reproducirSonido(R.raw.avanzar)
                    mostrarPaso(R.layout.guia_personajes)
                }
            }
            R.layout.guia_personajes -> {
                view.findViewById<Button>(R.id.btnNext).setOnClickListener {
                    reproducirSonido(R.raw.avanzar)
                    mostrarPaso(R.layout.guia_mundos)
                }
            }
            R.layout.guia_mundos -> {
                view.findViewById<Button>(R.id.btnNext).setOnClickListener {
                    reproducirSonido(R.raw.avanzar)
                    mostrarPaso(R.layout.guia_coleccionables)
                }
            }
            R.layout.guia_coleccionables -> {
                view.findViewById<Button>(R.id.btnNext).setOnClickListener {
                    reproducirSonido(R.raw.avanzar)
                    mostrarPaso(R.layout.guia_info)
                }
            }
            R.layout.guia_info -> {
                view.findViewById<Button>(R.id.btnNext).setOnClickListener {
                    reproducirSonido(R.raw.avanzar)
                    mostrarPaso(R.layout.guia_resumen)
                }
            }
            R.layout.guia_resumen -> {
                view.findViewById<Button>(R.id.btnFinish).setOnClickListener {
                    reproducirSonido(R.raw.omitir)
                    finalizarGuia()
                }
            }
        }
    }

    private fun finalizarGuia() {
        binding.guideContainer.visibility = android.view.View.GONE
        // Guardamos para que no vuelva a aparecer
        sharedPreferences.edit().putBoolean("guia_vista", true).apply()
    }

    private fun reproducirSonido(resourceId: Int){
        val mediaPlayer = android.media.MediaPlayer.create(this, resourceId)
        mediaPlayer.setOnCompletionListener { mp->
            mp.release()
        }
        mediaPlayer.start()
    }
}
