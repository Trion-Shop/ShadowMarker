package com.betwinner.shadowmarker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betwinner.shadowmarker.data.InMemoryShadowMarkerRepository
import com.betwinner.shadowmarker.domain.model.*
import com.betwinner.shadowmarker.domain.repository.ShadowMarkerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ShadowUiState(
    val selectedTab: Int = 0,
    val mechanics: List<CoverShadowMechanic> = emptyList(),
    val drills: List<ShadowDrillProtocol> = emptyList(),
    val masters: List<CoverShadowMaster> = emptyList(),
    val clashes: List<ShadowMarkerMatchClash> = emptyList(),
    // Simulator inputs
    val simAngleCoverageDegrees: Float = 65.0f,
    val simDistanceMeters: Float = 4.5f,
    val simOptionsCount: Int = 2,
    val simulationResult: CoverShadowSimulation? = null,
    // Active Modals
    val activeMechanicModal: CoverShadowMechanic? = null,
    val activeDrillModal: ShadowDrillProtocol? = null,
    val activeMasterModal: CoverShadowMaster? = null,
    val activeClashModal: ShadowMarkerMatchClash? = null
)

class ShadowViewModel(
    private val repository: ShadowMarkerRepository = InMemoryShadowMarkerRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShadowUiState())
    val uiState: StateFlow<ShadowUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val mechs = repository.getCoverMechanics()
            val drs = repository.getShadowDrills()
            val msts = repository.getShadowMasters()
            val cls = repository.getMatchClashes()
            val initSim = repository.calculateShadowSimulation(65.0f, 4.5f, 2)

            _uiState.update {
                it.copy(
                    mechanics = mechs,
                    drills = drs,
                    masters = msts,
                    clashes = cls,
                    simulationResult = initSim
                )
            }
        }
    }

    fun onSelectTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedTab = tabIndex) }
    }

    fun onSelectMechanic(mechanic: CoverShadowMechanic) {
        _uiState.update { it.copy(activeMechanicModal = mechanic) }
    }

    fun onSelectDrill(drill: ShadowDrillProtocol) {
        _uiState.update { it.copy(activeDrillModal = drill) }
    }

    fun onSelectMaster(master: CoverShadowMaster) {
        _uiState.update { it.copy(activeMasterModal = master) }
    }

    fun onSelectClash(clash: ShadowMarkerMatchClash) {
        _uiState.update { it.copy(activeClashModal = clash) }
    }

    fun dismissModal() {
        _uiState.update {
            it.copy(
                activeMechanicModal = null,
                activeDrillModal = null,
                activeMasterModal = null,
                activeClashModal = null
            )
        }
    }

    fun updateSimulation(angle: Float, distance: Float, options: Int) {
        val sim = repository.calculateShadowSimulation(angle, distance, options)
        _uiState.update {
            it.copy(
                simAngleCoverageDegrees = angle,
                simDistanceMeters = distance,
                simOptionsCount = options,
                simulationResult = sim
            )
        }
    }
}
