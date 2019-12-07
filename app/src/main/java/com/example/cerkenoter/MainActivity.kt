package com.example.cerkenoter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.field
import kotlinx.android.synthetic.main.activity_main.view.*

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
    val extraScoreKey = "com.example.cerkenoter.score"
    var scoreList = arrayListOf<String>()
    var restoredScoreList = arrayListOf<String>()

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
            gridSizeModify(field.width)
        }
    }

    private fun gridSizeModify(fieldWidth: Int){
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
            lp.width = fieldWidth / 9
            lp.height = fieldWidth / 9
            btn.layoutParams = lp
        }
    }

    private fun initSpinners(){
        ArrayAdapter.createFromResource(
            this, R.array.judge_number, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            judgeNumberSpinner.adapter = adapter
        }
        judgeNumberSpinner.onItemSelectedListener = SpinnerActivity(this)
    }

    private inner class SpinnerActivity(val a:  AppCompatActivity) : AppCompatActivity(), AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val str = parent?.getSelectedItem().toString()

            a.scoreView.text = a.scoreView.text.toString() + str
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    fun scoreParse(scoreText: CharSequence): ArrayList<String> {
        val consonant = listOf('k', 'l', 'n', 't', 'z', 'x', 'c', 'm', 'p')
        val pieceName = listOf('V', 'P', 'A', 'C', 'T', 'H', 'O', 'S', 'G', 'K', 'M')
        val number = listOf('0', '1', '2', '3', '4', '5')
        var resultList = arrayListOf<String>()
        var tmpString = ""

        for (char in scoreText) {
            if ((char in consonant || char in pieceName || char in number) && tmpString != "") {
                resultList.add(tmpString)
                tmpString = char.toString()
            } else {
                tmpString += char.toString()
            }
        }
        resultList.add(tmpString)
        return resultList
    }

    fun addScoreText(view: View){
        val scoreText = scoreView.text.toString()
        val parsedList: ArrayList<String> = scoreParse(scoreText)

        if (parsedList.size == 4){
            Toast.makeText(this, "Cannot add text! Too much element.", Toast.LENGTH_SHORT).show()
        }else{
            scoreView.text = scoreText + view.tag.toString()
        }
    }

    fun removeScoreText(view: View){
        val scoreText = scoreView.text.toString()
        val parsedList: ArrayList<String> = scoreParse(scoreText)

        if (parsedList.isEmpty()){
            Toast.makeText(this, "Cannot remove text! Already Empty.", Toast.LENGTH_SHORT).show()
        }else{
            var nextText = ""
            for (str in parsedList.dropLast(1)){
                nextText += str
            }
            scoreView.text = nextText
        }
    }

    fun enterScore(view: View){
        val scoreText = scoreView.text.toString()

        if (scoreText.isBlank()){
            val intent = Intent(this, SetDataActivity::class.java).apply {
                putStringArrayListExtra(extraScoreKey, scoreList)
            }
            startActivity(intent)
        }else{
            scoreList.add(scoreText)
            prevScoreView.text = scoreText
            if (restoredScoreList.size == 0) {
                scoreView.text = ""
            } else {
                scoreView.text = restoredScoreList[restoredScoreList.lastIndex]
                restoredScoreList.removeAt(restoredScoreList.lastIndex)
            }
        }
    }

    fun backScore(view: View){
        val prevScoreText = prevScoreView.text.toString()

        when(scoreList.size) {
            0 -> {}
            1 -> {
                prevScoreView.text = ""
                restoredScoreList.add(scoreView.text.toString())
                //toast(restoredScoreList)
            }
            else -> {
                prevScoreView.text = scoreList[scoreList.lastIndex-1]
                restoredScoreList.add(scoreView.text.toString())
                //toast(restoredScoreList)
            }
        }
        scoreView.text = prevScoreText
        scoreList.removeAt(scoreList.lastIndex)
    }
}
