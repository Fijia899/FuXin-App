package com.fuxin.app

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import com.fuxin.app.ui.theme.Coral
import com.fuxin.app.ui.theme.ElectricPurple
import com.fuxin.app.ui.theme.Night
import com.fuxin.app.ui.theme.Orange

class FuxinDemoTest {
    @Test
    fun homeExposesThreeModulesWithDirectFeatures() {
        assertEquals(listOf("healing", "reconciliation", "detachment"), modules.map { it.id })
        assertTrue(modules.all { it.features.size >= 8 })
    }

    @Test
    fun coreFeatureNamesAreInTheCorrectModule() {
        assertTrue(modules.first { it.id == "healing" }.features.any { it.id == "companion" })
        assertTrue(modules.first { it.id == "reconciliation" }.features.any { it.id == "risk-check" })
        assertTrue(modules.first { it.id == "detachment" }.features.any { it.id == "urge-delay" })
    }

    @Test
    fun modulesHaveDistinctEmotionalAccentColors() {
        assertTrue(Night != Coral)
        assertTrue(Coral != Orange)
        assertTrue(Orange != ElectricPurple)
    }

    @Test
    fun everyModuleHasEnoughFeaturesForCardGrid() {
        assertTrue(modules.all { it.features.size >= 8 })
        assertTrue(modules.all { it.features.isNotEmpty() })
    }
}
