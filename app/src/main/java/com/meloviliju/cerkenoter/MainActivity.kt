package com.meloviliju.cerkenoter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.math.min

private inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

class MainActivity : AppCompatActivity() {
    val extraNoteKey = "com.meloviliju.cerkenoter.NOTE"
    val extraRedKey = "com.meloviliju.cerkenoter.RED_PLAYER_NAME"
    val extraBlackKey = "com.meloviliju.cerkenoter.BLACK_PLAYER_NAME"
    private val pickContactRequest = 1001
    private val noteList = arrayListOf<String>()
    private val restoredNoteList = arrayListOf<String>()

    private var redPlayerName: String? = null
    private var blackPlayerName: String? = null

    // function for debugging.
    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    fun toast(text: ArrayList<String>){
        var str = ""
        for (elm in text) str += "$elm "
        toast(str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSpinners()
        field.afterMeasured {
            gridSizeModify(min(field.width, field.height))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickContactRequest){
            if (resultCode == Activity.RESULT_OK) {
                noteList.clear()
                restoredNoteList.clear()
                noteView.text = ""
                prevNoteView.text = ""
                if (null == intent) toast("data is null")
                redPlayerName = data?.getStringExtra(extraRedKey)
                blackPlayerName = data?.getStringExtra(extraBlackKey)
                if (redPlayerName == null || redPlayerName == "") toast("red is null")
                if (blackPlayerName == null || blackPlayerName == "") toast("black is null")
                toast("$redPlayerName, $blackPlayerName")
            }
        }
    }

    private fun gridSizeModify(fieldSize: Int){
        val buttonList = arrayListOf(
            ka, ke, ki, ku, ko, ky, kai, kau, kia,
            la, le, li, lu, lo, ly, lai, lau, lia,
            na, ne, ni, nu, no, ny, nai, nau, nia,
            ta, te, ti, tu, to, ty, tai, tau, tia,
            za, ze, zi, zu, zo, zy, zai, zau, zia,
            xa, xe, xi, xu, xo, xy, xai, xau, xia,
            ca, ce, ci, cu, co, cy, cai, cau, cia,
            ma, me, mi, mu, mo, my, mai, mau, mia,
            pa, pe, pi, pu, po, py, pai, pau, pia
            )
        for (btn in buttonList){
            val lp = btn.layoutParams
            lp.width = fieldSize / 9
            lp.height = fieldSize / 9
            btn.layoutParams = lp
        }
    }

    private fun initSpinners(){
        val spinnerArray = arrayOf(judgeSpinner, colorSpinner, declarationSpinner)
        val stringArrayArray = arrayOf(
            R.array.judge_number, R.array.color_name, R.array.declaration_name
        )
        for ((spinner, stringArray) in spinnerArray.zip(stringArrayArray)) {
            ArrayAdapter.createFromResource(
                this, stringArray, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
            spinner.onItemSelectedListener = SpinnerActivity(this)
        }
    }

    private inner class SpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val str = when (parent?.selectedItem.toString()) {
                "black" -> "B"
                "red" -> "R"
                else -> parent?.selectedItem.toString()
            }
            a.noteView.text = a.noteView.text.toString() + str
            a.enterButton.text = a.resources.getText(R.string.button_text_enter)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun noteParse(noteText: CharSequence): ArrayList<String> {
        val consonant = listOf('k', 'l', 'n', 't', 'z', 'x', 'c', 'm', 'p')
        val pieceName = listOf('V', 'P', 'A', 'C', 'T', 'H', 'O', 'S', 'G', 'K', 'M')
        val number = listOf('0', '1', '2', '3', '4', '5')
        val resultList = arrayListOf<String>()
        var tmpString = ""

        for (char in noteText) {
            if (tmpString.startsWith("=")){
                if (tmpString == "=tymor" || tmpString == "=taxt"){
                    resultList.add(tmpString)
                }else{
                    tmpString += char.toString()
                }
            }else if ((char in consonant || char in pieceName || char in number || char == '=') && tmpString != "") {
                resultList.add(tmpString)
                tmpString = char.toString()
            } else {
                tmpString += char.toString()
            }
        }
        resultList.add(tmpString)
        return resultList
    }

    fun onClickButton(view: View){
        when (view) {
            deleteButton -> removeNoteText()
            enterButton -> {
                if (noteView.text.isBlank()) transitActivity()
                else enterNote()
            }
        }
    }

    fun addNoteText(view: View){
        val noteText = noteView.text.toString()
        val parsedList: ArrayList<String> = noteParse(noteText)

        if (parsedList.size == 4){
            Toast.makeText(this, "Cannot add text! Too much element.", Toast.LENGTH_SHORT).show()
        }else{
            noteView.text = "$noteText${view.tag}"
            }
        enterButton.text = resources.getText(R.string.button_text_enter)
    }

    private fun removeNoteText(){
        val noteText = noteView.text.toString()
        val parsedList: ArrayList<String> = noteParse(noteText)

        if (parsedList.isEmpty()){
            Toast.makeText(this, "Cannot remove text! Already Empty.", Toast.LENGTH_SHORT).show()
        }else{
            var nextText = ""
            for (str in parsedList.dropLast(1)){
                nextText += str
            }
            noteView.text = nextText
        }
        if (noteView.text.isEmpty()){
            enterButton.text = resources.getText(R.string.button_text_over)
        }
    }

    private fun transitActivity(){
        Intent(this, SetDataActivity::class.java).apply {
            putExtra(extraRedKey, redPlayerName)
            putExtra(extraBlackKey, blackPlayerName)
            putStringArrayListExtra(extraNoteKey, noteList)
        }.also {
            startActivityForResult(it, pickContactRequest)
        }
    }

    private fun enterNote(){
        val noteText = noteView.text.toString()
        noteList.add(noteText)
        prevNoteView.text = noteText
        if (restoredNoteList.size == 0) {
            noteView.text = ""
        } else {
            noteView.text = restoredNoteList.last()
            restoredNoteList.removeAt(restoredNoteList.lastIndex)
        }
        enterButton.text = resources.getText(R.string.button_text_over)
    }

    fun backNote(view: View){
        val prevNoteText = prevNoteView.text.toString()

        when(noteList.size) {
            0 -> {}
            1 -> {
                prevNoteView.text = ""
                restoredNoteList.add(noteView.text.toString())
            }
            else -> {
                prevNoteView.text = noteList[noteList.lastIndex-1]
                restoredNoteList.add(noteView.text.toString())
            }
        }
        noteView.text = prevNoteText
        noteList.removeAt(noteList.lastIndex)
    }
}
