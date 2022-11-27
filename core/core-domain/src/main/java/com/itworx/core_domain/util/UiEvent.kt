package com.itworx.core.util

import com.itworx.core_domain.util.UiText

sealed class UiEvent {
    object Success : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}