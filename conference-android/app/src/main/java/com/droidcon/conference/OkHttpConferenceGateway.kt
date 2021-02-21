package com.droidcon.conference

import okhttp3.*
import org.json.JSONArray
import java.io.IOException
import kotlin.coroutines.suspendCoroutine

class OkHttpConferenceGateway(
    private val baseUrl: String,
    private val httpClient: OkHttpClient
) : ConferenceGateway {
    override suspend fun getConference(): Conference {
        val request = Request.Builder()
            .url("${baseUrl}conferences")
            .get()
            .build()

        return suspendCoroutine { block ->
            httpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    block.resumeWith(Result.failure(e))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful) {
                        block.resumeWith(Result.failure(IOException()))
                    } else {
                        val conference = parseConference(
                            response.body!!.string()
                        )
                        block.resumeWith(Result.success(conference))
                    }
                }
            })
        }
    }

    private fun parseConference(json: String): Conference {
        val conferenceJson = JSONArray(json).getJSONObject(0)
        return Conference(conferenceJson.getLong("id"), conferenceJson.getString("name"))
    }
}
