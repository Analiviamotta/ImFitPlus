package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

@Parcelize
data class UserDTO(
    var id: String = "",
    var age: Int = 0,
    var name: String = "",
    var height: Double = 0.0,
    var weight: Double = 0.0,
    var gender: Gender = Gender.Female,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var activityLevel: ActivityLevel = ActivityLevel.Sedentary,
    var birthDate: LocalDate = LocalDate.now()
): Parcelable
