package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.media.audio.AudioTrack

internal class RemoteAudioStreamTrack(
    native: AudioTrack
) : MediaStreamTrackImpl(native), AudioStreamTrack {
    // webrtc-java exposes no native per-track volume API (AudioTrack only has addSink/removeSink/getSignalLevel).
    override fun setVolume(volume: Double) {}
}
