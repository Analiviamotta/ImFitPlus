package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

interface UserDao {
    fun createUser(user: UserDTO): Long
    fun retrieveUser(userId: String): UserDTO
    fun retrieveUserByName(userName: String): UserDTO
    fun retrieveUsers(): MutableList<UserDTO>
    fun updateUser(user: UserDTO): Int
    fun retrieveUsersBySearchName(userName: String): MutableList<UserDTO>
}