package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.welcomeBt.setOnClickListener {
            val message = "Redirecionando para o formulario"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            Intent(this, PersonalDataFormActivity::class.java).let {
                startActivity(it)
            }
        }

        amb.historyBt.setOnClickListener {
            val message = "Direcionando para a tela de histórico"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            Intent(this, HistoryActivity::class.java).let {
                startActivity(it)
            }
        }
    }
}