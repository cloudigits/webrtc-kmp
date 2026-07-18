package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.media.audio.AudioTrack
import dev.onvoid.webrtc.media.audio.AudioTrackSource

internal class LocalAudioStreamTrack(
    native: AudioTrack,
    private val audioSource: AudioTrackSource,
    override val constraints: MediaTrackConstraints,
) : MediaStreamTrackImpl(native), AudioStreamTrack {
    // webrtc-java exposes no native per-track volume API (AudioTrack only has addSink/removeSink/getSignalLevel).
    override fun setVolume(volume: Double) {}
}
