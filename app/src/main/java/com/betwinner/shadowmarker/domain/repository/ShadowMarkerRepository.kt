package com.betwinner.shadowmarker.domain.repository

import com.betwinner.shadowmarker.domain.model.*

interface ShadowMarkerRepository {
    suspend fun getCoverMechanics(): List<CoverShadowMechanic>
    suspend fun getShadowDrills(): List<ShadowDrillProtocol>
    suspend fun getShadowMasters(): List<CoverShadowMaster>
    suspend fun getMatchClashes(): List<ShadowMarkerMatchClash>
    fun calculateShadowSimulation(angleDegrees: Float, distanceMeters: Float, options: Int): CoverShadowSimulation
}
