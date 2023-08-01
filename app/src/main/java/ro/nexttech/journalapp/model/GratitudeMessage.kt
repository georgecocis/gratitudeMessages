package ro.nexttech.journalapp.model

import java.time.LocalDateTime
import java.util.UUID

data class GratitudeMessage(
    val id: UUID,
    val message: String,
    val timestamp: LocalDateTime,
    val pictureUrls: List<String>?,
    val tags: List<String>?
)
