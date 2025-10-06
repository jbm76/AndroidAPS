package app.aaps.pump.danars.encryption

import androidx.test.core.app.ApplicationProvider
import app.aaps.core.interfaces.logging.AAPSLogger
import app.aaps.di.TestApplication
import info.nightscout.androidaps.danars.encryption.BleEncryption
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import javax.inject.Inject

class BleEncryptionTest @Inject constructor() {

    @Inject lateinit var aapsLogger: AAPSLogger

    private val context = ApplicationProvider.getApplicationContext<TestApplication>()
    lateinit var sut: BleEncryption
    val deviceName = "AAA123456789"

    @Before
    fun inject() {
        context.androidInjector().inject(this)
    }

    @Before
    fun setupMocks() {
        sut = BleEncryption(context)
    }

    @Test
    fun ble5Flow() {
        val packet = sut.getEncryptedPacket(BleEncryption.DANAR_PACKET__OPCODE_ENCRYPTION__PUMP_CHECK, null, deviceName)
        Assertions.assertTrue(packet.contentEquals(byteArrayOf(-91, -91, 12, -62, -1, 44, -126, -66, 92, -15, -52, 89, -10, -55, 90, -104, 111, 90, 90)))
    }
}