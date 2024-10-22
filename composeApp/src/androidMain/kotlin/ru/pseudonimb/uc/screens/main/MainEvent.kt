package ru.pseudonimb.uc.screens.main

sealed interface MainEvent {
    data object Continue : MainEvent
}