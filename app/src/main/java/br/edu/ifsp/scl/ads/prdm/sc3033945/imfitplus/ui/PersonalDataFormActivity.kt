package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityPersonalDataFormBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Gender
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO

class PersonalDataFormActivity : AppCompatActivity() {
    private val apdfb: ActivityPersonalDataFormBinding by lazy {
        ActivityPersonalDataFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(apdfb.root)

        apdfb.calculateIMCBt.setOnClickListener {

            var height = apdfb.heightEt.text.toString().toDoubleOrNull() ?: 0.0
            var weight = apdfb.weightEt.text.toString().toDoubleOrNull() ?: 0.0

            if(height <= 0){
                Toast.makeText(this, "Altura invalida", Toast.LENGTH_SHORT).show()
            }else if (weight <= 0){
                Toast.makeText(this, "Peso invalido", Toast.LENGTH_SHORT).show()
            } else {

                var imc = if (height > 0) weight / (height * height) else 0.0

                val message = "Redirecionando para tela de IMC"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                Intent(this, ImcResultActivity::class.java).let {
                    startActivity(it)
                }
            }
        }
    }
}