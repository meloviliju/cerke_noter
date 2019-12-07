package com.example.cerkenoter

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_set_data.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class SetDataActivity : AppCompatActivity() {
    private var first = ""
    private var season = ""

    fun toast(text: String) {Toast.makeText(this, text, Toast.LENGTH_SHORT).show()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_data)
        initSpinners()
    }

    private fun initSpinners(){
        ArrayAdapter.createFromResource(
            this, R.array.color_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            firstPlayerSpinner.adapter = adapter
        }
        firstPlayerSpinner.onItemSelectedListener = colorSpinnerActivity(this)

        ArrayAdapter.createFromResource(
            this, R.array.season_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            seasonNameSpinner.adapter = adapter
        }
        seasonNameSpinner.onItemSelectedListener = seasonSpinnerActivity(this)
    }

    private inner class colorSpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            first = parent?.getSelectedItem().toString()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private inner class seasonSpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            season = parent?.getSelectedItem().toString()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    fun outputScore(view: View){
        val os = shapeScoreData()
        if (null != os){
            val path: File? = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            if (null != path){
                val fileName = SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Date())
                val file = File(path, "${fileName}.txt")
                val bw = BufferedWriter(FileWriter(file))

                bw.write(os)
                bw.close()
                toast("Saved successfully! $fileName.txt")
            }
        }
    }

    private fun shapeScoreData(): String? {
        val redPlayerName = redPlayerNameView.text.toString()
        val blackPlayerName = blackPlayerNameView.text.toString()
        val scoreArrayList = intent.getStringArrayListExtra(MainActivity().extraScoreKey)
        val data = DataClass(
            blackPlayerName,
            redPlayerName,
            first,
            season,
            scoreArrayList
        )

        if (redPlayerName == "" || blackPlayerName == "" || first == "" || season == ""){
            toast("Fill in the all blanks!")
            return null
        }

        try{
            val mapper = jacksonObjectMapper()
            val jsonString = mapper.writeValueAsString(data)
            return jsonString
        }catch(e: Exception){
            toast("Sorry, something went wrong!\nCouldn't shape score string.")
            e.printStackTrace()
            return null
        }
    }
}

class DataClass(
    @JsonProperty("black") val black: String,
    @JsonProperty("red") val red: String,
    @JsonProperty("first") val first: String,
    @JsonProperty("season") val season: String,
    @JsonProperty("score") val score: List<String>?
)