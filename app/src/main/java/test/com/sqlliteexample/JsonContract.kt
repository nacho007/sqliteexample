package test.com.sqlliteexample

import android.provider.BaseColumns

/**
 * Created by ignaciodeandreisdenis on 25/2/18.
 */
object JsonContract {
    // Table contents are grouped together in an anonymous object.
    object JsonEntry : BaseColumns {
        const val TABLE_NAME = "json"
        const val COLUMN_NAME_DATA = "data"
    }
}
