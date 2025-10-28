package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityImcResultBinding

class ImcResultActivity : AppCompatActivity() {
    private val air: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(air.root)

        air.backBt.setOnClickListener {
            val message = "Voltando para tela anterior"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finish()
        }

        air.calculateCalorieExpenditureBt.setOnClickListener {
            val message = "Redirecionando para tela de gastos caloricos diarios"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            Intent(this, DailyCaloriesActivity::class.java).let {
                startActivity(it)
            }
        }

    }
}