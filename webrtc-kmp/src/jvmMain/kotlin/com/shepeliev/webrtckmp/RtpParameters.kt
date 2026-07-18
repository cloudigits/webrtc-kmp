package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.RTCRtcpParameters
import dev.onvoid.webrtc.RTCRtpCodecParameters
import dev.onvoid.webrtc.RTCRtpEncodingParameters
import dev.onvoid.webrtc.RTCRtpHeaderExtensionParameters
import dev.onvoid.webrtc.RTCRtpParameters

public actual class RtpParameters(public val native: RTCRtpParameters) {
    public actual val codecs: List<RtpCodecParameters>
        get() = native.codecs.map { RtpCodecParameters(it) }

    public actual val encodings: List<RtpEncodingParameters>
        get() = emptyList()

    public actual val headerExtension: List<HeaderExtension>
        get() = native.headerExtensions.map { HeaderExtension(it) }

    public actual val rtcp: RtcpParameters
        get() = RtcpParameters(native.rtcp)

    public actual val transactionId: String
        get() = "TODO"
}

public actual class RtpCodecParameters(public val native: RTCRtpCodecParameters) {
    public actual val payloadType: Int
        get() = native.payloadType

    public actual val mimeType: String?
        get() = native.mediaType.name

    public actual val clockRate: Int?
        get() = native.clockRate

    public actual val numChannels: Int?
        get() = native.channels

    public actual val parameters: Map<String, String>
        get() = native.parameters
}

public actual class RtpEncodingParameters(public val native: RTCRtpEncodingParameters) {
    public actual val rid: String?
        get() = null

    public actual val active: Boolean
        get() = native.active

    public actual val bitratePriority: Double
        get() = 0.0

    public actual val networkPriority: Int
        get() = 0

    public actual val maxBitrateBps: Int?
        get() = native.maxBitrate

    public actual val minBitrateBps: Int?
        get() = native.minBitrate

    public actual val maxFramerate: Int?
        get() = native.maxFramerate.toInt()

    public actual val numTemporalLayers: Int?
        get() = null

    public actual val scaleResolutionDownBy: Double?
        get() = native.scaleResolutionDownBy

    public actual val ssrc: Long?
        get() = native.ssrc
}

public actual class HeaderExtension(public val native: RTCRtpHeaderExtensionParameters) {
    public actual val uri: String
        get() = native.uri

    public actual val id: Int
        get() = native.id

    public actual val encrypted: Boolean
        get() = native.encrypted
}

public actual class RtcpParameters(public val native: RTCRtcpParameters) {
    public actual val cname: String
        get() = native.cName

    public actual val reducedSize: Boolean
        get() = native.reducedSize
}
