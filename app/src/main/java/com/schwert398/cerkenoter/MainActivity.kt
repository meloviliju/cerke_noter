package com.schwert398.cerkenoter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.field
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
    val extraNoteKey = "com.schwert398.cerkenoter.note"
    private var noteList = arrayListOf<String>()
    private var restoredNoteList = arrayListOf<String>()

    // function for debugging.
    fun toast(text: String) {Toast.makeText(this, text, Toast.LENGTH_SHORT).show()}
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
        ArrayAdapter.createFromResource(
            this, R.array.judge_number, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            judgeSpinner.adapter = adapter
        }
        judgeSpinner.onItemSelectedListener = SpinnerActivity(this)

        ArrayAdapter.createFromResource(
            this, R.array.color_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colorSpinner.adapter = adapter
        }
        colorSpinner.onItemSelectedListener = SpinnerActivity(this)

        ArrayAdapter.createFromResource(
            this, R.array.declaration_name, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            declarationSpinner.adapter = adapter
        }
        declarationSpinner.onItemSelectedListener = SpinnerActivity(this)
    }

    private inner class SpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val str = when (parent?.selectedItem.toString()) {
                "black" ->"B"
                "red" -> "R"
                else -> parent?.selectedItem.toString()
            }

            a.noteView.text = a.noteView.text.toString() + str
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun noteParse(noteText: CharSequence): ArrayList<String> {
        val consonant = listOf('k', 'l', 'n', 't', 'z', 'x', 'c', 'm', 'p')
        val pieceName = listOf('V', 'P', 'A', 'C', 'T', 'H', 'O', 'S', 'G', 'K', 'M')
        val number = listOf('0', '1', '2', '3', '4', '5')
        var resultList = arrayListOf<String>()
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

    fun addNoteText(view: View){
        val noteText = noteView.text.toString()
        val parsedList: ArrayList<String> = noteParse(noteText)

        if (parsedList.size == 4){
            Toast.makeText(this, "Cannot add text! Too much element.", Toast.LENGTH_SHORT).show()
        }else{
            noteView.text = "$noteText${view.tag}"
        }
    }

    fun removeNoteText(view: View){
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
    }

    fun enterNote(view: View){
        val noteText = noteView.text.toString()

        if (noteText.isBlank()){
            val intent = Intent(this, SetDataActivity::class.java).apply {
                putStringArrayListExtra(extraNoteKey, noteList)
            }
            startActivity(intent)
        }else{
            noteList.add(noteText)
            prevNoteView.text = noteText
            if (restoredNoteList.size == 0) {
                noteView.text = ""
            } else {
                noteView.text = restoredNoteList[restoredNoteList.lastIndex]
                restoredNoteList.removeAt(restoredNoteList.lastIndex)
            }
        }
    }

    fun backNote(view: View){
        val prevNoteText = prevNoteView.text.toString()

        when(noteList.size) {
            0 -> {}
            1 -> {
                prevNoteView.text = ""
                restoredNoteList.add(noteView.text.toString())
                //toast(restoredNoteList)
            }
            else -> {
                prevNoteView.text = noteList[noteList.lastIndex-1]
                restoredNoteList.add(noteView.text.toString())
                //toast(restoredNoteList)
            }
        }
        noteView.text = prevNoteText
        noteList.removeAt(noteList.lastIndex)
    }
}
