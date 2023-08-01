package ro.nexttech.journalapp.ui.view.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DisplayTags(tags: List<String>?,
                horizontalPadding: Dp?) {
    if (tags != null) {
        horizontalPadding?.let { Modifier.padding(horizontal = it) }?.let {
            LazyRow(
                modifier = it
            ) {
                items(tags) { tag ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(Color.White),
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Text(
                            text = tag,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(3.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}