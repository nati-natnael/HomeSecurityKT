package com.security

import com.security.config.StreamConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

@SpringBootApplication
class HomeSecurityApplication

fun main(args: Array<String>) {
    try {
        SpringApplication.run(HomeSecurityApplication::class.java)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}