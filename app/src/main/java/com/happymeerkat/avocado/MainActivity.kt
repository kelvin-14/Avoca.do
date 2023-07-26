package com.happymeerkat.avocado

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.happymeerkat.avocado.presentation.navigation.RootGraph
import com.happymeerkat.avocado.presentation.vm.MainActVM
import com.happymeerkat.avocado.ui.theme.AvocadoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AvocadoTheme {
                val viewModel: MainActVM = hiltViewModel()
                val dialogQueue = viewModel.visiblePermissionDialogueQueue
                val notificationsPermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = {isGranted ->
                        viewModel.onPermissionResult(
                            permission = android.Manifest.permission.POST_NOTIFICATIONS,
                            isGranted = isGranted
                        )
                    }
                )

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootGraph(
                        askNotificationsPermission = {notificationsPermissionResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)},
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

