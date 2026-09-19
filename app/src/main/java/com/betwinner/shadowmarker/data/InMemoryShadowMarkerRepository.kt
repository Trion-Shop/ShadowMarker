package com.betwinner.shadowmarker.data

import com.betwinner.shadowmarker.domain.model.*
import com.betwinner.shadowmarker.domain.repository.ShadowMarkerRepository

class InMemoryShadowMarkerRepository : ShadowMarkerRepository {

    override suspend fun getCoverMechanics(): List<CoverShadowMechanic> = listOf(
        CoverShadowMechanic(
            id = "mech_curved_approach",
            mechanicName = "Curved Run Lateral Shadow Screen",
            shadowArchetype = "Curved Approach Funnel",
            blockedSectorAngleDegrees = 65.0f,
            approachArcCurvature = 0.85f,
            laneDenialEfficiencyPct = 84.2f,
            interceptedPassTransitionsPerMatch = 4.6f,
            biomechanicalMechanics = "Forward runs on a crescent-shaped arc towards opposing center-back. The body posture keeps the opposing central midfielder directly behind the presser's spine, casting an impenetrable passing shadow without turning head.",
            executionPrinciples = listOf(
                "Maintain curved sprint stride to keep midfield passing lane eclipsed",
                "Scan behind shoulder right before opponent initiates pass swing",
                "Funnel opponent ball carrier toward touchline trap"
            ),
            opponentCounterAdjustments = listOf(
                "Opponent center-back steps wide or lofts chipped pass over presser head"
            )
        ),
        CoverShadowMechanic(
            id = "mech_pivot_double_shadow",
            mechanicName = "Double Pivot Central Seam Shield",
            shadowArchetype = "Midfield Pivot Shield",
            blockedSectorAngleDegrees = 78.0f,
            approachArcCurvature = 0.40f,
            laneDenialEfficiencyPct = 89.0f,
            interceptedPassTransitionsPerMatch = 5.2f,
            biomechanicalMechanics = "Defensive midfielder shifts laterally in sync with the ball, positioning torso to obstruct ground passes into opponent attacking 10. Eyes stay locked on passer hip angle while peripheral vision senses runner.",
            executionPrinciples = listOf(
                "Never turn back to the ball; mirror ball carrier horizontal movements",
                "Keep base of support wide with knees bent to react to sudden passes",
                "Deny line-breaking ground balls straight through Zone 14"
            ),
            opponentCounterAdjustments = listOf(
                "Playmaker drops deeper behind defensive midfielder to drag him out"
            )
        ),
        CoverShadowMechanic(
            id = "mech_in_to_out_screen",
            mechanicName = "Inside-Out Touchline Screen",
            shadowArchetype = "In-to-Out Screen",
            blockedSectorAngleDegrees = 55.0f,
            approachArcCurvature = 0.60f,
            laneDenialEfficiencyPct = 81.5f,
            interceptedPassTransitionsPerMatch = 3.8f,
            biomechanicalMechanics = "Winger positions inside the pitch corridor and charges outward towards fullback, blocking inward passes back to central midfielders and forcing opponent into corner dead-ends.",
            executionPrinciples = listOf(
                "Start run 2 yards inside central passing vector",
                "Accelerate with arms slightly spread to maximize physical silhouette",
                "Close down until carrier is forced into rushed kick out of bounds"
            ),
            opponentCounterAdjustments = listOf(
                "Fullback plays first-time down-the-line clipped pass"
            )
        )
    )

    override suspend fun getShadowDrills(): List<ShadowDrillProtocol> = listOf(
        ShadowDrillProtocol(
            id = "drill_3v3_shadow_cage",
            drillTitle = "3v3 Floating Neutral Passing-Lane Lockdown",
            drillFocus = "Cover-Shadow Blindside Positioning",
            gridDimensionsMeters = "20m x 15m Central Box",
            targetLaneBlocksCount = 18,
            scanningFrequencyPer5Sec = 4,
            drillObjectives = listOf(
                "Eliminate direct passes through central zone without physically tackling",
                "Cast cover-shadow that forces lateral or backward circulating passes",
                "Develop dynamic neck rotations to check shadow alignment every 1.5 seconds"
            ),
            trainingProgressions = listOf(
                "Phase 1: Shadow defender mirrors passer without tackling",
                "Phase 2: Add neutral target player behind shadow who must not receive ball",
                "Phase 3: Live 3v3 transition upon successful interception"
            )
        ),
        ShadowDrillProtocol(
            id = "drill_curved_funnel_circuit",
            drillTitle = "High-Intensity Curved Press Funneling",
            drillFocus = "Curved Approach Funnel",
            gridDimensionsMeters = "35m Half-Pitch Channel",
            targetLaneBlocksCount = 14,
            scanningFrequencyPer5Sec = 3,
            drillObjectives = listOf(
                "Execute curved sprint mechanics at 28km/h while shielding pivot",
                "Deny central switch and force ball onto opponent weak foot",
                "Win possession within 5 seconds of funnel trigger"
            ),
            trainingProgressions = listOf(
                "Phase 1: Cone-guided curved arc sprint mechanics",
                "Phase 2: Live center-back distribution with active midfielder runner",
                "Phase 3: Full team pressing trigger synchronization"
            )
        )
    )

    override suspend fun getShadowMasters(): List<CoverShadowMaster> = listOf(
        CoverShadowMaster(
            id = "mas_busquets",
            masterName = "Sergio Busquets",
            legendaryClub = "FC Barcelona / Spain",
            careerPassingLanesDenied = 620,
            avgCoverShadowAngleDegrees = 76.5f,
            signatureScreenManeuver = "The Radar Pivot: Shadow Screen without Sprinting",
            tacticalQuote = "You look at the game, you don't see Busquets. You watch Busquets, you see the entire game. I never ran fast; I simply closed the door before they saw it."
        ),
        CoverShadowMaster(
            id = "mas_rodri",
            masterName = "Rodri",
            legendaryClub = "Manchester City / Spain",
            careerPassingLanesDenied = 490,
            avgCoverShadowAngleDegrees = 81.0f,
            signatureScreenManeuver = "Midfield Wall Shadow & Immediate Forward Transition",
            tacticalQuote = "Cover-shadowing is psychological warfare. When the passer looks up and only sees your silhouette, he begins to panic."
        ),
        CoverShadowMaster(
            id = "mas_casemiro",
            masterName = "Casemiro",
            legendaryClub = "Real Madrid / Brazil",
            careerPassingLanesDenied = 530,
            avgCoverShadowAngleDegrees = 72.0f,
            signatureScreenManeuver = "Aggressive Snapping Shadow & Physical Interception",
            tacticalQuote = "Position yourself so that the ball carrier thinks the pass is open, then the moment he kicks it, step across his vision and crush the counter."
        )
    )

    override suspend fun getMatchClashes(): List<ShadowMarkerMatchClash> = listOf(
        ShadowMarkerMatchClash(
            id = "clash_city_inter_2023",
            matchHeadline = "Champions League Final: Rodri Masterclass Shadow Screen",
            teamA = "Manchester City",
            teamB = "Inter Milan",
            finalScore = "1 - 0",
            keyShadowOperator = "Rodri",
            passesDeniedCount = 28,
            turnoversCreatedInMidfield = 9,
            tacticalReport = "Rodri suffocated Inter Milan's central progressions by constantly casting his cover shadow over Barella and Calhanoglu, eventually scoring the match-winner.",
            decisiveShadowSequences = listOf(
                DecisiveShadowSequence(34, "Rodri", "Bastoni", "Calhanoglu", "Forced long clearance out of play"),
                DecisiveShadowSequence(58, "Rodri", "Brozovic", "Lautaro Martinez", "Inter turnover leads to City counter-attack"),
                DecisiveShadowSequence(68, "Rodri", "Darmian", "Dimarco", "Intercepted layoff leading directly to Rodri 1-0 winner")
            )
        ),
        ShadowMarkerMatchClash(
            id = "clash_spain_italy_2012",
            matchHeadline = "Euro 2012 Final: Total Passing Denial Tiki-Taka Shield",
            teamA = "Spain",
            teamB = "Italy",
            finalScore = "4 - 0",
            keyShadowOperator = "Sergio Busquets",
            passesDeniedCount = 35,
            turnoversCreatedInMidfield = 12,
            tacticalReport = "Sergio Busquets cast an unbroken shadow over Andrea Pirlo, disconnecting Italy's deep playmaker from his front runners and securing a 4-0 demolition.",
            decisiveShadowSequences = listOf(
                DecisiveShadowSequence(14, "Busquets", "De Rossi", "Andrea Pirlo", "Pirlo starved; Fabregas crosses for Silva 1-0"),
                DecisiveShadowSequence(41, "Busquets", "Chiellini", "Cassano", "Interception sets up Jordi Alba solo strike 2-0"),
                DecisiveShadowSequence(84, "Busquets", "Motta", "Balotelli", "Turnover enables Torres and Mata 4-0 rout")
            )
        ),
        ShadowMarkerMatchClash(
            id = "clash_chelsea_city_2021",
            matchHeadline = "Porto Final 2021: Kante Infinite Shadow Suffocation",
            teamA = "Chelsea FC",
            teamB = "Manchester City",
            finalScore = "1 - 0",
            keyShadowOperator = "N'Golo Kanté",
            passesDeniedCount = 31,
            turnoversCreatedInMidfield = 11,
            tacticalReport = "Kanté was awarded Man of the Match after systematically eliminating Kevin De Bruyne and Ilkay Gündogan through predictive cover-shadow interceptions.",
            decisiveShadowSequences = listOf(
                DecisiveShadowSequence(21, "Kanté", "Stones", "Kevin De Bruyne", "Blocked passing channel at midfield line"),
                DecisiveShadowSequence(42, "Kanté", "Walker", "Foden", "Cover shadow triggers transition for Havertz goal 1-0"),
                DecisiveShadowSequence(74, "Kanté", "Dias", "Bernardo Silva", "Crucial interception inside Chelsea defensive third")
            )
        )
    )

    override fun calculateShadowSimulation(
        angleDegrees: Float,
        distanceMeters: Float,
        options: Int
    ): CoverShadowSimulation {
        val distanceEfficiency = (15f - distanceMeters).coerceAtLeast(1f) / 15f
        val angleFactor = (angleDegrees / 90f).coerceIn(0.3f, 1.0f)
        val baseProb = (angleFactor * 65f) + (distanceEfficiency * 30f) - (options * 3.5f)
        val interceptProb = baseProb.toInt().coerceIn(30, 96)
        val backwardForced = (interceptProb * 0.9f).toInt().coerceIn(25, 92)

        val verdict = when {
            interceptProb >= 80 -> "Impenetrable cover-shadow: Target receiver is completely eclipsed. Ball carrier forced into panic backward pass."
            interceptProb >= 60 -> "Strong spatial denial: Only risky aerial chip passes can bypass the shadow sector."
            else -> "Incomplete screen: Distance too large allows ball carrier to penetrate through the open flank seam."
        }

        return CoverShadowSimulation(
            shadowAngleCoverageDegrees = angleDegrees,
            markerDistanceFromBallCarrierMeters = distanceMeters,
            carrierPassingOptionCount = options,
            passInterceptionProbabilityPct = interceptProb,
            backwardPassForcedPct = backwardForced,
            tacticalEvaluation = verdict
        )
    }
}
