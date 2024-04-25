package com.alexandros.p.gialamas.littlelemon.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.alexandros.p.gialamas.littlelemon.FilteredCategory
import com.alexandros.p.gialamas.littlelemon.data.MenuItemEntity
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor
import com.alexandros.p.gialamas.littlelemon.R
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LowerPanel(
    navController: NavController,
    menuItems: List<MenuItemEntity> = listOf(),
    selectedCategories: Set<FilteredCategory>,
    searchPhraseFlow: StateFlow<TextFieldValue>,
) {
    val searchPhrase by searchPhraseFlow.collectAsState()

    val filteredItems = remember(menuItems, searchPhrase, selectedCategories) {
        if (searchPhrase.text.isNotBlank()) {
            menuItems.sortedBy { it.title }.filter { item ->
                item.title.contains(searchPhrase.text, ignoreCase = true) &&
                        (selectedCategories.isEmpty() || selectedCategories.contains(
                            FilteredCategory.valueOf(
                                item.category.uppercase()
                            )
                        ))
            }
        } else {
            menuItems.sortedBy { it.title }.filter { item ->
                (selectedCategories.isEmpty() || selectedCategories.contains(
                    FilteredCategory.valueOf(
                        item.category.uppercase()
                    )
                ))
            }
        }
    }


    Column {
        LazyColumn(

        ) {
            items(filteredItems) { item ->
                MenuItem(item = item)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItem(item: MenuItemEntity) {

    Card(
        onClick = {},
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Text(
                    text = "${stringResource(id = R.string.price)} ${item.price}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = "Dish Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(0.9f)
                    .clip(RoundedCornerShape(10.dp)),
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.charcoal
    )

}