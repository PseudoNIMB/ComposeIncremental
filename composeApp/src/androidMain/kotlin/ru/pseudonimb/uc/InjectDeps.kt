package ru.pseudonimb.uc

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.pseudonimb.uc.screens.main.MainViewModel

val injectDeps = DI {
    bind<MainViewModel>() with singleton {
        MainViewModel()
    }
}