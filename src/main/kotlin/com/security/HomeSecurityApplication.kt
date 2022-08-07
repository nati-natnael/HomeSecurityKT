package com.security

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HomeSecurityApplication

fun main(args: Array<String>) {
    try {
        // Start Listener threads
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..1000) {
                println("coroutine is running $i")
                delay(1000)
            }
        }

        SpringApplication.run(HomeSecurityApplication::class.java)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}