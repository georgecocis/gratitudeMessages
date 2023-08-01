package ro.nexttech.journalapp.ui.navigation

import DisplayGratitudeMessages
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ro.nexttech.journalapp.ui.view.DisplayExtendedGratitudeMessage
import ro.nexttech.journalapp.viewmodel.GratitudeMessageViewModel
import java.util.UUID

sealed class Screen(val route: String) {
    object GratitudeMessagesScreen : Screen("gratitudeMessagesScreen")
    object ExtendedGratitudeMessageScreen : Screen("gratitudeMessagesScreen/{gratitudeMessageId}") {
        fun createRoute(gratitudeMessageId: UUID): String =
            "gratitudeMessagesScreen/$gratitudeMessageId"
    }
}

@Composable
fun NavGraph(
    gratitudeMessageViewModel: GratitudeMessageViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.GratitudeMessagesScreen.route
    ) {
        composable(
            Screen.GratitudeMessagesScreen.route
        ) {
            val gratitudeMessages =
                gratitudeMessageViewModel.gratitudeMessages.collectAsState().value
            DisplayGratitudeMessages(
                gratitudeMessageViewModel = gratitudeMessageViewModel,
                navController = navController
            )
        }
        composable(
            route = Screen.ExtendedGratitudeMessageScreen.route,
            arguments = listOf(navArgument("gratitudeMessageId") { type = NavType.StringType })
        ) { backStackEntry ->
            val gratitudeMessageId =
                UUID.fromString(requireNotNull(backStackEntry.arguments?.getString("gratitudeMessageId")))
            val extendedGratitudeMessage =
                gratitudeMessageViewModel.getGratitudeMessageById(gratitudeMessageId)
            DisplayExtendedGratitudeMessage(extendedGratitudeMessage = extendedGratitudeMessage,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}