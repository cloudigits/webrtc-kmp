package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.media.video.VideoTrackSink

public actual interface VideoTrack : MediaStreamTrack {
    public actual suspend fun switchCamera(deviceId: String?)
    public fun addSink(sink: VideoTrackSink)
    public fun removeSink(sink: VideoTrackSink)
}
