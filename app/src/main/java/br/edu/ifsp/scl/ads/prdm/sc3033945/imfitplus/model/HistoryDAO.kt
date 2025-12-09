package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

interface HistoryDAO {
    fun createHistory(history: HistoryDTO): Long
    fun retrieveHistory(historyId: String): HistoryDTO
    fun retrieveHistories(): MutableList<HistoryDTO>
    fun retrieveHistoriesByUser(userId: String): MutableList<HistoryDTO>
}