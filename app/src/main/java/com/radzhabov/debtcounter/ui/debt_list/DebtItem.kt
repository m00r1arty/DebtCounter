package com.radzhabov.debtcounter.ui.debt_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.debtcounter.data.Debt

@Composable
fun DebtItem(
    debt: Debt,
    onEvent: (DebtListEvent) -> Unit,
    modifier: Modifier,
) {
    Box {
        Card(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = debt.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        IconButton(onClick = {
                            onEvent(DebtListEvent.OnDeleteDebtClick(debt))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                            )
                        }
                    }
                    debt.description?.let {
                        Text(text = it)
                    }

                }
                Row (
                    modifier = Modifier.weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = debt.price)
                    Checkbox(
                        checked = debt.isDone,
                        onCheckedChange = { isChecked ->
                            onEvent(DebtListEvent.OnDoneChange(debt, isChecked))
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DebtItemPreview() {
    val debt = Debt(
        id = 1,
        name = "Пример",
        price = "100 руб.",
        description = "Описание задолженности",
        isDone = false
    )

    DebtItem(
        debt = debt,
        onEvent = {},
        modifier = Modifier.fillMaxWidth()
    )
}