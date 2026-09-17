package com.fuxin.app.data.remote

import com.fuxin.app.data.model.GuestSession
import com.fuxin.app.data.model.RelationCase
import com.fuxin.app.data.model.ReportSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class FuxinApiClient(private val baseUrl: String = DEFAULT_BASE_URL) {
    suspend fun createGuestSession(): GuestSession = withContext(Dispatchers.IO) {
        val json = request("POST", "/api/v1/guest/session", "{}")
        GuestSession(json.getString("token"), json.getInt("expiresIn"))
    }
    suspend fun createCase(token: String): RelationCase = withContext(Dispatchers.IO) {
        val json = request("POST", "/api/v1/cases", "{}", token)
        RelationCase(json.getString("id"), "draft")
    }
    suspend fun submitDiagnosis(caseId: String, answers: Map<String, String>): RelationCase = withContext(Dispatchers.IO) {
        val json = request("POST", "/api/v1/cases/$caseId/diagnosis", JSONObject(answers).toString())
        RelationCase(json.getString("id"), json.getString("status"))
    }
    suspend fun generateReport(caseId: String, extra: String = ""): ReportSummary = withContext(Dispatchers.IO) {
        parseReport(request("POST", "/api/v1/cases/$caseId/inputs", JSONObject().put("text", extra).toString()).getJSONObject("report"))
    }
    private fun request(method: String, path: String, body: String?, token: String? = null): JSONObject {
        val connection = (URL(baseUrl.trimEnd('/') + path).openConnection() as HttpURLConnection).apply {
            requestMethod = method; connectTimeout = 8_000; readTimeout = 12_000; doInput = true
            setRequestProperty("Content-Type", "application/json"); token?.let { setRequestProperty("Authorization", "Bearer $it") }
            if (body != null) { doOutput = true; outputStream.use { it.write(body.toByteArray()) } }
        }
        return try { val stream = if (connection.responseCode in 200..299) connection.inputStream else connection.errorStream; val text = stream?.bufferedReader()?.use { it.readText() }.orEmpty(); if (connection.responseCode !in 200..299) error("Fuxin API ${connection.responseCode}: $text"); JSONObject(text) } finally { connection.disconnect() }
    }
    private fun parseReport(json: JSONObject) = ReportSummary(json.optString("stage"), json.optString("confidence"), json.optJSONArray("facts").toStrings(), json.optJSONArray("risks").toStrings(), json.optString("uncertainty"), json.optString("disclaimer"))
    private fun JSONArray?.toStrings(): List<String> = if (this == null) emptyList() else List(length()) { getString(it) }
    companion object { const val DEFAULT_BASE_URL = "http://10.0.2.2:3000" }
}
