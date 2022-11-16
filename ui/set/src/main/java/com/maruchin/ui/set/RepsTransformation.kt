package com.maruchin.ui.set

private const val REPS_MULTIPLIER = 30

internal fun repsRangeToAngleRange(range: IntRange): IntRange =
    (range.first * REPS_MULTIPLIER)..(range.last * REPS_MULTIPLIER)

internal fun repsToAngle(reps: Int): Int =
    reps * REPS_MULTIPLIER

internal fun angleToReps(angle: Int): Int =
    angle / REPS_MULTIPLIER
