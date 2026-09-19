package com.betwinner.shadowmarker.presentation.ui.tabs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.presentation.ui.components.ShadowMetricBadge
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowUiState
import kotlin.math.*

@Composable
fun ShadowSimulatorTab(
    uiState: ShadowUiState,
    onUpdateSim: (Float, Float, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val sim = uiState.simulationResult

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "PASSING LANE DENIAL SIMULATOR",
            color = ShadowGold,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
        )
        Text(
            text = "Calculate cover shadow cones, passing lane occlusion, and turnover forcing rates.",
            color = ShadowMutedGreen,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Visual Canvas Pitch Simulation
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Pitch grid lines
                    drawLine(
                        color = ShadowGreenPitch.copy(alpha = 0.5f),
                        start = Offset(0f, h / 2),
                        end = Offset(w, h / 2),
                        strokeWidth = 1.dp.toPx()
                    )
                    drawCircle(
                        color = ShadowGreenPitch.copy(alpha = 0.5f),
                        center = Offset(w / 2, h / 2),
                        radius = 45.dp.toPx(),
                        style = Stroke(width = 1.dp.toPx())
                    )

                    // Ball Carrier (Opponent with ball) at top center
                    val carrierX = w * 0.5f
                    val carrierY = h * 0.22f

                    // Shadow Marker (Our Midfielder) positioned below carrier
                    // Distance scales between 2m and 8m
                    val distFraction = (uiState.simDistanceMeters - 2f) / (8f - 2f)
                    val markerDistY = (45.dp.toPx()) + distFraction * (55.dp.toPx())
                    val markerX = w * 0.5f
                    val markerY = carrierY + markerDistY

                    // Cover Shadow Cone projected behind marker
                    val coneAngle = uiState.simAngleCoverageDegrees
                    val coneLength = 100.dp.toPx()
                    val halfAngleRad = Math.toRadians((coneAngle / 2.0)).toFloat()

                    val leftConeX = markerX - coneLength * sin(halfAngleRad)
                    val leftConeY = markerY + coneLength * cos(halfAngleRad)

                    val rightConeX = markerX + coneLength * sin(halfAngleRad)
                    val rightConeY = markerY + coneLength * cos(halfAngleRad)

                    val conePath = Path().apply {
                        moveTo(markerX, markerY)
                        lineTo(leftConeX, leftConeY)
                        lineTo(rightConeX, rightConeY)
                        close()
                    }

                    // Draw shaded cover cone
                    drawPath(
                        path = conePath,
                        color = ShadowGoldAmber.copy(alpha = pulseAlpha * 0.35f)
                    )
                    drawPath(
                        path = conePath,
                        color = ShadowGold.copy(alpha = 0.7f),
                        style = Stroke(width = 1.5.dp.toPx())
                    )

                    // Draw Opponent Receiver Targets
                    val options = uiState.simOptionsCount
                    for (i in 0 until options) {
                        val receiverX = when (options) {
                            1 -> w * 0.5f
                            2 -> if (i == 0) w * 0.42f else w * 0.58f
                            3 -> when (i) {
                                0 -> w * 0.32f
                                1 -> w * 0.5f
                                else -> w * 0.68f
                            }
                            else -> w * (0.25f + i * 0.16f)
                        }
                        val receiverY = h * 0.82f

                        // Target circle
                        drawCircle(
                            color = ShadowGreenElevated,
                            center = Offset(receiverX, receiverY),
                            radius = 12.dp.toPx()
                        )
                        drawCircle(
                            color = ShadowWhite,
                            center = Offset(receiverX, receiverY),
                            radius = 6.dp.toPx()
                        )

                        // Blocked line of pass indicator
                        drawLine(
                            color = Color.Red.copy(alpha = 0.5f),
                            start = Offset(carrierX, carrierY),
                            end = Offset(receiverX, receiverY),
                            strokeWidth = 1.5.dp.toPx(),
                            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(8f, 8f))
                        )
                    }

                    // Draw Marker
                    drawCircle(
                        color = ShadowGreenNeon,
                        center = Offset(markerX, markerY),
                        radius = 14.dp.toPx()
                    )
                    drawCircle(
                        color = ShadowGreenDark,
                        center = Offset(markerX, markerY),
                        radius = 8.dp.toPx()
                    )

                    // Draw Carrier
                    drawCircle(
                        color = ShadowGold,
                        center = Offset(carrierX, carrierY),
                        radius = 12.dp.toPx()
                    )
                    drawCircle(
                        color = Color.White,
                        center = Offset(carrierX + 6.dp.toPx(), carrierY - 6.dp.toPx()),
                        radius = 4.dp.toPx()
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "● Marker (Def)",
                        color = ShadowGreenNeon,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "● Carrier (Att)",
                        color = ShadowGold,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "■ Occluded Cone",
                        color = ShadowGoldAmber,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sliders & Controls
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ShadowGreenElevated),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGreenBorder)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Shadow Cone Arc Angle",
                        color = ShadowWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${uiState.simAngleCoverageDegrees.toInt()}°",
                        color = ShadowGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simAngleCoverageDegrees,
                    onValueChange = { onUpdateSim(it, uiState.simDistanceMeters, uiState.simOptionsCount) },
                    valueRange = 30f..110f,
                    colors = SliderDefaults.colors(
                        thumbColor = ShadowGold,
                        activeTrackColor = ShadowGold,
                        inactiveTrackColor = ShadowGreenPitch
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Distance to Carrier (Meters)",
                        color = ShadowWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("%.1f m", uiState.simDistanceMeters),
                        color = ShadowGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simDistanceMeters,
                    onValueChange = { onUpdateSim(uiState.simAngleCoverageDegrees, it, uiState.simOptionsCount) },
                    valueRange = 2.0f..8.0f,
                    colors = SliderDefaults.colors(
                        thumbColor = ShadowGreenNeon,
                        activeTrackColor = ShadowGreenNeon,
                        inactiveTrackColor = ShadowGreenPitch
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Downfield Passing Options",
                        color = ShadowWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        (1..4).forEach { count ->
                            val isSel = uiState.simOptionsCount == count
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(if (isSel) ShadowGold else ShadowGreenPitch)
                                    .border(1.dp, ShadowGreenBorder, CircleShape)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "$count",
                                    color = if (isSel) ShadowGreenDark else ShadowWhite,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Simulation Output
        if (sim != null) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ShadowGreenPitch),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ShadowGold)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TACTICAL EFFICIENCY OUTPUT",
                        color = ShadowGoldBright,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ShadowMetricBadge(
                            label = "Interception %",
                            value = "${sim.passInterceptionProbabilityPct}%",
                            modifier = Modifier.weight(1f)
                        )
                        ShadowMetricBadge(
                            label = "Forced Backpass",
                            value = "${sim.backwardPassForcedPct}%",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = sim.tacticalEvaluation,
                        color = ShadowSoftGray,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
