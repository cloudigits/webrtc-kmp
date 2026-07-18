package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.RTCStatsReport

public actual class RtcStatsReport(public val native: RTCStatsReport) {
    public actual val timestampUs: Long = native.stats.values.firstOrNull()?.timestamp ?: -1
    public actual val stats: Map<String, RtcStats> = native.stats.mapValues { (_, v) -> RtcStats(v) }
    actual override fun toString(): String = native.toString()
}
