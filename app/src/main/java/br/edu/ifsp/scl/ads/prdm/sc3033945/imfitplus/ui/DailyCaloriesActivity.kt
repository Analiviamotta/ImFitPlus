package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityDailyCaloriesBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.ActivityLevel
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Gender
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO

class DailyCaloriesActivity : AppCompatActivity() {
    private val adcb: ActivityDailyCaloriesBinding by lazy {
        ActivityDailyCaloriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(adcb.root)

        val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        }else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }

        if (user == null) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
        } else {
            val tmb = calculateTMB(user)
            val dailyCalories = calculateDailyCalories(tmb, user.activityLevel)

            adcb.tmbTv.text = String.format("Sua Taxa Metabólica Basal é %.2f", tmb)
            adcb.dailyCaloriesResultTv.text = String.format("Seu gasto diário de calorias é %.2f kcal", dailyCalories)
        }

        adcb.backBt.setOnClickListener {
            val message = "Voltando para tela anterior"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finish()
        }

        adcb.calculateIdealWeightBt.setOnClickListener {
            val message = "Redirecionando para tela de peso ideal"

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            Intent(this, IdealWeightActivity::class.java).apply {
                putExtra(EXTRA_USER, user)
                startActivity(this)
            }
        }


    }

    private fun calculateFemaleTMB(user: UserDTO): Double {
        return 655 + (9.6 * user.weight) + (1.8 * user.height * 100) - (4.7 * user.age)
    }

    private fun calculateMaleTMB(user: UserDTO): Double {
        return 66 + (13.7 * user.weight) + (5 * user.height * 100) - (6.8 * user.age)
    }

    private fun calculateTMB(user: UserDTO): Double {
        return if (user.gender == Gender.Female) {
            calculateFemaleTMB(user)
        } else {
            calculateMaleTMB(user)
        }
    }

    private fun calculateDailyCalories(tmb: Double, activityLevel: ActivityLevel): Double {
        val multiplier = when(activityLevel) {
            ActivityLevel.Sedentary -> 1.2
            ActivityLevel.Light -> 1.375
            ActivityLevel.Moderate -> 1.55
            ActivityLevel.Intense -> 1.725
        }
        return tmb * multiplier
    }

}