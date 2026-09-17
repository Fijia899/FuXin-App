package com.fuxin.app.data.local

import android.content.Context

class FuxinLocalStore(context: Context) {
    private val preferences = context.getSharedPreferences("fuxin_session", Context.MODE_PRIVATE)

    fun loadMood(): Int = preferences.getInt(KEY_MOOD, 6)

    fun saveMood(value: Int) { preferences.edit().putInt(KEY_MOOD, value.coerceIn(1, 10)).apply() }

    fun loadDraft(): String = preferences.getString(KEY_DRAFT, "") ?: ""

    fun saveDraft(value: String) { preferences.edit().putString(KEY_DRAFT, value).apply() }

    fun loadDiary(): List<String> = preferences.getStringSet(KEY_DIARY, emptySet()).orEmpty().toList()
        .ifEmpty { listOf("今天先把想说的话写下来，不急着发送。") }

    fun addDiary(value: String) {
        val entries = preferences.getStringSet(KEY_DIARY, emptySet()).orEmpty().toMutableSet()
        entries.add(value)
        preferences.edit().putStringSet(KEY_DIARY, entries).apply()
    }

    fun clearAll() { preferences.edit().clear().apply() }

    private companion object {
        const val KEY_MOOD = "mood"
        const val KEY_DRAFT = "draft"
        const val KEY_DIARY = "diary"
    }
}
