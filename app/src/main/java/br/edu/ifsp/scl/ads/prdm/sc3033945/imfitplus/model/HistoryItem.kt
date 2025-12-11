package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

data class HistoryItemDTO(
    var id: String = "",
    var userAge: Int = 0,
    var height: Double = 0.0,
    var userName: String = "",
    var weight: Double = 0.0,
    var userActivityLevel: ActivityLevel = ActivityLevel.Sedentary,
    var imc: Double = 0.0,
    var idealWeight: Double = 0.0,
    var category: Category
)
