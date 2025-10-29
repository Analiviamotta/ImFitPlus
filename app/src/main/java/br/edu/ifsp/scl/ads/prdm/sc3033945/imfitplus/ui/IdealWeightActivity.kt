package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityIdealWeightBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
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

        if (user == null) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
        } else {
            val idealWeight = 22 * (user.height * user.height)
            val diff = user.weight - idealWeight

            aiwb.diffWeightResultTv.text = String.format("A diferenca entre seu peso atual e o peso ideal é %.2f", diff)
            aiwb.idealWeightResultTv.text = String.format("Seu peso ideal é %.2f", idealWeight)

        }

        aiwb.finishBt.setOnClickListener {
            val message = "Finalizando..., obrigada por usar o app <3"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
    }
}