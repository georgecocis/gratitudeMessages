package ro.nexttech.journalapp.data.repository

import ro.nexttech.journalapp.model.GratitudeMessage
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

class GratitudeMessageRepository @Inject constructor() {
    private var mockedGratitudeMessages: MutableList<GratitudeMessage> = mutableListOf( GratitudeMessage(
        UUID.randomUUID(),
        "I love animals and animals love me. What a time to be alive!",
        LocalDateTime.now().minusYears(1),
        listOf(
            "mock_image_1",
            "mock_image_2"
            ),
        listOf("#ANIMALS", "#HORSE", "#PANDA", "#BLESSED")
    ),
        GratitudeMessage(
            UUID.randomUUID(),
            "Today I am grateful. Tomorrow? We shall see.",
            LocalDateTime.now().minusDays(5),
            null,
            null
        ),
        GratitudeMessage(
            UUID.randomUUID(),
            "I like boards. All kinds of boards.",
            LocalDateTime.now().minusMonths(1),
            listOf(
                "mock_image_3",
                "mock_image_4",
                "mock_image_5",
            ),
            listOf("#SNOWBOARD", "#SKATEBOARD", "#SURFBOARD", "#OUTDOOTS")
        ),
        GratitudeMessage(
            UUID.randomUUID(),
            "Always salt your pasta while boiling it!",
            LocalDateTime.now().minusDays(7),
            null,
            listOf("#PASTA", "#CHEF", "#COOKING", "#DINNER", "#MENU")
        ),
        GratitudeMessage(
            UUID.randomUUID(),
            "This is a sample message.",
            LocalDateTime.now(),
            null,
            listOf("#SAMPLE", "#TAG")
        )
    )

    fun getGratitudeMessages(): List<GratitudeMessage> {
        return mockedGratitudeMessages
    }

    fun saveGratitudeMessage(gratitudeMessage: GratitudeMessage) {
        mockedGratitudeMessages.add(gratitudeMessage)
    }
}