package com.arkarzaw.speedtest.datas

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "Speed")
class SpeedVO {

    @PrimaryKey
    @NonNull
    var id : Long = 0

    var day = 0

    var month = 0

    var year = 0

    var hour = 0

    var downloadSpeed = 0.0

    var uploadSpeed = 0.0

    var ping = 0.0

}