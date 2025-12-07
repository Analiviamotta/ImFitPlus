package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.databinding.TileHistoryBinding
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryItemDTO

class HistoryAdapter (
    context: Context,
    private val historyList: MutableList<HistoryItemDTO>
): ArrayAdapter<HistoryItemDTO>(context, R.layout.tile_history, historyList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val historyItem = historyList[position]

        var historyTileView = convertView

        if (historyTileView == null) {
            TileHistoryBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE)
                        as LayoutInflater, parent, false
            ).apply {
                historyTileView = root
                val historyTileViewHolder = TileHistoryViewHolder(userNameTv, ageTv, weightTv,idealWeightTv, imcTv, categoryTv )
                historyTileView.tag = historyTileViewHolder
            }
        }

        val tileViewHolder = historyTileView?.tag as TileHistoryViewHolder
        tileViewHolder.userNameTv.text = historyItem.userName
        tileViewHolder.ageTv.text = "${historyItem.userAge} anos"
        tileViewHolder.weightTv.text = "Peso: %.0f kg".format(historyItem.weight)
        tileViewHolder.idealWeightTv.text = "Ideal: %.0f kg".format(historyItem.idealWeight)
        tileViewHolder.imcTv.text = "IMC: %.0f".format(historyItem.imc)
        tileViewHolder.categoryTv.text = "Categoria: ${historyItem.category.label}"

        return historyTileView
    }

        private data class TileHistoryViewHolder(val userNameTv: TextView, val ageTv: TextView, val
        weightTv: TextView, val idealWeightTv: TextView, val imcTv: TextView, val categoryTv: TextView)
}