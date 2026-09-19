package com.betwinner.shadowmarker.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.domain.model.*

@Composable
fun MechanicDetailModal(
    mechanic: CoverShadowMechanic,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenNeon)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = mechanic.mechanicName.uppercase(),
                    color = ShadowGold,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = mechanic.shadowArchetype,
                    color = ShadowGreenNeon,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ShadowMetricBadge(
                        label = "Blocked Angle",
                        value = "${mechanic.blockedSectorAngleDegrees.toInt()}°",
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Efficiency",
                        value = "${mechanic.laneDenialEfficiencyPct.toInt()}%",
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Transitions/G",
                        value = "${mechanic.interceptedPassTransitionsPerMatch}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Biomechanical Execution",
                    color = ShadowWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = mechanic.biomechanicalMechanics,
                    color = ShadowSoftGray,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Core Principles",
                    color = ShadowGoldBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                mechanic.executionPrinciples.forEach { principle ->
                    Text(
                        text = "• $principle",
                        color = ShadowMutedGreen,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Counter Adjustments",
                    color = ShadowGoldBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                mechanic.opponentCounterAdjustments.forEach { adj ->
                    Text(
                        text = "• $adj",
                        color = ShadowSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShadowGreenPitch),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShadowGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun DrillDetailModal(
    drill: ShadowDrillProtocol,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = drill.drillTitle.uppercase(),
                    color = ShadowGoldBright,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = drill.drillFocus,
                    color = ShadowMutedGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ShadowMetricBadge(
                        label = "Grid Size",
                        value = drill.gridDimensionsMeters,
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Target Blocks",
                        value = "${drill.targetLaneBlocksCount}",
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Scan Rate",
                        value = "${drill.scanningFrequencyPer5Sec}/5s",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Drill Tactical Objectives",
                    color = ShadowWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.drillObjectives.forEach { obj ->
                    Text(
                        text = "• $obj",
                        color = ShadowSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Training Progressions",
                    color = ShadowGreenNeon,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.trainingProgressions.forEachIndexed { idx, prog ->
                    Text(
                        text = "${idx + 1}. $prog",
                        color = ShadowMutedGreen,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShadowGreenPitch),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShadowGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MasterDetailModal(
    master: CoverShadowMaster,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGoldBright)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = master.masterName.uppercase(),
                    color = ShadowGoldBright,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = master.legendaryClub,
                    color = ShadowMutedGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ShadowMetricBadge(
                        label = "Lanes Denied",
                        value = "${master.careerPassingLanesDenied}",
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Avg Angle",
                        value = "${master.avgCoverShadowAngleDegrees.toInt()}°",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Signature Screen Maneuver",
                    color = ShadowWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = master.signatureScreenManeuver,
                    color = ShadowGreenNeon,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Tactical Philosophy",
                    color = ShadowGoldAmber,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "“${master.tacticalQuote}”",
                    color = ShadowSoftGray,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShadowGreenPitch),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShadowGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ClashDetailModal(
    clash: ShadowMarkerMatchClash,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = clash.matchHeadline.uppercase(),
                        color = ShadowGold,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = clash.finalScore,
                        color = ShadowWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${clash.teamA} vs ${clash.teamB}",
                    color = ShadowGreenNeon,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ShadowMetricBadge(
                        label = "Denied Passes",
                        value = "${clash.passesDeniedCount}",
                        modifier = Modifier.weight(1f)
                    )
                    ShadowMetricBadge(
                        label = "Turnovers",
                        value = "${clash.turnoversCreatedInMidfield}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Assessment",
                    color = ShadowWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clash.tacticalReport,
                    color = ShadowSoftGray,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Decisive Shadow Sequences",
                    color = ShadowGoldBright,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                clash.decisiveShadowSequences.forEach { seq ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = ShadowGreenElevated),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${seq.minute}' | ${seq.marker}",
                                    color = ShadowGold,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "DENIED",
                                    color = ShadowGreenNeon,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Blocked: ${seq.blockedPasser} → ${seq.starvedReceiver}",
                                color = ShadowWhite,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Outcome: ${seq.resultantTurnover}",
                                color = ShadowMutedGreen,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ShadowGreenPitch),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ShadowGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
