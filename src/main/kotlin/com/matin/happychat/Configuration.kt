package com.matin.happychat

import ChatGrpcService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig {
    @Bean
    fun grpcService(): ChatGrpcService {
        return ChatGrpcService()
    }
}