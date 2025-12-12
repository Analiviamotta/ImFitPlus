package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityIdealWeightBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_DAILY_CALORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_TMB
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_WEIGHT
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO

class IdealWeightActivity : AppCompatActivity() {
    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(aiwb.root)

        val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        }else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }

        val imc =  intent.getDoubleExtra(EXTRA_IMC, 0.0)
        val tmb =  intent.getDoubleExtra(EXTRA_TMB, 0.0)
        val category = intent.getStringExtra(EXTRA_IMC_CATEGORY)
        val dailyCalories = intent.getDoubleExtra(EXTRA_DAILY_CALORY, 0.0)

        var idealWeight = 0.0
        if (user == null) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
        } else {
            idealWeight = 22 * (user.height * user.height)
            val diff = user.weight - idealWeight

            aiwb.diffWeightResultTv.text = String.format("A diferenca entre seu peso atual e o peso ideal é %.2f", diff)
            aiwb.idealWeightResultTv.text = String.format("Seu peso ideal é %.2f", idealWeight)

        }

        aiwb.openResumeBt.setOnClickListener {
            val message = "Redirecionando, para resumo de saude <3"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            Intent(this, Resume::class.java).let { intent ->
                intent.putExtra(EXTRA_USER, user)
                intent.putExtra(EXTRA_IMC, imc)
                intent.putExtra(EXTRA_IMC_CATEGORY, category)
                intent.putExtra(EXTRA_DAILY_CALORY, dailyCalories)
                intent.putExtra(EXTRA_WEIGHT, idealWeight)
                intent.putExtra(EXTRA_TMB, tmb)
                startActivity(intent)
            }
        }
    }
}