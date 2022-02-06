package xyz.savvamirzoyan.eposea.ui

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val BASE_URL = "https://example.com/"

@ExperimentalSerializationApi
class App : Application() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder().build()
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        val resourceManager = ResourceManager.Base(applicationContext)
    }
}