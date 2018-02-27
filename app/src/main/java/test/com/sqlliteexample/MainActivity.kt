package test.com.sqlliteexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json = loadJSONFromAsset("simulated_json/app_init.json")
        Log.e("JSON", json)

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
