package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller.ResumeController
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityResumeBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Category
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_DAILY_CALORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_TMB
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_WEIGHT
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryDTO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID

class Resume : AppCompatActivity() {
    private val ar: ActivityResumeBinding by lazy {
        ActivityResumeBinding.inflate(layoutInflater)
    }

    private val resumeController : ResumeController by lazy {
        ResumeController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ar.root)

        fun calculateAge(birthDate: LocalDate): Int{
            val currentDate = LocalDate.now()
            return Period.between(birthDate, currentDate).years
        }

        val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        }else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }

        val imc =  intent.getDoubleExtra(EXTRA_IMC, 0.0)
        val tmb =  intent.getDoubleExtra(EXTRA_TMB, 0.0)
        val category = intent.getStringExtra(EXTRA_IMC_CATEGORY)
        val dailyCalories = intent.getDoubleExtra(EXTRA_DAILY_CALORY, 0.0)
        val weight = intent.getDoubleExtra(EXTRA_WEIGHT, 0.0)
        val categoryEnum = Category.values().find {
            it.label == category
        } ?: Category.NORMAL

        var fcmax = 0
        if (user == null) {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show()
        }else {
            val age = calculateAge(user.birthDate)
            val water = (user.weight * 100) * 350
            fcmax = 220 - age
            val zonaLeveMin = fcmax * 0.5
            val zonaLeveMax = fcmax * 0.6
            val zonaQueimaMax = fcmax * 0.7
            val zonaAerobicaMax = fcmax * 0.8
            val zonaAnaerobicaMax = fcmax * 0.9

            ar.nameTv.text = String.format("Nome: %s", user.name)
            ar.imcResultTv.text = String.format("IMC: %.2f", imc)
            ar.categoryTv.text = String.format("Categoria do IMC: %s", category)
            ar.idealWeightTv.text = String.format("Peso Ideal: %.2f", weight)


            ar.fcMaxTv.text = String.format("FCMAX %0f", fcmax )
            ar.zonaLeveTv.text = String.format("Zona Leve: %0f, %0f", zonaLeveMin, zonaLeveMax)
            ar.zonaQuimaTv.text = String.format("Zona Queima de Gordura: %0f, %0f", zonaLeveMax - zonaQueimaMax)
            ar.zonaAerobicaTv.text = String.format("Zona Anaerobica: %0f, %0f", zonaQueimaMax , zonaAerobicaMax)
            ar.zonaAnaerobicaTv.text = String.format("Zona Anaerobica: %0f, %0f", zonaAerobicaMax , zonaAnaerobicaMax)

            ar.dailyCaloriesResultTv.text = String.format("Gasto Calórico Diario: %.2f", dailyCalories)
            ar.waterTv.text = String.format("Recomendaçao de consumo de agua em ml: %.2f", water)
        }

        ar.saveBt.setOnClickListener {
            if(user === null) return@setOnClickListener

            Thread{
                val findUser = resumeController.getUserByName(user.name)

                if(findUser.id.isEmpty()) {
                    resumeController.addUser(user)
                } else {
                    user.id = findUser.id
                    resumeController.updateUser(user)
                }

                val history = HistoryDTO(
                    UUID.randomUUID().toString(),
                    user.id,
                    LocalDateTime.now(),
                    user.age,
                    user.height,
                    user.weight,
                    user.activityLevel,
                    imc,
                    weight,
                    categoryEnum,
                    tmb,
                    fcmax
                )

                resumeController.addHistory(history)

                Intent(this, HistoryActivity::class.java).apply {
                    putExtra(EXTRA_USER, user)
                    startActivity(this)
                }
            }.start()
        }


    }
}