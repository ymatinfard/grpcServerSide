package com.matin.happychat.data

import com.matin.happychat.ChatMessage
import com.matin.happychat.data.model.ChatMessageEntity

fun ChatMessage.toEntity(): ChatMessageEntity = ChatMessageEntity(
    senderId = senderId,
    receiverId = receiverId,
    content = message
)

fun ChatMessageEntity.toProto(): ChatMessage {
    return ChatMessage.newBuilder()
        .setSenderId(this.senderId)
        .setReceiverId(this.receiverId)
        .setMessage(this.content)
        .setTimeStamp(this.timestamp.toString())
        .build()
}