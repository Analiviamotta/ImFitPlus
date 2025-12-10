package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.controller

import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model.UserSqlite
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.ui.PersonalDataFormActivity

class PersonalDataFormController(personalDataFormActivity: PersonalDataFormActivity) {
    private val userDAO: UserDao = UserSqlite(personalDataFormActivity)

    fun getUserByName(userName: String) = userDAO.retrieveUserByName(userName)
}





