package com.security.service

import com.google.common.collect.EvictingQueue
import com.security.config.StreamConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.awt.BorderLayout
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.SocketTimeoutException
import java.util.stream.Stream
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

object StreamBuffer {
    val buffers: MutableMap<Int, EvictingQueue<ByteArrayInputStream>> = mutableMapOf()
}

@Component
class SourceListenerService(private val streamConfig: StreamConfig) {

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationStart() {

        streamConfig.sources.forEach {
            // Create stream buffer
            StreamBuffer.buffers[it.id] = EvictingQueue.create(it.queueSize)

            // Start Listener coroutine
            val scope = CoroutineScope(Dispatchers.IO)

            scope.launch {
                val buffer = StreamBuffer.buffers[it.id]!!

                val server = ServerSocket(it.port)

                println("Started source listener ID: ${it.id} Port: ${it.port}")

                val socket = server.accept()
                socket.soTimeout = 10 // milliseconds

                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                while (!socket.isClosed) {
                    val tempBuffer = ByteArrayOutputStream()

                    while (true) {
                        try {
                            val buf = reader.readLine()
                            tempBuffer.writeBytes(buf.toByteArray())
                        } catch (ex: SocketTimeoutException) {
                            break
                        }
                    }

//                    if (tempBuffer.size() > 0) {
//                        buffer.offer(ByteArrayInputStream(tempBuffer.toByteArray()))
//
//                        System.setProperty("java.awt.headless", "false")
//                        val frame = JFrame("server")
//                        frame.setSize(400, 400)
//
//                        frame.isVisible = true
//
//                        val label = JLabel(ImageIcon(tempBuffer.toByteArray()))
//                        label.text = "Image received"
//
//                        frame.add(label, BorderLayout.CENTER)
//                    }
//
//                    println("*** image *** ${buffer.poll()}")
                }
            }
        }
    }
}