package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityIdealWeightBinding

class IdealWeightActivity : AppCompatActivity() {
    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(aiwb.root)
    }
}