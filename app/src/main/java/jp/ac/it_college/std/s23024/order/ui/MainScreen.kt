package jp.ac.it_college.std.s23024.order.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var isOrderView by remember { mutableStateOf(false) }
    if (isOrderView) {
        OverView(onTapButton = {isOrderView = false})
    } else {
        TopView(onTapButton = { isOrderView = true })
    }
}