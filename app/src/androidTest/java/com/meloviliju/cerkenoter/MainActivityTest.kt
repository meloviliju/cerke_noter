package com.meloviliju.cerkenoter


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatButton = onView(
            allOf(
                withId(R.id.nai),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    56
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.kauk),
                childAtPosition(
                    allOf(
                        withId(R.id.innerPieceQueue),
                        childAtPosition(
                            withId(R.id.outerPieceQueue),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatImageView.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.ny),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    47
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.noteView), withText("naiPny"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("naiPny")))

        val appCompatButton3 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton3.perform(click())

        val appCompatButton4 = onView(
            allOf(
                withId(R.id.me),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    16
                ),
                isDisplayed()
            )
        )
        appCompatButton4.perform(click())

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.gua),
                childAtPosition(
                    allOf(
                        withId(R.id.innerPieceQueue),
                        childAtPosition(
                            withId(R.id.outerPieceQueue),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatImageView2.perform(scrollTo(), click())

        val appCompatButton5 = onView(
            allOf(
                withId(R.id.mi),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    25
                ),
                isDisplayed()
            )
        )
        appCompatButton5.perform(click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.mu),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    34
                ),
                isDisplayed()
            )
        )
        appCompatButton6.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.judgeSpinner),
                childAtPosition(
                    allOf(
                        withId(R.id.optionButtonBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            6
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.noteView), withText("meAmimu2"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("meAmimu2")))

        val appCompatButton7 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.cai),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    60
                ),
                isDisplayed()
            )
        )
        appCompatButton8.perform(click())

        val appCompatImageView3 = onView(
            allOf(
                withId(R.id.kauk),
                childAtPosition(
                    allOf(
                        withId(R.id.innerPieceQueue),
                        childAtPosition(
                            withId(R.id.outerPieceQueue),
                            0
                        )
                    ),
                    1
                )
            )
        )
        appCompatImageView3.perform(scrollTo(), click())

        val appCompatButton9 = onView(
            allOf(
                withId(R.id.mai),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    61
                ),
                isDisplayed()
            )
        )
        appCompatButton9.perform(click())

        val appCompatButton10 = onView(
            allOf(
                withId(R.id.my),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    52
                ),
                isDisplayed()
            )
        )
        appCompatButton10.perform(click())

        val appCompatButton11 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton11.perform(click())

        val appCompatButton12 = onView(
            allOf(
                withId(R.id.le),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        appCompatButton12.perform(click())

        val appCompatImageView4 = onView(
            allOf(
                withId(R.id.gua),
                childAtPosition(
                    allOf(
                        withId(R.id.innerPieceQueue),
                        childAtPosition(
                            withId(R.id.outerPieceQueue),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatImageView4.perform(scrollTo(), click())

        val appCompatButton13 = onView(
            allOf(
                withId(R.id.li),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    19
                ),
                isDisplayed()
            )
        )
        appCompatButton13.perform(click())

        val appCompatButton14 = onView(
            allOf(
                withId(R.id.lu),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    28
                ),
                isDisplayed()
            )
        )
        appCompatButton14.perform(click())

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.judgeSpinner),
                childAtPosition(
                    allOf(
                        withId(R.id.optionButtonBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            6
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatSpinner2.perform(click())

        val appCompatButton15 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton15.perform(click())

        val appCompatButton16 = onView(
            allOf(
                withId(R.id.mau),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    70
                ),
                isDisplayed()
            )
        )
        appCompatButton16.perform(click())

        val appCompatImageView5 = onView(
            allOf(
                withId(R.id.gua),
                childAtPosition(
                    allOf(
                        withId(R.id.innerPieceQueue),
                        childAtPosition(
                            withId(R.id.outerPieceQueue),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatImageView5.perform(scrollTo(), click())

        val appCompatButton17 = onView(
            allOf(
                withId(R.id.cau),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    69
                ),
                isDisplayed()
            )
        )
        appCompatButton17.perform(click())

        val appCompatButton18 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton18.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("mauAcau"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("mauAcau")))

        val appCompatTextView = onView(
            allOf(
                withId(R.id.prevNoteView), withText("mauAcau"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatTextView2 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("leAlilu1"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView2.perform(click())

        val appCompatTextView3 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("caiPmaimy"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView3.perform(click())

        val appCompatTextView4 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("meAmimu2"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView4.perform(click())

        val appCompatButton19 = onView(
            allOf(
                withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton19.perform(click())

        val appCompatButton20 = onView(
            allOf(
                withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton20.perform(click())

        val appCompatButton21 = onView(
            allOf(
                withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton21.perform(click())

        val appCompatButton22 = onView(
            allOf(
                withId(R.id.xe),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    14
                ),
                isDisplayed()
            )
        )
        appCompatButton22.perform(click())

        val appCompatButton23 = onView(
            allOf(
                withId(R.id.ze),
                childAtPosition(
                    allOf(
                        withId(R.id.gridButtons),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    13
                ),
                isDisplayed()
            )
        )
        appCompatButton23.perform(click())

        val appCompatSpinner3 = onView(
            allOf(
                withId(R.id.judgeSpinner),
                childAtPosition(
                    allOf(
                        withId(R.id.optionButtonBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            6
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatSpinner3.perform(click())

        val appCompatSpinner4 = onView(
            allOf(
                withId(R.id.judgeSpinner),
                childAtPosition(
                    allOf(
                        withId(R.id.optionButtonBar),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            6
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatSpinner4.perform(click())

        val appCompatButton24 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton24.perform(click())

        val textView4 = onView(
            allOf(
                withId(R.id.noteView), withText("caiPmaimy"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("caiPmaimy")))

        val textView5 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("meAxeze1"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("meAxeze1")))

        val appCompatButton25 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton25.perform(click())

        val appCompatButton26 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton26.perform(click())

        val appCompatButton27 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton27.perform(click())

        val appCompatTextView5 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("mauAcau"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView5.perform(click())

        val appCompatTextView6 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("leAlilu1"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView6.perform(click())

        val appCompatTextView7 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("caiPmaimy"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView7.perform(click())

        val appCompatTextView8 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("meAxeze1"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView8.perform(click())

        val appCompatTextView9 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("naiPny"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatTextView9.perform(click())

        val appCompatButton28 = onView(
            allOf(
                withText("Enter"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatButton28.perform(click())

        val textView6 = onView(
            allOf(
                withId(R.id.prevNoteView), withText("naiPny"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("naiPny")))

        val textView7 = onView(
            allOf(
                withId(R.id.noteView), withText("meAxeze1"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("meAxeze1")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
