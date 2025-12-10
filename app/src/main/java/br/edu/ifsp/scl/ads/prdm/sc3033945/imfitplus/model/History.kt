package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

import java.time.LocalDateTime

data class HistoryDTO(
    var id: String = "",
    var userId: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var userAge: Int = 0,
    var height: Double = 0.0,
    var weight: Double = 0.0,
    var userActivityLevel: ActivityLevel = ActivityLevel.Sedentary,
    var imc: Double = 0.0,
    var idealWeight: Double = 0.0,
    var category: Category = Category.OVERWEIGHT
)
