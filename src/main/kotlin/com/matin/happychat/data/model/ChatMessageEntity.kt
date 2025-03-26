package com.matin.happychat.data.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("chat_message")
data class ChatMessageEntity(
    @Id
    val id: Long? = null,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
