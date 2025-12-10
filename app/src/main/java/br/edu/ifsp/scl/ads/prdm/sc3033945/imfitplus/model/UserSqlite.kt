package br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.ads.prdm.sc3033945.imfitplus.R
import java.sql.SQLException
import java.time.LocalDateTime

class UserSqlite(context: Context): UserDao {
    companion object {
        private val IM_FIT_PLUS_DB_FILE = "inFitPlus"
        private val USER_TABLE = "user"
        private val ID_COLUMN = "id"
        private val AGE_COLUMN = "age"
        private val NAME_COLUMN = "name"
        private val HEIGHT_COLUMN = "height"
        private val WEIGHT_COLUMN = "weight"
        private val GENDER_COLUMN = "gender"
        private val CREATED_AT_COLUMN = "createdAt"
        private val ACTIVITY_LEVEL_COLUMN = "activityLevel"

        private val CREATE_USER_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $USER_TABLE (" +
                "$ID_COLUMN TEXT PRIMARY KEY, " +
                "$AGE_COLUMN INTEGER NOT NULL, " +
                "$NAME_COLUMN TEXT NOT NULL, " +
                "$HEIGHT_COLUMN REAL NOT NULL, " +
                "$WEIGHT_COLUMN REAL NOT NULL, " +
                "$GENDER_COLUMN TEXT NOT NULL, " +
                "$CREATED_AT_COLUMN TEXT NOT NULL, " +
                "$ACTIVITY_LEVEL_COLUMN TEXT NOT NULL" +
                ");"
    }

    private val userDatabase : SQLiteDatabase = context.openOrCreateDatabase(
        IM_FIT_PLUS_DB_FILE,
        MODE_PRIVATE,
        null
    )

    // Criando a tabela de usuarios
    init {
        try {
            userDatabase.execSQL(CREATE_USER_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.message.toString())
        }
    }

    override fun createUser(user: UserDTO): Long = userDatabase.insert(USER_TABLE, null, user.toContentValues())

    override fun retrieveUser(userId: String): UserDTO {
        val cursor = userDatabase.query(true,
            USER_TABLE,
            null,
            "$ID_COLUMN = ?",
            arrayOf(userId),
            null,
            null,
            null,
            null)

        return if(cursor.moveToFirst()){
            val user = cursor.toUser()
            cursor.close()
            user
        } else {
            cursor.close()
            UserDTO()
        }
    }

    override fun retrieveUserByName(userName: String): UserDTO {
        val cursor = userDatabase.query(true,
            USER_TABLE,
            null,
            "$NAME_COLUMN = ?",
            arrayOf(userName),
            null,
            null,
            null,
            null)

        return if(cursor.moveToFirst()){
            val user = cursor.toUser()
            cursor.close()
            user
        } else {
            cursor.close()
            UserDTO()
        }
    }

    override fun retrieveUsers(): MutableList<UserDTO> {
       val userList: MutableList<UserDTO> = mutableListOf()
        val cursor = userDatabase.rawQuery("SELECT * FROM $USER_TABLE;", null)

        while (cursor.moveToNext()){
            userList.add(cursor.toUser())
        }

        cursor.close()

        return userList
    }

    override fun updateUser(user: UserDTO): Int {
        TODO("Not yet implemented")
    }

    private fun UserDTO.toContentValues() = ContentValues().apply {
        put(ID_COLUMN, id)
        put(AGE_COLUMN, age)
        put(NAME_COLUMN, name)
        put(HEIGHT_COLUMN, height)
        put(WEIGHT_COLUMN, weight)
        put(GENDER_COLUMN, gender.name)
        put(CREATED_AT_COLUMN, createdAt.toString())
        put(ACTIVITY_LEVEL_COLUMN, activityLevel.name)
    }

    private fun Cursor.toUser() = UserDTO(
        getString(getColumnIndexOrThrow(ID_COLUMN)),
        getInt(getColumnIndexOrThrow(AGE_COLUMN)),
        getString(getColumnIndexOrThrow(NAME_COLUMN)),
        getDouble(getColumnIndexOrThrow(HEIGHT_COLUMN)),
        getDouble(getColumnIndexOrThrow(WEIGHT_COLUMN)),
        Gender.valueOf(getString(getColumnIndexOrThrow(GENDER_COLUMN))),
        LocalDateTime.parse(getString(getColumnIndexOrThrow(CREATED_AT_COLUMN))),
        ActivityLevel.valueOf(getString(getColumnIndexOrThrow(ACTIVITY_LEVEL_COLUMN)))
    )

}