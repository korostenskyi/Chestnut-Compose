package io.korostenskyi.chestnut.presentation.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.Router
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val navigationFlow: NavigationFlow
) : ViewModel() {

    fun back() {
        viewModelScope.launch {
            navigationFlow.navigate(Router::back)
        }
    }
}
