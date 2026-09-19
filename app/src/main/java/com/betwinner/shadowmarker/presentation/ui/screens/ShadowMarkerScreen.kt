package com.betwinner.shadowmarker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.presentation.ui.components.*
import com.betwinner.shadowmarker.presentation.ui.tabs.*
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowViewModel

data class ShadowNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun ShadowMarkerScreen(
    viewModel: ShadowViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val navItems = listOf(
        ShadowNavItem("Screening", Icons.Default.SportsSoccer),
        ShadowNavItem("Radar Sim", Icons.Default.Radar),
        ShadowNavItem("Mechanics", Icons.Default.Shield),
        ShadowNavItem("Drills", Icons.Default.FitnessCenter)
    )

    Scaffold(
        bottomBar = {
            // Dark Obsidian Inset Island Bottom Bar with emerald laser border glow
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                shape = RoundedCornerShape(24.dp),
                color = ShadowGreenDark.copy(alpha = 0.95f),
                shadowElevation = 12.dp,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, ShadowGreenNeon.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEachIndexed { index, item ->
                        val isSelected = uiState.selectedTab == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(18.dp))
                                .background(if (isSelected) ShadowGreenPitch else Color.Transparent)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { viewModel.onSelectTab(index) }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    tint = if (isSelected) ShadowGreenNeon else ShadowMutedGreen.copy(alpha = 0.6f),
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = item.title,
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Normal,
                                    color = if (isSelected) ShadowGold else ShadowMutedGreen.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(ShadowBgGradient)
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ShadowHeader(
                    title = "Shadow Marker",
                    subtitle = "Cover-Shadow Passing Lane Occlusion Lab"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    when (uiState.selectedTab) {
                        0 -> ShadowClashesTab(
                            uiState = uiState,
                            onSelectClash = { viewModel.onSelectClash(it) },
                            onSelectMaster = { viewModel.onSelectMaster(it) }
                        )
                        1 -> ShadowSimulatorTab(
                            uiState = uiState,
                            onUpdateSim = { angle, dist, options ->
                                viewModel.updateSimulation(angle, dist, options)
                            }
                        )
                        2 -> CoverMechanicsTab(
                            uiState = uiState,
                            onSelectMechanic = { viewModel.onSelectMechanic(it) }
                        )
                        3 -> ShadowDrillsTab(
                            uiState = uiState,
                            onSelectDrill = { viewModel.onSelectDrill(it) },
                            onSelectMaster = { viewModel.onSelectMaster(it) }
                        )
                    }
                }
            }

            // Active Deep Dive Modals
            uiState.activeMechanicModal?.let { mechanic ->
                MechanicDetailModal(
                    mechanic = mechanic,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeDrillModal?.let { drill ->
                DrillDetailModal(
                    drill = drill,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeMasterModal?.let { master ->
                MasterDetailModal(
                    master = master,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeClashModal?.let { clash ->
                ClashDetailModal(
                    clash = clash,
                    onDismiss = { viewModel.dismissModal() }
                )
            }
        }
    }
}
