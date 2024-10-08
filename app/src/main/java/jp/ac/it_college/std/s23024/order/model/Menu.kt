package jp.ac.it_college.std.s23024.order.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Menu(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
)
