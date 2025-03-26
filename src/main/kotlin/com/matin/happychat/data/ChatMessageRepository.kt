package com.matin.happychat.data

import com.matin.happychat.data.model.ChatMessageEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : CoroutineCrudRepository<ChatMessageEntity, Long> {
    suspend fun findByReceiverId(receiverId: Long): Flow<ChatMessageEntity>
}
