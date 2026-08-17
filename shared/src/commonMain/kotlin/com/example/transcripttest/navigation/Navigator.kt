package com.example.transcripttest.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.transcripttest.Route

class Navigator(startDestination: Route) {
    val backStack: SnapshotStateList<Route> = mutableStateListOf(startDestination)

    fun navigate(route: Route) {
        backStack.add(route)
    }

    fun pop() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.size - 1)
        }
    }

    fun navigateSingleTop(route: Route) {
        if (backStack.lastOrNull() != route) {
            backStack.add(route)
        }
    }

    fun replaceAll(route: Route) {
        backStack.clear()
        backStack.add(route)
    }
}
