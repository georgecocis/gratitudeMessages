package ro.nexttech.journalapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ro.nexttech.journalapp.data.repository.GratitudeMessageRepository
import ro.nexttech.journalapp.model.GratitudeMessage
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class GratitudeMessageViewModel @Inject constructor (private val gratitudeMessageRepository: GratitudeMessageRepository) : ViewModel() {
    private var _gratitudeMessages = MutableStateFlow<List<GratitudeMessage>>(emptyList())
    val gratitudeMessages: StateFlow<List<GratitudeMessage>> get() = _gratitudeMessages.asStateFlow()

    init {
        getGratitudeMessages()
    }

    private fun getGratitudeMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedGratitudeMessages = gratitudeMessageRepository.getGratitudeMessages()
            withContext(Dispatchers.Main) {
                _gratitudeMessages.value = fetchedGratitudeMessages
            }
        }
    }

    fun getGratitudeMessageById(gratitudeMessageId: UUID): GratitudeMessage? {
        return gratitudeMessages.value.find { it.id == gratitudeMessageId }
    }

    private fun addGratitudeMessage(gratitudeMessage: GratitudeMessage) {
        viewModelScope.launch(Dispatchers.IO) {
            gratitudeMessageRepository.saveGratitudeMessage(gratitudeMessage)
        }
    }
}