package com.matin.happychat

import ChatGrpcService
import com.matin.happychat.service.ChatService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig {

    @Bean
    fun grpcService(chatService: ChatService): ChatGrpcService {
        return ChatGrpcService(chatService)
    }
}