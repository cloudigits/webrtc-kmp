package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.RTCRtpSendParameters
import dev.onvoid.webrtc.RTCRtpSender

public actual class RtpSender internal constructor(
    public val native: RTCRtpSender,
    track: MediaStreamTrack?
) {
    public actual val id: String
        get() = native.track.id

    private var _track: MediaStreamTrack? = track
    public actual val track: MediaStreamTrack? get() = _track

    public actual var parameters: RtpParameters
        get() = RtpParameters(native.parameters)
        set(value) {
            native.parameters = RTCRtpSendParameters().apply {
                this.transactionId = value.transactionId
                this.codecs = value.codecs.map {
                    it.native
                }
            }
        }

    public actual val dtmf: DtmfSender?
        get() = null

    public actual suspend fun replaceTrack(track: MediaStreamTrack?) {
        native.replaceTrack((track as? MediaStreamTrackImpl)?.native)
        _track = track
    }
}
