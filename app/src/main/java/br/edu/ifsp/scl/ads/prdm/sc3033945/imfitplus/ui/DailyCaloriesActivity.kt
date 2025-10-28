package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityDailyCaloriesBinding

class DailyCaloriesActivity : AppCompatActivity() {
    private val adcb: ActivityDailyCaloriesBinding by lazy {
        ActivityDailyCaloriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(adcb.root)

        adcb.backBt.setOnClickListener {
            val message = "Voltando para tela anterior"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finish()
        }

        adcb.calculateIdealWeightBt.setOnClickListener {
            val message = "Redirecionando para tela de peso ideal"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            Intent(this, IdealWeightActivity::class.java).let {
                startActivity(it)
            }
        }

    }
}