package io.korostenskyi.chestnut.presentation.screen

sealed class HomeSideEffect {

    data class Toast(val message: String) : HomeSideEffect()
}
