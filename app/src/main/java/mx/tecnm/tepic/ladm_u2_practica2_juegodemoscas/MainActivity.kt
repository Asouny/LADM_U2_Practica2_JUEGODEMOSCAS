package mx.tecnm.tepic.ladm_u2_practica2_juegodemoscas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var lienzo :Lienzo ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Lienzo(this))
        lienzo = Lienzo(this)
        setContentView(lienzo!!)

        Hilo(this).start()

    }
}