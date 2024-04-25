package com.alexandros.p.gialamas.littlelemon.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandros.p.gialamas.littlelemon.FilteredCategory
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor

@Composable
fun MiddlePanel(
    selectedCategories : Set<FilteredCategory>,
    setSelectedCategories : (Set<FilteredCategory>) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            FilterButton(
                buttonLabel = "Starters",
                category = FilteredCategory.STARTERS,
                selectedCategories = selectedCategories,
                setSelectedCategories = setSelectedCategories
            )
            FilterButton(
                buttonLabel = "Mains",
                category = FilteredCategory.MAINS,
                selectedCategories = selectedCategories,
                setSelectedCategories = setSelectedCategories
            )
            FilterButton(
                buttonLabel = "Desserts",
                category = FilteredCategory.DESSERTS,
                selectedCategories = selectedCategories,
                setSelectedCategories = setSelectedCategories
            )
            FilterButton(
                buttonLabel = "Drinks",
                category = FilteredCategory.DRINKS,
                selectedCategories = selectedCategories,
                setSelectedCategories = setSelectedCategories
            )
        }

        Divider(thickness = 1.dp, color = LittleLemonColor.charcoal, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun FilterButton(
    buttonLabel: String,
    category: FilteredCategory,
    selectedCategories : Set<FilteredCategory>,
    setSelectedCategories : (Set<FilteredCategory>) -> Unit
) {
    val buttonFocused = category in selectedCategories
    var buttonOn by remember { mutableStateOf(LittleLemonColor.yellow) }
    var buttonOff by remember { mutableStateOf(LittleLemonColor.cloud) }

    Button(
        onClick = {
                  val newSelection = if (category in selectedCategories) {
                      selectedCategories - category
                  } else {
                      selectedCategories + category
                  }
                  setSelectedCategories(newSelection)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (!buttonFocused) buttonOff else buttonOn),
        border = BorderStroke(1.dp, LittleLemonColor.charcoal),
        modifier = Modifier

    ) {
        Text(
            text = buttonLabel,
            color = Color.Black
        )
    }
}

