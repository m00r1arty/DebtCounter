package com.radzhabov.debtcounter.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radzhabov.debtcounter.domain.util.Routes
import com.radzhabov.debtcounter.ui.add_edit_debt.AddEditDebtScreen
import com.radzhabov.debtcounter.ui.add_edit_debt.AddEditDebtViewModel
import com.radzhabov.debtcounter.ui.debt_list.DebtListScreen
import com.radzhabov.debtcounter.ui.debt_list.DebtListViewModel

@Composable
fun AppNavigation(
    navController: NavController,
    addEditDebtViewModel: AddEditDebtViewModel,
    debtListViewModel: DebtListViewModel,

) {
    NavHost(
        navController = navController as NavHostController,
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