package ru.pseudonimb.uc.screens.main

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    private val scope: CoroutineScope = CoroutineScope(
        Dispatchers.IO + SupervisorJob()
    )

    init {
        scope.launch {
            _mainState.update {
                it.copy(

                )
            }
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Continue -> {
                scope.launch {
                    _mainState.update {
                        it.copy(

                        )
                    }
                }
            }
        }
    }
}