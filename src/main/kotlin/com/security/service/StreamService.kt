package com.security.service

import com.security.config.StreamConfig
import org.springframework.stereotype.Service

@Service
class StreamService(private val streamConfig: StreamConfig) {

    fun getStreamIds(): List<Map<String, Int>> {
        return streamConfig.sources.mapNotNull { it.id }.map { mapOf("stream_id" to it) }
    }
}