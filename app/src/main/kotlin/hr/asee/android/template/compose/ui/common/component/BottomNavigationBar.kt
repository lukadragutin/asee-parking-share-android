package hr.asee.android.template.compose.ui.common.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.domain.model.navigation.NavigationItem

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationItem>,
    onNavElementClicked: (NavigationItem) -> Unit,
    selectedElement: NavigationItem?,
) {

    BottomNavigation(modifier = modifier) {
        items.forEach { element ->
            BottomNavigationItem(
                selected = element == selectedElement,
                onClick = { onNavElementClicked(element) },
                icon = {
                    Icon(
                        painter = painterResource(id = element.icon),
                        contentDescription = element.contentDescription?.let { stringResource(id = it) }
                    )
                },
                label = element.label?.let {
                    {
                        Text(text = stringResource(id = it))
                    }
                }
            )
        }
    }
}
