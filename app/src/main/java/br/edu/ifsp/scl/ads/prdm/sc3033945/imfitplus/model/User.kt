package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

data class User(
    var id: Int = -1,
    var age: Int = 0,
    var name: String = "",
    var height: Double = 0.0,
    var weight: Double = 0.0,
    var gender: Gender = Gender.Female,
    var activityLevel: ActivityLevel = ActivityLevel.Sedentary
)
