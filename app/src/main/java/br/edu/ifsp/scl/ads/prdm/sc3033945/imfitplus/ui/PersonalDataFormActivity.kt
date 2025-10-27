package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityPersonalDataFormBinding

class PersonalDataFormActivity : AppCompatActivity() {
    private val apdfb: ActivityPersonalDataFormBinding by lazy {
        ActivityPersonalDataFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_personal_data_form)
    }
}