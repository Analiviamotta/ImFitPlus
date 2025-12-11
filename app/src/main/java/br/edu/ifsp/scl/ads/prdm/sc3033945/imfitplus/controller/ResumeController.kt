package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller

import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryDAO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistoryDTO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.HistorySqlite
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDTO
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserSqlite
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui.Resume

class ResumeController(resumeActivity: Resume) {
    private val userDAO: UserDao = UserSqlite(resumeActivity)
    private val historyDAO: HistoryDAO = HistorySqlite(resumeActivity)

    fun getUserByName(userName: String) = userDAO.retrieveUserByName(userName)
    fun addUser(user: UserDTO) = userDAO.createUser(user)
    fun updateUser(user: UserDTO) = userDAO.updateUser(user)

    fun addHistory(history: HistoryDTO) = historyDAO.createHistory(history)
}