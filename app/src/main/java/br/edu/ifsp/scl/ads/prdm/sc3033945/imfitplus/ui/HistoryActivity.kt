package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui

import android.os.Bundle
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
    private val historyList: MutableList<HistoryItemDTO> = mutableListOf()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(this, historyList)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(hab.root)

        fillHistoryList()
        hab.historyLV.adapter = historyAdapter

    }

    private fun fillHistoryList() {
        for (i in 1..10) {
            historyList.add(
                HistoryItemDTO(
                    i,
                    20,
                    "Usuario ${i}",
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