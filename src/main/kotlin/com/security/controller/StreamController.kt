package com.security.controller

import com.security.service.StreamService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["stream"])
class StreamController(private val streamService: StreamService) {

    @GetMapping
    fun getStreamIds(): ResponseEntity<*> {
        return ResponseEntity.ok(streamService.getStreamIds())
    }

    @GetMapping(path = ["/{streamId}"])
    fun startStream(@PathVariable streamId: Int): ResponseEntity<*> {
        return ResponseEntity.ok("$streamId")
    }
}