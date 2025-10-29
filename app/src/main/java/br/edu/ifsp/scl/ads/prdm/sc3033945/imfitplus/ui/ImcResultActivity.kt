package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityImcResultBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO


class ImcResultActivity : AppCompatActivity() {
    private val air: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(air.root)

        val imc =  intent.getDoubleExtra(EXTRA_IMC, 0.0)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        } else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }
        val category = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Normal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }

        air.nameTv.text = String.format("Oii %s", user?.name ?: "")
        air.imcResultTv.text = String.format("Seu IMC é %.2f", imc)
        air.categoryTv.text = String.format("Você se enquadra na categoria %s", category)


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