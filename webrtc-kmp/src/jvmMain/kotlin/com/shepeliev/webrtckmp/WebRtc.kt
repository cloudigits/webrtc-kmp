@file:JvmName("WebRtcKmpJVM")

package com.shepeliev.webrtckmp

import dev.onvoid.webrtc.PeerConnectionFactory
import dev.onvoid.webrtc.logging.Logging
import dev.onvoid.webrtc.media.MediaDevices
import dev.onvoid.webrtc.media.audio.AudioDevice
import dev.onvoid.webrtc.media.audio.AudioDeviceModule
import dev.onvoid.webrtc.media.audio.AudioProcessing
import java.util.Optional

public typealias AudioDeviceModuleBuilder = () -> AudioDeviceModule?

public object WebRtc {

    private var _peerConnectionFactory: PeerConnectionFactory? = null
    internal val peerConnectionFactory: PeerConnectionFactory
        get() {
            if (_peerConnectionFactory == null) initialize()
            return checkNotNull(_peerConnectionFactory)
        }

    private var _audioDeviceModule: AudioDeviceModule? = null
    internal val audioDeviceModule: AudioDeviceModule
        get() {
            if (_audioDeviceModule == null) initialize()
            return checkNotNull(_audioDeviceModule)
        }

    private val builder by lazy {
        WebRtcBuilder()
    }

    public fun configureBuilder(block: WebRtcBuilder.() -> Unit = {}) {
        block(builder)
    }

    private fun initialize() {
        initLogging()
        initializePeerConnectionFactory()
    }

    private fun initializePeerConnectionFactory() {
        with(builder) {
            _audioDeviceModule = audioModuleBuilder()

            _peerConnectionFactory = PeerConnectionFactory(
                _audioDeviceModule,
                audioProcessing,
            )
        }
    }

    private fun initLogging() {
        with(builder) {
            loggingSeverity?.let {
                Logging.addLogSink(it) { _, message ->
                    println(message)
                }
            }
        }
    }

    public fun addDeviceChangeListener(listener: MediaDeviceListener) {
        MediaDevicesImpl.addDeviceChangeListener(listener)
    }

    public fun removeDeviceChangeListener(listener: MediaDeviceListener) {
        MediaDevicesImpl.removeDeviceChangeListener(listener)
    }

    public fun setAudioOutputDevice(device: AudioDevice) {
        audioDeviceModule.stopPlayout()
        audioDeviceModule.setPlayoutDevice(device)
        audioDeviceModule.initPlayout()
    }

    public fun setAudioOutputDevice(device: MediaDeviceInfo) {
        MediaDevices.getAudioRenderDevices().firstOrNull {
            it.descriptor == device.deviceId
        }?.let {
            setAudioOutputDevice(it)
        }
    }

    public fun setAudioInputDevice(device: AudioDevice) {
        audioDeviceModule.stopRecording()
        audioDeviceModule.setRecordingDevice(device)
        audioDeviceModule.initRecording()
    }

    public fun setAudioInputDevice(device: MediaDeviceInfo) {
        MediaDevices.getAudioCaptureDevices().firstOrNull {
            it.descriptor == device.deviceId
        }?.let {
            setAudioInputDevice(it)
        }
    }

    // Returns the native device: MediaDeviceKind has no AudioOutput variant upstream, so this
    // can't be represented as a MediaDeviceInfo.
    public fun getDefaultAudioOutput(): AudioDevice? = MediaDevices.getDefaultAudioRenderDevice()

    public fun disposePeerConnectionFactory() {
        peerConnectionFactory.dispose()
    }
}

internal val defaultAudioDeviceModuleBuilder: AudioDeviceModuleBuilder = {
    AudioDeviceModule().apply {
        MediaDevices.getDefaultAudioRenderDevice()?.let {
            setPlayoutDevice(it)
            initPlayout()
            startPlayout()
        }
    }
}

public class WebRtcBuilder(
    public var loggingSeverity: Logging.Severity? = null,
    public var audioModuleBuilder: AudioDeviceModuleBuilder = defaultAudioDeviceModuleBuilder,
    public var audioProcessing: AudioProcessing? = null,
)
