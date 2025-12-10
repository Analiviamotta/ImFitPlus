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

class HistorySqlite(context: Context): HistoryDAO {
    companion object {
        private val IM_FIT_PLUS_DB_FILE = "inFitPlus"
        private const val HISTORY_TABLE = "history"

        private const val ID_COLUMN = "id"
        private const val CREATED_AT_COLUMN = "createdAt"
        private const val USER_AGE_COLUMN = "userAge"
        private const val HEIGHT_COLUMN = "height"
        private const val WEIGHT_COLUMN = "weight"
        private const val USER_ACTIVITY_LEVEL_COLUMN = "userActivityLevel"
        private const val IMC_COLUMN = "imc"
        private const val IDEAL_WEIGHT_COLUMN = "idealWeight"
        private const val CATEGORY_COLUMN = "category"

        private val CREATE_HISTORY_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS $HISTORY_TABLE (" +
                    "$ID_COLUMN TEXT PRIMARY KEY, " +
                    "$CREATED_AT_COLUMN TEXT NOT NULL, " +
                    "$USER_AGE_COLUMN INTEGER NOT NULL, " +
                    "$HEIGHT_COLUMN REAL NOT NULL, " +
                    "$WEIGHT_COLUMN REAL NOT NULL, " +
                    "$USER_ACTIVITY_LEVEL_COLUMN TEXT NOT NULL, " +
                    "$IMC_COLUMN REAL NOT NULL, " +
                    "$IDEAL_WEIGHT_COLUMN REAL NOT NULL, " +
                    "$CATEGORY_COLUMN TEXT NOT NULL" +
                    ");"
    }

    private val inFitPlusDatabase : SQLiteDatabase = context.openOrCreateDatabase(
        HistorySqlite.Companion.IM_FIT_PLUS_DB_FILE,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            inFitPlusDatabase.execSQL(CREATE_HISTORY_TABLE_STATEMENT)
        } catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.message.toString())
        }
    }

    override fun createHistory(history: HistoryDTO): Long = inFitPlusDatabase.insert(
        HISTORY_TABLE,
        null,
        history.toContentValues()
    )

    override fun retrieveHistory(historyId: String): HistoryDTO {
        TODO("Not yet implemented")
    }

    override fun retrieveHistories(): MutableList<HistoryDTO> {
        val historyList: MutableList<HistoryDTO> = mutableListOf()
        val cursor = inFitPlusDatabase.rawQuery("SELECT * FROM $HISTORY_TABLE;", null)

        while (cursor.moveToNext()) {
            historyList.add(cursor.toHistory())
        }

        cursor.close()
        return historyList

    }

    override fun retrieveHistoriesByUser(userId: String): MutableList<HistoryDTO> {
        TODO("Not yet implemented")
    }

    private fun HistoryDTO.toContentValues() = ContentValues().apply {
        put(ID_COLUMN, id)
        put(CREATED_AT_COLUMN, createdAt.toString())
        put(USER_AGE_COLUMN, userAge)
        put(HEIGHT_COLUMN, height)
        put(WEIGHT_COLUMN, weight)
        put(USER_ACTIVITY_LEVEL_COLUMN, userActivityLevel.name)
        put(IMC_COLUMN, imc)
        put(IDEAL_WEIGHT_COLUMN, idealWeight)
        put(CATEGORY_COLUMN, category.name)
    }

    private fun Cursor.toHistory() = HistoryDTO(
         getString(getColumnIndexOrThrow(ID_COLUMN)),
         LocalDateTime.parse(getString(getColumnIndexOrThrow(CREATED_AT_COLUMN))),
         getInt(getColumnIndexOrThrow(USER_AGE_COLUMN)),
         getDouble(getColumnIndexOrThrow(HEIGHT_COLUMN)),
         getDouble(getColumnIndexOrThrow(WEIGHT_COLUMN)),
         ActivityLevel.valueOf(getString(getColumnIndexOrThrow(USER_ACTIVITY_LEVEL_COLUMN))),
         getDouble(getColumnIndexOrThrow(IMC_COLUMN)),
         getDouble(getColumnIndexOrThrow(IDEAL_WEIGHT_COLUMN)),
         Category.valueOf(getString(getColumnIndexOrThrow(CATEGORY_COLUMN)))
    )
}