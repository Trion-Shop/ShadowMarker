package com.betwinner.shadowmarker.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betwinner.shadowmarker.core.theme.*
import com.betwinner.shadowmarker.presentation.ui.components.*
import com.betwinner.shadowmarker.presentation.ui.tabs.*
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowViewModel

@Composable
fun ShadowMarkerScreen(
    viewModel: ShadowViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val tabTitles = listOf("Simulator", "Mechanics", "Drills", "Matches")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ShadowBgGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ShadowHeader(
                title = "Shadow Marker",
                subtitle = "Cover-Shadow Passing Lane Occlusion Lab"
            )

            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = ShadowGreenDark,
                contentColor = ShadowGold,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                        color = ShadowGold,
                        height = 3.dp
                    )
                },
                divider = {
                    HorizontalDivider(color = ShadowGreenBorder.copy(alpha = 0.4f))
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.onSelectTab(index) },
                        text = {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = if (uiState.selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                                color = if (uiState.selectedTab == index) ShadowGold else ShadowMutedGreen
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedTab) {
                    0 -> ShadowSimulatorTab(
                        uiState = uiState,
                        onUpdateSim = { angle, dist, options ->
                            viewModel.updateSimulation(angle, dist, options)
                        }
                    )
                    1 -> CoverMechanicsTab(
                        uiState = uiState,
                        onSelectMechanic = { viewModel.onSelectMechanic(it) }
                    )
                    2 -> ShadowDrillsTab(
                        uiState = uiState,
                        onSelectDrill = { viewModel.onSelectDrill(it) },
                        onSelectMaster = { viewModel.onSelectMaster(it) }
                    )
                    3 -> ShadowClashesTab(
                        uiState = uiState,
                        onSelectClash = { viewModel.onSelectClash(it) }
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
