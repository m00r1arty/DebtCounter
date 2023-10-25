package com.radzhabov.debtcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.radzhabov.debtcounter.ui.AppNavigation
import com.radzhabov.debtcounter.ui.add_edit_debt.AddEditDebtViewModel
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

                AppNavigation(
                    navController = navController,
                    addEditDebtViewModel = addEditDebtViewModel,
                    debtListViewModel = debtListViewModel,
                )
            }
        }
    }
}
