package com.happymeerkat.avocado

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.happymeerkat.avocado.domain.model.Category
import com.happymeerkat.avocado.presentation.screens.dialog.DateDialog
import com.happymeerkat.avocado.presentation.screens.dialog.NewItemEditor
import com.happymeerkat.avocado.presentation.screens.dialog.TimeDialog
import com.happymeerkat.avocado.presentation.vm.EditItemVM
import com.happymeerkat.avocado.presentation.vm.MainVM
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WidgetViewsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_views)
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);



        setContent {
            val editVM: EditItemVM = hiltViewModel()
            val eState = editVM.itemUIState.collectAsState().value

            val mainVM: MainVM = hiltViewModel()
            val mState = mainVM.mainUIState.collectAsState().value

            val context = LocalContext.current
            val dateDialogState = rememberMaterialDialogState()
            val timeDialogState = rememberMaterialDialogState()


            NewItemEditor(
                modifier = Modifier.fillMaxSize(),
                closeModal = { finish() },
                currentCategory = Category(1, "All"),
                showDateDialog = { dateDialogState.show() },
                alpha = 0.2f
            )

            DateDialog(dateDialogState = dateDialogState, openTimeDialog = {timeDialogState.show()})
            TimeDialog(timeDialogState = timeDialogState)
        }
    }
}