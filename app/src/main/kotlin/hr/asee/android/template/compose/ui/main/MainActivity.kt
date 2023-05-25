package hr.asee.android.template.compose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationDelegate: NavigationDelegate

    @Inject
    lateinit var bottomNavBarDelegate: BottomNavBarDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeCodingTemplateTheme {
                ComposeTemplateRoot(
                    navigationDelegate = navigationDelegate
                )
            }
        }
    }
}
