package mx.tecnm.tepic.ladm_u2_practica2_juegodemoscas


class Hilo(p: MainActivity): Thread(){
    var puntero = p
    override fun run(){
        super.run()

        while(true){
            sleep(200)
            puntero.runOnUiThread {
                puntero.lienzo!!.animarMosca()
            }
        }
    }
}