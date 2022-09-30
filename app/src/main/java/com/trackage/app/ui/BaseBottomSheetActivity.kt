package com.trackage.app.ui

import androidx.activity.ComponentActivity
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
abstract class BaseBottomSheetActivity : ComponentActivity() {

    abstract var scaffoldState: BottomSheetScaffoldState
    abstract var coroutineScope: CoroutineScope

    override fun onBackPressed() {
        coroutineScope.launch {
            if (scaffoldState.bottomSheetState.isExpanded) {
                scaffoldState.bottomSheetState.collapse()
            } else {
                super.onBackPressed()
            }
        }
    }
}