package mx.tecnm.tepic.ladm_u2_practica2_juegodemoscas


import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.lang.String
import java.util.*

class Lienzo(p: MainActivity): View(p){

    var aplastador = 1
    var txtTimer =""
    var moscas : Array<Mosca> = Array(100, { Mosca(this) })
    var kmosca = 0

    //Sintaxis del Timer
    var countDownTimer: CountDownTimer? = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            txtTimer= (
                String.format(
                    Locale.getDefault(),
                    "%d sec.",
                    millisUntilFinished / 1000L
                )
            )
            if(aplastador==100){
                mensaje("FELICIDADES")
                this.cancel()    }
            if(millisUntilFinished/1000L==0.toLong()){
                    mensaje("PERDISTE, VUELVE A INTENTARLO ")}
        }

        override fun onFinish() {
            this.cancel()
        }
    }.start()



    override fun onDraw(c: Canvas){
        super.onDraw(c)
        var paint = Paint()
        //Cielo
        c.drawColor(Color.CYAN)
        paint.setColor(Color.BLACK)

        paint.textSize = 40f
        c.drawText("SCORE: " +aplastador.toString(), 100f, 100f, paint)

        paint.textSize = 40f
        c.drawText("Numero de mosca: "+kmosca.toString(), 100f, 200f, paint)

        paint.textSize = 40f
        c.drawText("Tiempo: "+ txtTimer, 100f, 300f, paint)




        //mosca.pintar(c,paint)
        (0..99).forEach {
            moscas[it].pintar(c, paint)
        }



    }

    fun animarMosca(){
        (0..99).forEach {
            if(moscas[it].viva)
            moscas[it].rebote(width, height)
        }
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){

            MotionEvent.ACTION_DOWN -> {
                var puntero = 0

                while (puntero <= 99) {

                    if (moscas[puntero].estaEnArea(event.x, event.y)) {
                        if (moscas[puntero].viva) {
                            aplastador++
                            kmosca = puntero
                            var muerte = BitmapFactory.decodeResource(
                                this.resources,
                                R.drawable.mancha
                            )
                            moscas[puntero].imagen = muerte
                            moscas[puntero].viva = false
                        }
                    }
                    puntero++
                }
            }
                            }
        invalidate()
        return true
    }

    fun mensaje(m: kotlin.String){
        AlertDialog.Builder(this.context)
            .setTitle("RESULTADOS")
            .setMessage("El total de moscas aplastadas fue:" + aplastador + ".  " +m)
            .setPositiveButton("OK"){p, i ->}
            .show()
    }
}