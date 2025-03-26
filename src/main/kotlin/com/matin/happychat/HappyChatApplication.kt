package com.matin.happychat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories // This enables R2DBC repository scanning
class HappyChatApplication

fun main(args: Array<String>) {
    runApplication<HappyChatApplication>(*args)
}
