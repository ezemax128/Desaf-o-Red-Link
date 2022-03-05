package pumpkin.app.desafioredlink.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer()
    }

    private fun timer() {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                //Nothing To Do
            }

            override fun onFinish() {
                Toast.makeText(
                    this@Splash,
                    "Desafío Programado por Ezequiel Pirola Android Developer",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this@Splash, MainActivity::class.java))
            }

        }.start()
    }
}