package com.arkarzaw.speedtest

import android.app.DatePickerDialog
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.arkarzaw.speedtest.datas.SpeedTestModel
import com.arkarzaw.speedtest.datas.SpeedVO
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity() {

    val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    var month = Calendar.getInstance().get(Calendar.MONTH)
    var year  = Calendar.getInstance().get(Calendar.YEAR)
    var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    private var speedList:List<SpeedVO> = emptyList()
    companion object {
        fun newIntent(context: Context): Intent {
            var intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupController()
        Log.e("SpeedLIst",speedList.size.toString()+"**************")

    }

    private fun setupController() {
        var calendar = Calendar.getInstance()
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        chooseDay.text = "$day ${months[month]} $year"

        changeDate.setOnClickListener {
            showMonthYearDialog()
        }
        getDemoDay()
//        getSpeedByDay(day,month,year)

    }

    private fun setupChart(list:List<SpeedVO>) {


        val entries = ArrayList<Entry>()
        val entrie2 = ArrayList<Entry>()
        var down = 0.0
        var up = 0.0
        var ping = 0.0
        for (entry in list) {
            entries.add(Entry(entry.day.toFloat(), entry.downloadSpeed.toFloat()))
            entrie2.add(Entry(entry.day.toFloat(), entry.uploadSpeed.toFloat()))
            down+=entry.downloadSpeed
            up+=entry.uploadSpeed
            up+=entry.ping
        }

        tvAvgDownload.text="Average Download Speed = "+down/list.size+" Mb"
        tvAvgUpload.text="Average Upload Speed = "+down/list.size+" Mb"
        tvAvgPing.text="Average Ping = "+down/list.size

        //****
        // Controlling X axis
        val xAxis = chart.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        //Customizing x axis value
        val hours = Array(list.size) { i -> (i+1).toString() }
        val formatter = IAxisValueFormatter { value, _ -> hours[value.toInt() - 1] }
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter
        xAxis.labelCount = list.size
        chart.xAxis.setDrawGridLines(false)

        val dataSet = LineDataSet(entries, "DownLoadSpeed")
        val dataSet2 = LineDataSet(entrie2, "UploadSpeed")
        dataSet.setDrawValues(false)
        dataSet.color = ContextCompat.getColor(this, R.color.colorAccent)
        dataSet.valueTextColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        dataSet2.setDrawValues(false)
        dataSet2.color = ContextCompat.getColor(this, R.color.lime_green)
        dataSet2.valueTextColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val lines = ArrayList<LineDataSet>()
        lines.add(dataSet)
        lines.add(dataSet2)
        chart.data = LineData(lines as List<ILineDataSet>?)
        chart.invalidate()
        chart.animateX(2500)
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)

    }

    private fun getDemoDay(){
        val rand = Random()
        for(i in 1..24){
            var speed = SpeedVO()
            speed.id= i.toLong()
            speed.day=i
            speed.month=10
            speed.year=2018
            speed.downloadSpeed = (rand.nextDouble() * 30)
            speed.uploadSpeed = rand.nextDouble() * 30
            SpeedTestModel.getInstance().storeSpeed(speed)
        }
        var speed: LiveData<List<SpeedVO>>? = SpeedTestModel.getInstance().getAllSpeed()
        speed!!.observe(this,android.arch.lifecycle.Observer {
            if(it!=null){
                setupChart(it)
            }
        })
    }

    private fun getSpeedByDay(day:Int , month:Int ,year:Int){
        var speed: LiveData<List<SpeedVO>>? = SpeedTestModel.getInstance().getSpeedByDay(day,month,year)
        speed!!.observe(this,android.arch.lifecycle.Observer {
            if(it!=null){
                setupChart(it)
            }
        })
    }

    private fun showMonthYearDialog() {
        val yearSelected: Int
        val monthSelected: Int
        val daySelected: Int
        val calendar = Calendar.getInstance()
        yearSelected = calendar.get(Calendar.YEAR)
        monthSelected = calendar.get(Calendar.MONTH)
        daySelected = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            chooseDay.text = "$dayOfMonth ${months[monthOfYear]} $year"
//            getSpeedByDay(dayOfMonth,monthOfYear,year)
        }, yearSelected, monthSelected, daySelected)
        datePicker.show()
    }
}
