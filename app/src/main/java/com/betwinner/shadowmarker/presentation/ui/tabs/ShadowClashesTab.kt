package com.betwinner.shadowmarker.presentation.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.domain.model.CoverShadowMaster
import com.betwinner.shadowmarker.domain.model.ShadowMarkerMatchClash
import com.betwinner.shadowmarker.presentation.ui.components.ShadowMatchClashCard
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowUiState

@Composable
fun ShadowClashesTab(
    uiState: ShadowUiState,
    onSelectClash: (ShadowMarkerMatchClash) -> Unit,
    onSelectMaster: (CoverShadowMaster) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        // Carousel of Legendary Shadow Masters
        item {
            Column {
                Text(
                    text = "LEGENDARY SHADOW MASTERS",
                    color = ShadowGold,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.masters, key = { it.id }) { master ->
                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .clickable { onSelectMaster(master) }
                                .border(1.dp, ShadowGreenBorder, RoundedCornerShape(14.dp)),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = ShadowGreenDark)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = master.masterName,
                                        color = ShadowGoldBright,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(ShadowGreenElevated)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "${master.avgCoverShadowAngleDegrees.toInt()}°",
                                            color = ShadowGreenNeon,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Text(
                                    text = master.legendaryClub,
                                    color = ShadowMutedGreen,
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "“${master.tacticalQuote}”",
                                    color = ShadowSoftGray,
                                    fontSize = 10.sp,
                                    maxLines = 2,
                                    lineHeight = 13.sp,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "HISTORIC SHADOW CLASHES",
                    color = ShadowGold,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Tactical match battles where cover shadows dictated championship outcomes.",
                    color = ShadowMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.clashes, key = { it.id }) { clash ->
            ShadowMatchClashCard(
                clash = clash,
                onClick = { onSelectClash(clash) }
            )
        }
    }
}
