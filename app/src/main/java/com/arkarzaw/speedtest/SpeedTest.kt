package com.arkarzaw.speedtest

import java.util.*

object SpeedTest {

    val rand = Random()

    fun getSpeed() : SpeedVO {
        var speed = SpeedVO()
        speed.downloadSpeed = (rand.nextDouble() * 30)
        speed.uploadSpeed = rand.nextDouble() * 30
        speed.ping = rand.nextDouble() *200
        speed.time = System.currentTimeMillis().toDouble()
        return speed
    }
}