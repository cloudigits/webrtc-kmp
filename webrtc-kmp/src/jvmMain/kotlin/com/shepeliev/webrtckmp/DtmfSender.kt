package com.shepeliev.webrtckmp

public actual class DtmfSender {

    public actual val canInsertDtmf: Boolean
        get() = false

    public actual val duration: Int
        get() = 0

    public actual val interToneGap: Int
        get() = 0

    public actual fun insertDtmf(tones: String, durationMs: Int, interToneGapMs: Int): Boolean {
        TODO("Not yet implemented for JVM platform")
    }

    public actual fun tones(): String = ""
}
