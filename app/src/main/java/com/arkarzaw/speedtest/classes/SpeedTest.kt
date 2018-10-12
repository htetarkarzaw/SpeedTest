package com.arkarzaw.speedtest.classes

import com.arkarzaw.speedtest.datas.SpeedVO
import java.util.*

class SpeedTest {

    val rand = Random()

    fun getSpeed() : SpeedVO {
        var speed = SpeedVO()
        speed.downloadSpeed = (rand.nextDouble() * 30)
        speed.uploadSpeed = rand.nextDouble() * 30
        speed.ping = rand.nextDouble() *200
        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        val hour: Int = c.get(Calendar.HOUR_OF_DAY)
        speed.id = c.timeInMillis
        speed.year = year
        speed.month = month
        speed.day = day
        speed.hour = hour
        return speed
    }
}