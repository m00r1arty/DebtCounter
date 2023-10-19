package com.radzhabov.debtcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.radzhabov.debtcounter.domain.util.Routes
import com.radzhabov.debtcounter.ui.add_edit_debt.AddEditDebtScreen
import com.radzhabov.debtcounter.ui.add_edit_debt.AddEditDebtViewModel
import com.radzhabov.debtcounter.ui.debt_list.DebtListScreen
import com.radzhabov.debtcounter.ui.debt_list.DebtListViewModel
import com.radzhabov.debtcounter.ui.theme.DebtCounterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val addEditDebtViewModel: AddEditDebtViewModel by viewModels()
    private val debtListViewModel: DebtListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebtCounterTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.DEBT_LIST,
                ){
                    composable(Routes.DEBT_LIST) {
                        DebtListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            },
                            viewModel = debtListViewModel
                        )
                    }
                    composable(
                        route = Routes.ADD_EDIT_DEBT + "?debtId={debtId}",
                        arguments = listOf(
                            navArgument(name = "debtId") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditDebtScreen(
                            onPopBackStack = {
                                navController.popBackStack()
                            },
                            viewModel = addEditDebtViewModel
                        )
                    }
                }
            }
        }
    }
}
