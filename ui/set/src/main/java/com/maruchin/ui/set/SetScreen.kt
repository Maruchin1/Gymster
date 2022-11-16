package com.maruchin.ui.set

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.GymsterAppBar
import com.maruchin.core.ui.GymsterExerciseView
import com.maruchin.core.ui.GymsterTheme
import com.maruchin.model.training.sampleTrainings

private enum class Mode { WEIGHT, REPS }

@Composable
internal fun SetScreen(
    state: SetUiState,
    onCompleteSet: (weightAngle: Int, repsAnge: Int) -> Unit,
    onBack: () -> Unit,
) {
    val weightState = rememberKnobState(
        angle = state.weightAngle,
        angleRange = state.weightAngleRange
    )
    val repsState = rememberKnobState(
        angle = state.repsAngle,
        angleRange = state.repsAngleRange
    )
    var mode by remember { mutableStateOf(Mode.WEIGHT) }
    Scaffold(
        topBar = {
            GymsterAppBar(
                title = "${stringResource(R.string.set)} ${state.setNumber}.",
                onBack = onBack
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                GymsterExerciseView(
                    name = state.exerciseName,
                    numOfSets = state.numOfSets,
                    repsRange = state.repsRange,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    when (mode) {
                        Mode.WEIGHT -> Knob(knobState = weightState)
                        Mode.REPS -> Knob(knobState = repsState)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SelectableValue(
                            text = "${angleToWeight(weightState.angle)} ${stringResource(R.string.weight_unit)}",
                            selected = mode == Mode.WEIGHT,
                            onSelect = { mode = Mode.WEIGHT },
                        )
                        Box(modifier = Modifier.padding(vertical = 16.dp)) {
                            Divider(
                                thickness = 4.dp,
                                modifier = Modifier.width(182.dp)
                            )
                        }
                        SelectableValue(
                            text = "${angleToReps(repsState.angle)} ${stringResource(R.string.reps)}",
                            selected = mode == Mode.REPS,
                            onSelect = { mode = Mode.REPS }
                        )
                    }
                }
            }
            Button(
                onClick = {
                    onCompleteSet(weightState.angle, repsState.angle)
                    onBack()
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(text = stringResource(R.string.complete_set).uppercase())
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GymsterTheme {
        SetScreen(
            state = SetUiState(
                sampleTrainings[2],
                sampleTrainings[0],
            ),
            onCompleteSet = { _, _ -> },
            onBack = {},
        )
    }
}
