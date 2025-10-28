package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityDailyCaloriesBinding

class DailyCaloriesActivity : AppCompatActivity() {
    private val adcb: ActivityDailyCaloriesBinding by lazy {
        ActivityDailyCaloriesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(adcb.root)

        adcb.calculateIdealWeightBt.setOnClickListener {
            Intent(this, IdealWeightActivity::class.java).let {
                startActivity(it)
            }
        }

    }
}