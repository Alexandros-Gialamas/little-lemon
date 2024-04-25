package com.alexandros.p.gialamas.littlelemon.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun UpperPanel(
    searchPhraseFlow: MutableStateFlow<TextFieldValue>,
) {
    var searchPhrase by remember { mutableStateOf(TextFieldValue("")) }
    var clearButton by remember { mutableStateOf(false) }

    if (searchPhrase.text.isNotBlank()) clearButton = true else clearButton = false

    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
//                .padding(top = 4.dp)
                .height(intrinsicSize = IntrinsicSize.Min)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 24.sp,
                    color = LittleLemonColor.cloud,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.bodySmall,
                color = LittleLemonColor.cloud,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f)
            )
        }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Upper Panel Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()

            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
                searchPhraseFlow.value = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (clearButton) {
                    IconButton(
                        onClick = {
                            searchPhrase = TextFieldValue("")
                            searchPhraseFlow.value = TextFieldValue("")
                        },
                    ) {
                        Icon(imageVector = Icons.TwoTone.Clear, contentDescription = "Clear Icon")
                    }
            }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            label = {
                Text(
                    stringResource(id = R.string.search_phrase),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = LittleLemonColor.cloud,
                focusedContainerColor = LittleLemonColor.cloud,
                focusedLabelColor = LittleLemonColor.yellow,
                focusedIndicatorColor = LittleLemonColor.yellow,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}




