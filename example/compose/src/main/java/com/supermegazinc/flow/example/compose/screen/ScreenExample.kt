package com.supermegazinc.flow.example.compose.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.supermegazinc.flow.compose.utils.compose
import com.supermegazinc.flow.compose.utils.getValue
import com.supermegazinc.flow.compose.utils.setValue

@Composable
fun ScreenExample() {

	val vm = viewModel<ScreenExampleVM>()

	//Convert SuperMutableState to SuperMutableStateCompose
	val backendSuperMutableFlow = vm.backendSuperMutableFlow.compose()

	//Access getter and setter through 'value' and 'set()'
	val v: String = backendSuperMutableFlow.value
	fun set() { backendSuperMutableFlow.set("hey") }

	//Or even easier by delegation
	var input by backendSuperMutableFlow

	Column(
		modifier = Modifier
			.fillMaxSize(),
		verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Text(input)
		TextField(
			value = input,
			onValueChange = { input = it }
		)
	}

}

@Preview
@Composable
fun ScreenExamplePreview() {
	Surface {
		ScreenExample()
	}
}