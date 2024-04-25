package com.alexandros.p.gialamas.littlelemon.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor

@Composable
fun UpperPanel(
    searchPhrase : TextFieldValue,
) {
    var setSearchPhrase by remember { mutableStateOf(searchPhrase) }

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
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            color = LittleLemonColor.cloud
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 16.dp)
                .height(intrinsicSize = IntrinsicSize.Min)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.bodySmall,
                color = LittleLemonColor.cloud,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Upper Panel Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()

            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
                value = setSearchPhrase,
                onValueChange = { setSearchPhrase = it},
                label = { Text("Search") },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

        }
    }


