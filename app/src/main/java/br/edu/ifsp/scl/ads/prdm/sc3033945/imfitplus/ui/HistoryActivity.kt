package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.adapter.HistoryAdapter
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.ActivityHistoryBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.ActivityLevel
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.Category
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryItemDTO

class HistoryActivity : AppCompatActivity() {
    private val hab: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    private val historyListFull: MutableList<HistoryItemDTO> = mutableListOf()

    private val historyListFiltered: MutableList<HistoryItemDTO> = mutableListOf()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(this, historyListFiltered)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(hab.root)

        fillHistoryList()


        historyListFiltered.addAll(historyListFull)


        hab.historyLV.adapter = historyAdapter


        hab.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterHistory(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })



    }
    private fun filterHistory(query: String) {
        historyListFiltered.clear()

        if (query.isEmpty()) {

            historyListFiltered.addAll(historyListFull)
        } else {

            val filtered = historyListFull.filter { history ->
                history.userName.contains(query, ignoreCase = true)
            }
            historyListFiltered.addAll(filtered)
        }

        historyAdapter.notifyDataSetChanged()
    }

    private fun fillHistoryList() {
        for (i in 1..10) {
            historyListFull.add(
                HistoryItemDTO(
                    i,
                    20,
                    "Usuario $i",
                    20.0,
                    ActivityLevel.Sedentary,
                    9.0,
                    52.0,
                    Category.OVERWEIGHT
                )
            )
        }
    }
}