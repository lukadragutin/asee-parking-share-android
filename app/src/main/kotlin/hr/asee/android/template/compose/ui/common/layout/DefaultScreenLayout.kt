package hr.asee.android.template.compose.ui.common.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun DefaultScreenLayout(
    screenTitle: String,
    contentPadding: PaddingValues = PaddingValues(all = 20.dp),
    background: Color = MaterialTheme.colors.background,
    onBackground: Color = MaterialTheme.colors.onBackground,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(contentPadding)
    ) {
        Row(modifier = Modifier.padding(bottom = 20.dp)) {
            Text(text = screenTitle.uppercase(), color = onBackground, style = MaterialTheme.typography.h1, overflow = TextOverflow.Ellipsis)
        }

        Row(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}
