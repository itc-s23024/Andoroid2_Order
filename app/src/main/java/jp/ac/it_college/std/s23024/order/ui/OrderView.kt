package jp.ac.it_college.std.s23024.order.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.ac.it_college.std.s23024.order.R
import jp.ac.it_college.std.s23024.order.ui.theme.BaseColor

@Composable
fun RadioButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onSelect: () -> Unit = {}
) {
   Row(modifier = modifier
       .padding(8.dp)
       .fillMaxWidth()
       .selectable(selected = selected, onClick = onSelect)
   ) {
      RadioButton(
          selected = selected,
          onClick = null
      )
      Text(
          text = text,
          style = MaterialTheme.typography.bodyLarge
      )
   }
}

@Preview
@Composable
private fun RadioButtonWithTextPreview() {
    RadioButtonWithText(
        text = "ハンバーガー",
        selected = true,
    )
}

@Composable
fun MainDishSection(modifier: Modifier = Modifier) {
    var selectedDish by remember { mutableIntStateOf(R.string.hamburger) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .selectableGroup()
        ) {
            Text(
                text = stringResource(id = R.string.choose_main),
                style = MaterialTheme.typography.titleLarge
            )
            RadioButtonWithText(
                text = stringResource(R.string.hamburger),
                selected = selectedDish == R.string.hamburger,
                onSelect = { selectedDish = R.string.hamburger }
            )
            RadioButtonWithText(
                text = stringResource(R.string.cheeseburger),
                selected = selectedDish == R.string.cheeseburger,
                onSelect = { selectedDish = R.string.cheeseburger  }
            )
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Preview( locale = "ja", showBackground = true, backgroundColor = 0xFF000000 )
@Composable
private fun MainDishSectionPreview() {
    MainDishSection()
}

@Composable
fun SideMenuSection(modifier: Modifier = Modifier) {
    var frenchFries by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.choose_side),
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.toggleable(
                    value = frenchFries,
                    onValueChange = { frenchFries = it}
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = frenchFries,
                    onCheckedChange = null
                )
                Text(
                    text = stringResource(id = R.string.french_fries),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00000)
@Preview(locale = "ja",  showBackground = true, backgroundColor = 0xFF00000)
@Composable
fun SideMenuSectionPreview() {
    SideMenuSection()
}

@Composable
fun SauceAmountSection(modifier: Modifier = Modifier) {
    var sauceAmount by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BaseColor,
                shape = MaterialTheme.shapes.extraLarge
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.amount_sauce),
                style = MaterialTheme.typography.titleLarge
            )
            Slider(
                value = sauceAmount,
                onValueChange = { sauceAmount = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Text(
                text = when (sauceAmount) {
                    in 0.0f..0.3f -> stringResource(R.string.less)
                    in 0.3f..0.7f -> stringResource(R.string.normal)
                    else -> stringResource(R.string.more)
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00000)
@Preview(locale = "ja", showBackground = true, backgroundColor = 0xFF00000)
@Composable
private fun SauceAmountSectionPreview() {
    SauceAmountSection()
}

@Composable
fun DrinkSelectionSection(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDrink by remember { mutableIntStateOf(R.string.blank) }
    val drinks = listOf(
        R.string.iced_coffee,
        R.string.iced_cafe_au_lait,
        R.string.coke
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BaseColor,
                shape = MaterialTheme.shapes.extraLarge
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.choose_your_drink),
                style = MaterialTheme.typography.titleLarge
                )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = stringResource(selectedDrink),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {  expanded = true }
                        )
                    },
                    label = {
                        Text(text = stringResource(R.string.drink))
                    },
                    readOnly = true
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    drinks.forEach { drink ->
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(drink))
                            },
                            onClick = {
                                selectedDrink = drink
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00000)
@Preview(locale = "ja", showBackground = true, backgroundColor = 0xFF00000)
@Composable
private fun DrinkSelectionSectionPreview() {
    DrinkSelectionSection()
}

