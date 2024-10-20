package ru.pseudonimb.uc.screens.main

data class MainState (
    val isLoading: Boolean = false,
    val NUMBER_CONST: Int = 10,
    val number: Int = 0,
    val numberModifier: Int = 1
)