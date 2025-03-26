package com.matin.happychat.service

import com.matin.happychat.ChatMessage
import com.matin.happychat.data.ChatMessageRepository
import com.matin.happychat.data.model.ChatMessageEntity
import com.matin.happychat.data.toEntity
import com.matin.happychat.data.toProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class ChatService(private val repository: ChatMessageRepository) {

    suspend fun saveMessage(chatMessage: ChatMessage): ChatMessage {
        return repository.save(chatMessage.toEntity()).toProto()
    }

    suspend fun getMessagesForUser(receiverId: Long): Flow<ChatMessage> {
        return repository.findByReceiverId(receiverId).map(ChatMessageEntity::toProto)
    }
}
