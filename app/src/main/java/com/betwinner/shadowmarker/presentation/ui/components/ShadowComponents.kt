package com.betwinner.shadowmarker.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.domain.model.*

@Composable
fun ShadowHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ShadowGreenPitch,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .border(
                width = 1.dp,
                color = ShadowGreenBorder,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = title.uppercase(),
                    color = ShadowGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = ShadowMutedGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ShadowGreenDark)
                    .border(1.dp, ShadowGoldAmber, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "LANE DENIAL",
                    color = ShadowGoldBright,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun ShadowMetricBadge(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(ShadowGreenDark.copy(alpha = 0.8f))
            .border(1.dp, ShadowGreenBorder.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = ShadowGold,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label.uppercase(),
            color = ShadowMutedGreen,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CoverMechanicCard(
    mechanic: CoverShadowMechanic,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ShadowGreenElevated),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mechanic.mechanicName,
                    color = ShadowWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ShadowGreenDark)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${mechanic.laneDenialEfficiencyPct.toInt()}% DENIAL",
                        color = ShadowGreenNeon,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = mechanic.shadowArchetype,
                color = ShadowGold,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mechanic.biomechanicalMechanics,
                color = ShadowSoftGray,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ShadowMetricBadge(
                    label = "Shadow Cone",
                    value = "${mechanic.blockedSectorAngleDegrees.toInt()}°",
                    modifier = Modifier.weight(1f)
                )
                ShadowMetricBadge(
                    label = "Approach Arc",
                    value = "${mechanic.approachArcCurvature}m",
                    modifier = Modifier.weight(1f)
                )
                ShadowMetricBadge(
                    label = "Turnovers/G",
                    value = "${mechanic.interceptedPassTransitionsPerMatch}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ShadowDrillCard(
    drill: ShadowDrillProtocol,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ShadowGreenElevated),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drill.drillTitle,
                    color = ShadowWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ShadowGoldAmber.copy(alpha = 0.2f))
                        .border(1.dp, ShadowGoldAmber, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = drill.gridDimensionsMeters,
                        color = ShadowGoldBright,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = drill.drillFocus,
                color = ShadowMutedGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ShadowMetricBadge(
                    label = "Target Blocks",
                    value = "${drill.targetLaneBlocksCount}",
                    modifier = Modifier.weight(1f)
                )
                ShadowMetricBadge(
                    label = "Scanning Freq",
                    value = "${drill.scanningFrequencyPer5Sec}/5s",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ShadowMasterCard(
    master: CoverShadowMaster,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ShadowGreenPitch),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGoldAmber))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = master.masterName,
                        color = ShadowGoldBright,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = master.legendaryClub,
                        color = ShadowMutedGreen,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(ShadowGreenDark)
                        .border(1.dp, ShadowGreenNeon, CircleShape)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${master.avgCoverShadowAngleDegrees.toInt()}°",
                        color = ShadowGreenNeon,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "“${master.tacticalQuote}”",
                color = ShadowSoftGray,
                fontSize = 12.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Screen: ${master.signatureScreenManeuver}",
                color = ShadowWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun ShadowMatchClashCard(
    clash: ShadowMarkerMatchClash,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = ShadowGreenPitch),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = clash.matchHeadline,
                    color = ShadowGold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ShadowGreenDark)
                        .border(1.dp, ShadowGreenBorder, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = clash.finalScore,
                        color = ShadowWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${clash.teamA} vs ${clash.teamB}",
                color = ShadowWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Key Marker: ${clash.keyShadowOperator}",
                color = ShadowGreenNeon,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = clash.tacticalReport,
                color = ShadowSoftGray,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))
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
                    label = "Midfield Turnovers",
                    value = "${clash.turnoversCreatedInMidfield}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
