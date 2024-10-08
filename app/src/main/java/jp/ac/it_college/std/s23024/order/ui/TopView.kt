package jp.ac.it_college.std.s23024.order.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.it_college.std.s23024.order.R
import jp.ac.it_college.std.s23024.order.model.Menu
import jp.ac.it_college.std.s23024.order.ui.theme.BaseColor
import jp.ac.it_college.std.s23024.order.ui.theme.Lime600
import jp.ac.it_college.std.s23024.order.ui.theme.Orange400
import jp.ac.it_college.std.s23024.order.ui.theme.Orange900

@Composable
fun TopView(modifier: Modifier = Modifier, onTapButton: () -> Unit = {}) {
    Column(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            // Image()
            Image(
                painter =  painterResource(id = R.drawable.top),
                contentDescription = stringResource(id = R.string.top_description)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Orange900)) {
                        append(stringResource(id = R.string.shop_name_first))
                    }
                    append(stringResource(id = R.string.shop_name_second))
                },
                modifier = Modifier.padding(top = 72.dp),
                color = Lime600,
                fontSize = 50.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Blue,
                        blurRadius = 10f,
                        offset = Offset(10f, 10f)
                    )
                )
            )
        }
        // Image()
        Image(
            painter = painterResource(id = R.drawable.middle),
            contentDescription = stringResource(R.string.middle_description)
        )
        val scrollState = rememberScrollState()
        val menuItems = listOf(
            Menu(R.string.classic_beef, R.drawable.classicbeef),
            Menu(R.string.spicy_chicken, R.drawable.spicychicken),
            Menu(R.string.vegetarian, R.drawable.vegetarian),
            Menu(R.string.barbecue, R.drawable.barbecue),
            Menu(R.string.hot_dogs, R.drawable.hotdog),
            Menu(R.string.coffee, R.drawable.coffee),
            Menu(R.string.nugget, R.drawable.nugget),
        )
        Row(
            modifier = Modifier.horizontalScroll(scrollState)
        ) {
            menuItems.forEach { item ->
    MenuView(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
        id = item.icon,
        title = stringResource(item.title),
        onClick = { onTapButton() }
    )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:medium_phone")
@Composable
private fun TopViewPreview() {
    TopView()
}

@Composable
fun MenuView(
    modifier: Modifier = Modifier,
    @DrawableRes id: Int,
    title: String = "",
    onClick: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick(id) }
            .padding(10.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(BaseColor, Orange400),
                    start = Offset(0f, 80.dp.px),
                    end = Offset(0f, 150.dp.px)
                ),
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Image(
                painter = painterResource(id = id),
                contentDescription = null
            )
        }
    }
}

@Stable
inline val Dp.px: Float
    @Composable get() {
        val density = LocalDensity.current
        return with(density) { this@px.toPx()}
    }

@Preview
@Composable
fun MenuViewPreview() {
    MenuView(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp),
        id = R.drawable.classicbeef,
        title = "classicbeef"
    )
}