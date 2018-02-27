package test.com.sqlliteexample

import android.provider.BaseColumns

/**
 * Created by ignaciodeandreisdenis on 25/2/18.
 */
const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${JsonContract.JsonEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${JsonContract.JsonEntry.COLUMN_NAME_DATA} TEXT)"

const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${JsonContract.JsonEntry.TABLE_NAME}"