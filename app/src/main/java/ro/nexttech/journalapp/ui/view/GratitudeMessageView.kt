import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ro.nexttech.journalapp.model.GratitudeMessage
import ro.nexttech.journalapp.ui.navigation.Screen
import ro.nexttech.journalapp.ui.view.util.DisplayTags
import ro.nexttech.journalapp.viewmodel.GratitudeMessageViewModel
import java.time.format.DateTimeFormatter

@Composable
fun DisplayGratitudeMessages(
    gratitudeMessageViewModel: GratitudeMessageViewModel,
    navController: NavController
) {
    val gratitudeMessages by gratitudeMessageViewModel.gratitudeMessages.collectAsState()

    Surface(
        color = Color.Transparent,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                text = "Daily gratitude",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn {
                items(gratitudeMessages.sortedByDescending { it.timestamp }) { gratitudeMessage ->
                    DisplayGratitudeMessageInList(gratitudeMessage, navController)
                }
            }
        }
    }
}

@Composable
fun DisplayGratitudeMessageInList(
    gratitudeMessage: GratitudeMessage,
    navController: NavController
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    Screen.ExtendedGratitudeMessageScreen.createRoute(
                        gratitudeMessageId = gratitudeMessage.id
                    )
                )
            }
            .fillMaxSize(),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = gratitudeMessage.timestamp.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                ),
                modifier = Modifier.align(alignment = Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = gratitudeMessage.message,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 15.sp
                )
            )
            DisplayTags(tags = gratitudeMessage.tags, 0.dp)
            if (gratitudeMessage.pictureUrls != null) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val randomPicture = gratitudeMessage.pictureUrls?.get(0)
                    if (randomPicture != null) {
                        val painter = painterResource(
                            id = extractImageIdFromString(
                                randomPicture,
                                LocalContext.current
                            )
                        )
                        Image(
                            painter = painter,
                            contentDescription = "Gratitude message picture 1",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(15.dp))
                        )
                    }
                }
            }
        }
    }
}

fun extractImageIdFromString(name: String, context: Context): Int {
    return context.resources.getIdentifier(name, "drawable", context.packageName)
}