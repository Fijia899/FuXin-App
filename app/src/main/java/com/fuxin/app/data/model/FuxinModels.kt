package com.fuxin.app.data.model

data class GuestSession(val token: String, val expiresIn: Int)
data class RelationCase(val id: String, val status: String)
data class ReportSummary(val stage: String, val confidence: String, val facts: List<String>, val risks: List<String>, val uncertainty: String, val disclaimer: String)
