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
import com.betwinner.shadowmarker.domain.model.ShadowMarkerMatchClash
import com.betwinner.shadowmarker.presentation.ui.components.ShadowMatchClashCard
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowUiState

@Composable
fun ShadowClashesTab(
    uiState: ShadowUiState,
    onSelectClash: (ShadowMarkerMatchClash) -> Unit,
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
                    text = "HISTORIC SHADOW CLASHES",
                    color = ShadowGold,
                    fontSize = 16.sp,
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
