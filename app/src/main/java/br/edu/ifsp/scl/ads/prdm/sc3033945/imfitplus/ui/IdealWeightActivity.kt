package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityIdealWeightBinding

class IdealWeightActivity : AppCompatActivity() {
    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(aiwb.root)

        aiwb.finishBt.setOnClickListener {
            val message = "Finalizando..., obrigada por usar o app <3"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
    }
}