package com.maruchin.ui.set

private const val WEIGHT_MULTIPLIER = 60f

internal fun weightRangeToAngleRange(range: IntRange): IntRange =
    (range.first * WEIGHT_MULTIPLIER.toInt())..(range.last * WEIGHT_MULTIPLIER.toInt())

internal fun weightToAngle(weight: Float): Int =
    (weight * WEIGHT_MULTIPLIER).toInt()

internal fun angleToWeight(angle: Int): Float =
    ((angle / WEIGHT_MULTIPLIER) / 0.5f).toInt() * 0.5f
