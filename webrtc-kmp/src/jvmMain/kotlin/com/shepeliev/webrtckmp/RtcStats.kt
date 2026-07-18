package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.RTCStats

public actual class RtcStats internal constructor(public val native: RTCStats) {
    public actual val timestampUs: Long = native.timestamp
    public actual val type: String = native.type.name
    public actual val id: String = native.id
    public actual val members: Map<String, Any> = native.attributes
    actual override fun toString(): String = native.toString()
}
