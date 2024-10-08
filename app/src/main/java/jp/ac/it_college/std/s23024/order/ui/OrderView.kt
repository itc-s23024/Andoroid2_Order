package jp.ac.it_college.std.s23024.order.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.it_college.std.s23024.order.R
import jp.ac.it_college.std.s23024.order.ui.theme.BaseColor
import jp.ac.it_college.std.s23024.order.ui.theme.Orange400
import jp.ac.it_college.std.s23024.order.ui.theme.Pink80

@Composable
fun OverView(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int = R.drawable.classicbeef,
    onTapButton: () -> Unit = {}
) {
   val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.order_screen),
            contentDescription = stringResource(id = R.string.order_description),
            modifier = Modifier.fillMaxWidth(),
            contentScale =  ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.order_background),
                contentDescription = null
            )
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        MainDishSection()
        SideMenuSection()
        SauceAmountSection()
        DrinkSelectionSection()
        OrderButtonSection(
            text = stringResource(id = R.string.order_now),
            onClick = onTapButton
        )
    }
}

@Preview(
    device = "spec:width=400dp,height=1200dp", showBackground = true,
    backgroundColor = 0xff00000,
)
@Preview(
    device = "spec:width=400dp,height=1200dp", showBackground = true,
    backgroundColor = 0xff00000, locale = "ja",
)
@Composable
fun OrderViewPreview() {
    OverView()
}

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

@Composable
fun OrderButtonSection(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Pink80, Orange400)
    )
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = gradient,
                shape = MaterialTheme.shapes.extraLarge
            )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderButtonSectionPreview(modifier: Modifier = Modifier) {
    OrderButtonSection(text = "注文する")
}