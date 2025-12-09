package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityPersonalDataFormBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.ActivityLevel
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Gender
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO
import java.time.LocalDateTime
import java.util.UUID

class PersonalDataFormActivity : AppCompatActivity() {
    private val apdfb: ActivityPersonalDataFormBinding by lazy {
        ActivityPersonalDataFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(apdfb.root)

        apdfb.calculateIMCBt.setOnClickListener {

            val name = apdfb.nameEt.text.toString()
            val age = apdfb.ageEt.text.toString().toDoubleOrNull() ?: 0.0
            val height = apdfb.heightEt.text.toString().toDoubleOrNull() ?: 0.0
            val weight = apdfb.weightEt.text.toString().toDoubleOrNull() ?: 0.0

            if(height <= 0){
                Toast.makeText(this, "Altura invalida", Toast.LENGTH_SHORT).show()
            } else if (weight <= 0){
                Toast.makeText(this, "Peso invalido", Toast.LENGTH_SHORT).show()
            } else if(name.isEmpty()) {
                Toast.makeText(this, "O nome e obrigatorio", Toast.LENGTH_SHORT).show()

            }else if(age <= 0){
                Toast.makeText(this, "Idade invalida", Toast.LENGTH_SHORT).show()
            }else{
                val userId = UUID.randomUUID().toString()
                val imc = weight / (height * height)

                val gender = if(apdfb.femaleRb.isChecked) Gender.Female else Gender.Male

                val activityLevel = when(apdfb.physicalActivityLevelSp.selectedItemPosition){
                    0 -> ActivityLevel.Sedentary
                    1 -> ActivityLevel.Light
                    2 -> ActivityLevel.Moderate
                    3 -> ActivityLevel.Intense
                    else -> ActivityLevel.Sedentary
                }

                var user = UserDTO(userId,
                    apdfb.ageEt.text.toString().toInt(),
                    apdfb.nameEt.text.toString(),
                    height,
                    weight,
                    gender,
                    LocalDateTime.now(),
                    activityLevel)


                val message = "Redirecionando para tela de IMC"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                Intent(this, ImcResultActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                    putExtra(EXTRA_IMC, imc)
                    startActivity(this)
                }
            }
        }
    }
}