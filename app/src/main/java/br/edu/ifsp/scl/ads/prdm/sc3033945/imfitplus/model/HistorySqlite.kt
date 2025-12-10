package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

import android.content.Context

class HistorySqlite(context: Context): HistoryDAO {
    override fun createHistory(history: HistoryDTO): Long {
        TODO("Not yet implemented")
    }

    override fun retrieveHistory(historyId: String): HistoryDTO {
        TODO("Not yet implemented")
    }

    override fun retrieveHistories(): MutableList<HistoryDTO> {
        TODO("Not yet implemented")
    }

    override fun retrieveHistoriesByUser(userId: String): MutableList<HistoryDTO> {
        TODO("Not yet implemented")
    }
}