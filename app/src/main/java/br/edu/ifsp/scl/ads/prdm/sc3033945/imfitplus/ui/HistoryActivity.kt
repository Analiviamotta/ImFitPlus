package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.adapter.HistoryAdapter
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller.HistoryController
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller.ResumeController
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityHistoryBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.ActivityLevel
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Category
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_IMC_CATEGORY
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Constants.EXTRA_USER
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryDTO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryItemDTO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO

class HistoryActivity : AppCompatActivity() {
    private val hab: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    private val historyController : HistoryController by lazy {
        HistoryController(this)
    }

    private val historyListFull: MutableList<HistoryItemDTO> = mutableListOf()

    private val historyListFiltered: MutableList<HistoryItemDTO> = mutableListOf()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(this, historyListFiltered)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(hab.root)


        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_USER, UserDTO::class.java)
        } else {
            intent.getParcelableExtra<UserDTO>(EXTRA_USER)
        }

        fillHistoryList(user)

        if(user !== null) hab.searchEt.visibility = View.GONE
        else hab.searchEt.visibility = View.VISIBLE

        historyListFiltered.addAll(historyListFull)


        hab.historyLV.adapter = historyAdapter


        hab.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterHistory(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        hab.backBt.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }


    private fun filterHistory(query: String) {
        historyListFiltered.clear()

        if (query.isEmpty()) {

            historyListFiltered.addAll(historyListFull)
        } else {

           val users = historyController.getUsersBySearch(query)

            users.forEach { user->
                val histories = historyController.getHistoryByUser(user.id)
                val historiItens = histories.map { history -> parseHistoryDTO(history, user.name) }

                historyListFiltered.addAll(historiItens)
            }

        }

        historyAdapter.notifyDataSetChanged()
    }

    private fun parseHistoryDTO(history: HistoryDTO, userName: String): HistoryItemDTO =
        HistoryItemDTO(
            history.id,
            history.userAge,
            history.height,
            userName,
            history.weight,
            history.userActivityLevel,
            history.imc,
            history.idealWeight,
            history.category,
            history.tmb
        )


    private fun fillHistoryList(user: UserDTO?) {

        val histories = if (user != null) {
            historyController.getHistoryByUser(user.id)
        } else {
            historyController.getAllHistories()
        }

        histories.forEach { historyDTO ->
            val user = historyController.getUserById(historyDTO.userId)

            historyListFull.add(
                HistoryItemDTO(
                    historyDTO.id,
                    historyDTO.userAge,
                    historyDTO.height,
                    user.name,
                    historyDTO.weight,
                    historyDTO.userActivityLevel,
                    historyDTO.imc,
                    historyDTO.idealWeight,
                    historyDTO.category,
                    historyDTO.tmb
                )
            )
        }
    }
}