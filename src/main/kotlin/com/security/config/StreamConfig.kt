package com.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(value = "stream")
class StreamConfig {
    var model: Model? = null
    var label: Label? = null
    var sources: List<StreamSource> = emptyList()
}

class Model {
    var baseUrl: String? = null
    var name: String? = null
    var date: String? = null
}

class Label {
    var baseUrl: String? = null
    var name: String? = null
}

class StreamSource {
    var id: Int? =  0
    var port: Int? =  5555
    var queueSize: Int? =  5
}