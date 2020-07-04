package com.practice.calculator.SecurityCentrak

import com.practice.calculator.Door
import com.practice.calculator.SecurityCentral
import com.practice.calculator.Window
import io.mockk.*
import org.junit.Test

class test_securityCentral {

    private var window = mockk<Window>()
    private var door = mockk<Door>()
    val securityCentral = SecurityCentral(window, door)

    @Test
    fun test() {
        every { window.close() } returns Unit
        every { door.close() } returns Unit

        securityCentral.securityOn()
        verifyAll {
            window.close()
            door.close()
        }
    }
}