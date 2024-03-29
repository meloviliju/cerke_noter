package com.meloviliju.cerkenoter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.android.synthetic.main.activity_set_data.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class SetDataActivity : AppCompatActivity() {
    private var redPlayerName = ""
    private var blackPlayerName = ""
    private var first = ""
    private var season = ""

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_data)
        toast(redPlayerName)
        toast(blackPlayerName)
        if (redPlayerName != "" && blackPlayerName != ""){
            redPlayerNameView.setText(intent.getCharSequenceExtra(MainActivity().extraRedKey))
            blackPlayerNameView.setText(intent.getCharSequenceExtra(MainActivity().extraBlackKey))
        }
        initSpinners()
    }

    private fun initSpinners(){
        ArrayAdapter.createFromResource(
            this, R.array.color_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            firstPlayerSpinner.adapter = adapter
        }
        firstPlayerSpinner.onItemSelectedListener = ColorSpinnerActivity(this)

        ArrayAdapter.createFromResource(
            this, R.array.season_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            seasonNameSpinner.adapter = adapter
        }
        seasonNameSpinner.onItemSelectedListener = SeasonSpinnerActivity(this)
    }

    private inner class ColorSpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            first = parent?.selectedItem.toString()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private inner class SeasonSpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            season = parent?.selectedItem.toString()
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    fun onClickButton(view: View){
        when (view) {
            outputButton -> outputNote()
            resetButton ->{
                intent = Intent().apply {
                    putExtra(MainActivity().extraRedKey, redPlayerName)
                    putExtra(MainActivity().extraBlackKey, blackPlayerName)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun outputNote(){
        val os = shapeNoteData()
        if (null != os){
            val path: File? = getExternalFilesDir(null)
            if (null != path){
                val fileName = SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Date())
                val file = File(path, "note${fileName}.txt")
                val bw = BufferedWriter(FileWriter(file))

                bw.write(os)
                bw.close()
                toast("Saved successfully!\n note$fileName.txt")
            }
        }
    }

    private fun shapeNoteData(): String? {
        redPlayerName = redPlayerNameView.text.toString()
        blackPlayerName = blackPlayerNameView.text.toString()
        val hands = handsEditView.text.toString()
        val noteArrayList = intent.getStringArrayListExtra(MainActivity().extraNoteKey)

        if (null == noteArrayList){
            toast("The note seems to be invalid.\nPlease report this to the developer.")
            return null
        }else if (redPlayerName == "" || blackPlayerName == "" || first == "" || season == ""){
            toast("Fill in the all blanks!")
            return null
        }

        if (noteArrayList.size == 0){
            noteArrayList.add(hands)
        } else {
            noteArrayList[noteArrayList.lastIndex] = "${noteArrayList.lastOrNull()}=$hands"
        }
        val data = DataClass(
            blackPlayerName, redPlayerName,
            first, season,
            noteArrayList
        )

        return try{
            val mapper = jacksonObjectMapper()
            val jsonString = mapper.writeValueAsString(data)
            jsonString
        }catch(e: Exception){
            toast("Sorry, something went wrong!\nCouldn't shape note string.")
            e.printStackTrace()
            null
        }
    }
}

class DataClass(
    @JsonProperty("black") val black: String,
    @JsonProperty("red") val red: String,
    @JsonProperty("first") val first: String,
    @JsonProperty("season") val season: String,
    @JsonProperty("note") val note: ArrayList<String>?
)