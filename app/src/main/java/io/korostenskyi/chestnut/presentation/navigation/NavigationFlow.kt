package io.korostenskyi.chestnut.presentation.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect

class NavigationFlow {

    private val _navigationFlow = MutableSharedFlow<(Router) -> Unit>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val navigationFlow = _navigationFlow.asSharedFlow()

    suspend fun navigate(event: Router.() -> Unit) {
        _navigationFlow.emit(event)
    }

    suspend fun collect(router: Router) {
        navigationFlow.collect { it(router) }
    }
}
