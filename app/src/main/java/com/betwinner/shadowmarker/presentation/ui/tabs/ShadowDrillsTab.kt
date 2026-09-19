package com.betwinner.shadowmarker.presentation.ui.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.ShadowGold
import com.betwinner.shadowmarker.core.theme.ShadowMutedGreen
import com.betwinner.shadowmarker.domain.model.CoverShadowMaster
import com.betwinner.shadowmarker.domain.model.ShadowDrillProtocol
import com.betwinner.shadowmarker.presentation.ui.components.ShadowDrillCard
import com.betwinner.shadowmarker.presentation.ui.components.ShadowMasterCard
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowUiState

@Composable
fun ShadowDrillsTab(
    uiState: ShadowUiState,
    onSelectDrill: (ShadowDrillProtocol) -> Unit,
    onSelectMaster: (CoverShadowMaster) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        item {
            Column {
                Text(
                    text = "LANE-DENIAL TRAINING LAB",
                    color = ShadowGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Targeted tactical drills designed to drill peripheral scanning and lane occlusion.",
                    color = ShadowMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.drills, key = { it.id }) { drill ->
            ShadowDrillCard(
                drill = drill,
                onClick = { onSelectDrill(drill) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(
                    text = "LEGENDARY SHADOW MASTERS",
                    color = ShadowGold,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Elite midfielders who mastered the art of winning the ball without tackling.",
                    color = ShadowMutedGreen,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.masters, key = { it.id }) { master ->
            ShadowMasterCard(
                master = master,
                onClick = { onSelectMaster(master) }
            )
        }
    }
}
