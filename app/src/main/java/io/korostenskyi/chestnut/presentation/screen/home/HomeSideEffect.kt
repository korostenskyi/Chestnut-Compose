package io.korostenskyi.chestnut.presentation.screen.home

sealed class HomeSideEffect {

    data class Toast(val message: String) : HomeSideEffect()
}
