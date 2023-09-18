package com.example.horoscopeapp.data.network

import android.util.Log
import com.example.horoscopeapp.data.network.dto.Age
import com.example.horoscopeapp.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class AgeService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getAge(name: String): Age {
        return try {
            client.get{
                val ageURL = Constants.BASE_URL + "?name=${name.toLowerCase()}"
                url(ageURL)
            }
        } catch(e: RedirectResponseException){
            Log.d("TAG300", e.response.status.description)
            Age()
        } catch(e: ClientRequestException){
            Log.d("TAG400", e.response.status.description)
            Age()
        } catch(e: ServerResponseException){
            Log.d("TAG500", e.response.status.description)
            Age()
        }
    }
}