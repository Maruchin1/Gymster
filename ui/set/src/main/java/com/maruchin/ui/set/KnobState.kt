package com.maruchin.ui.set

import android.view.MotionEvent
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.atan2

@Composable
internal fun rememberKnobState(angle: Int, angleRange: IntRange) =
    remember(angle, angleRange) {
        KnobState(angle, angleRange)
    }

@Stable
internal class KnobState(angle: Int, private val angleRange: IntRange) {

    var center by mutableStateOf(Offset(0f, 0f))
    var angle by mutableStateOf(angle)

    private var moveStartAngle by mutableStateOf(0)
    private var movePreviousAngle by mutableStateOf(0)
    private var moveEndAngle by mutableStateOf(angle)

    fun startMove(event: MotionEvent) {
        moveStartAngle = calculateCurrentAngle(event)
    }

    fun move(event: MotionEvent) {
        val angle = calculateCurrentAngle(event)
        if (movesFromFirstToLastQuarter(angle)) {
            moveStartAngle += FULL_CIRCLE
        }
        if (movesFromLastToFirstQuarter(angle)) {
            moveStartAngle -= FULL_CIRCLE
        }
        movePreviousAngle = angle
        val newDisplayedAngle = moveEndAngle + (angle - moveStartAngle)
        if (newDisplayedAngle in angleRange) {
            this.angle = newDisplayedAngle
        }
    }

    fun endMove() {
        moveEndAngle = angle
    }

    private fun calculateCurrentAngle(event: MotionEvent): Int {
        var angle = -atan2(center.x - event.x, center.y - event.y) * (HALF_OF_CIRCLE / PI).toFloat()
        if (angle < 0) {
            angle += FULL_CIRCLE
        }
        return angle.toInt()
    }

    private fun movesFromFirstToLastQuarter(angle: Int): Boolean =
        movePreviousAngle < ONE_FOURTH_OF_CIRCLE && angle > THREE_FOURTHS_OF_CIRCLE

    private fun movesFromLastToFirstQuarter(angle: Int): Boolean =
        movePreviousAngle > THREE_FOURTHS_OF_CIRCLE && angle < ONE_FOURTH_OF_CIRCLE

    companion object {
        const val FULL_CIRCLE = 360
        const val ONE_FOURTH_OF_CIRCLE = 90
        const val HALF_OF_CIRCLE = 180
        const val THREE_FOURTHS_OF_CIRCLE = 270
    }
}
