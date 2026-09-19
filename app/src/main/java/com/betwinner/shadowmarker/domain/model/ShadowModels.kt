package com.betwinner.shadowmarker.domain.model

data class CoverShadowMechanic(
    val id: String,
    val mechanicName: String,
    val shadowArchetype: String, // Blindside Passing Lane Block, Curved Approach Funnel, Midfield Pivot Shield, In-to-Out Screen
    val blockedSectorAngleDegrees: Float,
    val approachArcCurvature: Float,
    val laneDenialEfficiencyPct: Float,
    val interceptedPassTransitionsPerMatch: Float,
    val biomechanicalMechanics: String,
    val executionPrinciples: List<String>,
    val opponentCounterAdjustments: List<String>
)

data class ShadowDrillProtocol(
    val id: String,
    val drillTitle: String,
    val drillFocus: String, // 3v3 + 1 Floating Neutral Cover-Shadow, Curved Pressing Funnel, Split-Pass Interception
    val gridDimensionsMeters: String,
    val targetLaneBlocksCount: Int,
    val scanningFrequencyPer5Sec: Int,
    val drillObjectives: List<String>,
    val trainingProgressions: List<String>
)

data class CoverShadowMaster(
    val id: String,
    val masterName: String,
    val legendaryClub: String,
    val careerPassingLanesDenied: Int,
    val avgCoverShadowAngleDegrees: Float,
    val signatureScreenManeuver: String,
    val tacticalQuote: String
)

data class ShadowMarkerMatchClash(
    val id: String,
    val matchHeadline: String,
    val teamA: String,
    val teamB: String,
    val finalScore: String,
    val keyShadowOperator: String,
    val passesDeniedCount: Int,
    val turnoversCreatedInMidfield: Int,
    val tacticalReport: String,
    val decisiveShadowSequences: List<DecisiveShadowSequence>
)

data class DecisiveShadowSequence(
    val minute: Int,
    val marker: String,
    val blockedPasser: String,
    val starvedReceiver: String,
    val resultantTurnover: String
)

data class CoverShadowSimulation(
    val shadowAngleCoverageDegrees: Float,
    val markerDistanceFromBallCarrierMeters: Float,
    val carrierPassingOptionCount: Int,
    val passInterceptionProbabilityPct: Int,
    val backwardPassForcedPct: Int,
    val tacticalEvaluation: String
)
