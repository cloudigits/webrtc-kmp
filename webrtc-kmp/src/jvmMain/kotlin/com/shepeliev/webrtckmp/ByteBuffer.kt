package com.shepeliev.webrtckmp

import java.nio.ByteBuffer

public fun ByteBuffer.toByteArray(): ByteArray {
    val bytes = ByteArray(remaining())
    get(bytes)
    rewind()
    return bytes
}
