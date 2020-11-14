package mx.tecnm.tepic.ladm_u2_practica2_juegodemoscas


import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Mosca(punteroLienzo: Lienzo) {
    var x = (0..1920).random().toFloat()
    var y = (0..1080).random().toFloat()
    var incX = 50
    var incY = 50
    var imagen = BitmapFactory.decodeResource(punteroLienzo.resources, R.drawable.mosca)
    var viva = true



    fun pintar(c: Canvas, p:Paint){
        c.drawBitmap(imagen,x,y, p)
    }


    fun rebote(ancho:Int, alto:Int){
        x+=incX
        y+=incY
        if(x<= -100||x>=ancho){
            incX*=-1
        }

        if(y<=-100||y>=alto){
            incY*=-1
        }
    }

    fun estaEnArea(toqueX:Float,toqueY:Float): Boolean {
        var x2 = x + imagen.width
        var y2 = y + imagen.height

        if(toqueX >= x && toqueX<= x2){
            if(toqueY >= y && toqueY <= y2){
                return true
            }
        }
        return false
    }

}