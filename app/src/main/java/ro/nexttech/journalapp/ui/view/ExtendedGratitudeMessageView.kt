package ro.nexttech.journalapp.ui.view

import ro.nexttech.journalapp.ui.view.util.RoundedCornerImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import extractImageIdFromString
import ro.nexttech.journalapp.model.GratitudeMessage
import ro.nexttech.journalapp.ui.view.util.DisplayTags
import java.time.format.DateTimeFormatter

@Composable
fun DisplayExtendedGratitudeMessage(
    extendedGratitudeMessage: GratitudeMessage?,
    onBackClick: () -> Unit
) {
    if (extendedGratitudeMessage != null) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent,
                    Color.Gray
                )
            ) {
                Text(
                    text = "Back",
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = extendedGratitudeMessage.timestamp.format(
                    DateTimeFormatter.ofPattern("dd MMMM yyyy")
                ),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = extendedGratitudeMessage.message,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            DisplayTags(tags = extendedGratitudeMessage.tags, 12.dp)

            val selectedPicture = remember {
                mutableStateOf(extendedGratitudeMessage.pictureUrls?.firstOrNull())
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (selectedPicture.value != null) {
                        RoundedCornerImage(
                            painter = painterResource(
                                id = extractImageIdFromString(
                                    selectedPicture.value!!,
                                    LocalContext.current
                                )
                            ),
                            contentDescription = "Main picture",
                            cornerRadius = 15.dp,
                            imageSize = 300.dp,
                            null
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    ) {
                        extendedGratitudeMessage.pictureUrls?.let {
                            items(it.size) { index ->
                                if (it[index] != selectedPicture.value) {
                                    RoundedCornerImage(
                                        painter = painterResource(
                                            id = extractImageIdFromString(
                                                it[index],
                                                LocalContext.current
                                            )
                                        ),
                                        contentDescription = "Small picture",
                                        cornerRadius = 4.dp,
                                        imageSize = 75.dp,
                                        onClick = {
                                            selectedPicture.value = it[index]
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        Text(text = "Gratitude message not found")
    }
}