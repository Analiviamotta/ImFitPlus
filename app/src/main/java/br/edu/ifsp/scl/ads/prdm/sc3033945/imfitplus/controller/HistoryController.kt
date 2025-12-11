package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller

import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryDAO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistorySqlite
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserSqlite
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui.HistoryActivity

class HistoryController(historyActivity: HistoryActivity) {
    private val historyDAO: HistoryDAO = HistorySqlite(historyActivity)
    private val userDAO: UserDao = UserSqlite(historyActivity)

    fun getUserById(userId: String) = userDAO.retrieveUser(userId)
    fun getUsersBySearch(userName: String) = userDAO.retrieveUsersBySearchName(userName)
    fun getHistoryByUser(userId: String) = historyDAO.retrieveHistoriesByUser(userId)
    fun getAllHistories() = historyDAO.retrieveHistories()
}