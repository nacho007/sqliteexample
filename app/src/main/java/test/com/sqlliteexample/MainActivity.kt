package test.com.sqlliteexample

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.smartway.android.fucrea.model.User
import kotlinx.android.synthetic.main.activity_main.*
import test.com.sqlliteexample.JsonContract.JsonEntry.COLUMN_NAME_DATA
import test.com.sqlliteexample.JsonContract.JsonEntry.TABLE_NAME
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    var dbWritable: SQLiteDatabase? = null
    var dbreadable: SQLiteDatabase? = null
    var json: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DbHelper(this)
        dbWritable = dbHelper.writableDatabase
        dbreadable = dbHelper.readableDatabase


        json = loadJSONFromAsset("simulated_json/user.json")
        Log.e("JSON", json)

//        var user: User
//
//        try {
//            var gson = Gson()
//            user = gson.fromJson<User>(json, User::class.java)
//            Log.e("USER", user.toString())
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

        buttonAddRow.setOnClickListener {
            // Create a new map of values, where column names are the keys
            val values = ContentValues().apply {
                put(COLUMN_NAME_DATA, json)

            }

            // Insert the new row, returning the primary key value of the new row
            val newRowId = dbWritable?.insert(TABLE_NAME, null, values)
            Toast.makeText(this, "onAddRow: " + newRowId, Toast.LENGTH_SHORT).show()
        }

        buttonGetRow.setOnClickListener {

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(BaseColumns._ID, COLUMN_NAME_DATA)

            // Filter results WHERE "title" = 'My Title'
//            val selection = "${COLUMN_NAME_DATA} = ?"
//            val selectionArgs = arrayOf("")

            // How you want the results sorted in the resulting Cursor
//            val sortOrder = "${COLUMN_NAME_SUBTITLE} DESC"

            val cursor = dbreadable?.query(
                    TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null               // The sort order
            )


            val itemIds = mutableListOf<Long>()
            var itemId = 0L
            var jsonFromDataBase = ""

            with(cursor) {
                while (this!!.moveToNext()) {
                    itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                    itemIds.add(itemId)

                    jsonFromDataBase = getString(getColumnIndexOrThrow(COLUMN_NAME_DATA))

                    if (itemId == 2L) {
                        var gson = Gson()
                        var user = gson.fromJson<User>(json, User::class.java)
                        Log.e("USER", user.toString())
                    }

                }
            }


            Toast.makeText(this, "onGetRow: " + itemId, Toast.LENGTH_SHORT).show()
        }

    }


    fun loadJSONFromAsset(file: String): String {

        var json: String = ""

        try {

            var inputStream: InputStream = assets.open(file)
            var size = inputStream.available()
            var buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)

        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return json
    }


}
