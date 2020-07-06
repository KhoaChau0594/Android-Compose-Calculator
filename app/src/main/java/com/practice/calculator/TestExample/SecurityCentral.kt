package com.practice.calculator.TestExample

class Window{
    fun close() = println("Window close")
}

class Door{
    fun close() = println("Window close")
}

class SecurityCentral(var window: Window, var door: Door) {
    fun securityOn(){
        window.close()
        door.close()
    }
}


