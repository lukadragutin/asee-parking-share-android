package hr.asee.android.template.compose.ui.common.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Gray
import hr.asee.android.template.domain.model.navigation.NavigationItem

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    onNavElementClicked: (NavigationItem) -> Unit,
    selectedElement: NavigationItem?,
) {

    BottomNavigation(
        modifier = modifier
            .height(64.dp)
            .drawBehind {
                drawLine(
                    color = Gray,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f),
                    strokeWidth = 1.dp.toPx()
                )
            },
        backgroundColor = MaterialTheme.colors.background
    ) {
        items.forEach { element ->
            BottomNavigationItem(
                selected = element == selectedElement,
                onClick = { onNavElementClicked },
                icon = {
                    Icon(
                        painter = painterResource(id = element.icon),
                        contentDescription = element.contentDescription?.let { stringResource(id = it) },
                        tint = if (element == selectedElement) AssecoBlue else DarkGray,
                        modifier = Modifier.size(100.dp)
                    )
                }
            )
        }
    }
}
