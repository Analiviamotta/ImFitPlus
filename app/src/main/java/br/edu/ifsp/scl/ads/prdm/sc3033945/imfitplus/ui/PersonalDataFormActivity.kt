package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller.PersonalDataFormController
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

    private val userController : PersonalDataFormController by lazy {
        PersonalDataFormController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(apdfb.root)

        apdfb.physicalActivityLevelSp.isEnabled = false

        apdfb.isNotFirstUseCb.setOnCheckedChangeListener { _, isChecked ->

            val enabled = !isChecked

            apdfb.nameEt.isEnabled = enabled
            apdfb.ageEt.isEnabled = enabled
            apdfb.heightEt.isEnabled = enabled
            apdfb.weightEt.isEnabled = enabled
            apdfb.femaleRb.isEnabled = enabled
            apdfb.maleRb.isEnabled = enabled


            apdfb.physicalActivityLevelSp.isEnabled = enabled
            if (enabled) {
                apdfb.physicalActivityLevelSp.isEnabled = true
            } else {
                apdfb.physicalActivityLevelSp.isEnabled = false
            }


            apdfb.findUserBt.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        apdfb.findUserBt.setOnClickListener {
            val name = apdfb.nameEt.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(
                    this,
                    "Digite o nome para preencher as informações do usuário",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val user = userController.getUserByName(name)


            if (user.id.isEmpty()) {
                Toast.makeText(
                    this,
                    "Usuário não encontrado",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            apdfb.ageEt.setText(user.age.toString())
            apdfb.heightEt.setText(user.height.toString())
            apdfb.weightEt.setText(user.weight.toString())
            apdfb.nameEt.setText(user.name)


            if (user.gender == Gender.Female) {
                apdfb.femaleRb.isChecked = true
            } else {
                apdfb.maleRb.isChecked = true
            }


            apdfb.physicalActivityLevelSp.setSelection(
                when (user.activityLevel) {
                    ActivityLevel.Sedentary -> 0
                    ActivityLevel.Light -> 1
                    ActivityLevel.Moderate -> 2
                    ActivityLevel.Intense -> 3
                }
            )

            Toast.makeText(
                this,
                "Usuário encontrado! Dados preenchidos.",
                Toast.LENGTH_SHORT
            ).show()
        }



        apdfb.calculateIMCBt.setOnClickListener {

            val name = apdfb.nameEt.text.toString()
            val user = userController.getUserByName(name)

           if(!apdfb.isNotFirstUseCb.isChecked) {

               if (!user.id.isEmpty()) {
                  Toast.makeText(
                           this,
                           "Já existe um usuario com esse nome",
                           Toast.LENGTH_SHORT
                   ).show()
                   return@setOnClickListener
               }

           }else {
               if (user.id.isEmpty()) {
                   Toast.makeText(
                       this,
                       "Não foi encontrado um usuário com esse nome",
                       Toast.LENGTH_SHORT
                   ).show()
                   return@setOnClickListener
               }
           }



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