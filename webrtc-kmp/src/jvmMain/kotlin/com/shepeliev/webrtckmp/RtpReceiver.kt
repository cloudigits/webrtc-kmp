package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.RTCRtpReceiver

public actual class RtpReceiver(public val native: RTCRtpReceiver, public actual val track: MediaStreamTrack?) {
    public actual val id: String
        get() = "TODO"

    public actual val parameters: RtpParameters
        get() = RtpParameters(native.parameters)
}
