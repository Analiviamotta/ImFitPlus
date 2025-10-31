package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityResumeBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_DAILY_CALORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_WEIGHT
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO

class Resume : AppCompatActivity() {
    private val ar: ActivityResumeBinding by lazy {
        ActivityResumeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ar.root)

        val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        }else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }

        val imc =  intent.getDoubleExtra(EXTRA_IMC, 0.0)
        val category = intent.getStringExtra(EXTRA_IMC_CATEGORY)
        val dailyCalories = intent.getDoubleExtra(EXTRA_DAILY_CALORY, 0.0)
        val weight = intent.getDoubleExtra(EXTRA_WEIGHT, 0.0)

        if (user == null) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
        }else {

            val water = (user.weight * 100) * 350

            ar.nameTv.text = String.format("Nome: %s", user.name)
            ar.imcResultTv.text = String.format("IMC: %.2f", imc)
            ar.categoryTv.text = String.format("Categoria do IMC: %s", category)
            ar.idealWeightTv.text = String.format("Peso Ideal: %.2f", weight)
            ar.dailyCaloriesResultTv.text = String.format("Gasto Calórico Diario: %.2f", dailyCalories)
            ar.waterTv.text = String.format("Recomendaçao de consumo de agua em ml: %.2f", water)
        }
    }
}