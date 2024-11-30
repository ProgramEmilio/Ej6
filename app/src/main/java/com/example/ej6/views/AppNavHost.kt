import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ej6.viewmodels.BooksViewModel
import com.example.ej6.views.AddBookScreen
import com.example.ej6.views.BookListScreen
import com.example.ej6.views.EditBookScreen

@Composable
fun AppNavHost(viewModel: BooksViewModel) {
    // Recuerda crear un NavController
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") {
            // Aquí pasas el NavController a BookListScreen
            BookListScreen(viewModel = viewModel, navController = navController)
        }
        composable("addBook") {
            // Aquí puedes navegar a la pantalla de añadir libro
            AddBookScreen(viewModel = viewModel, onBookAdded = { navController.popBackStack() })
        }
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                val book = viewModel.books.value.firstOrNull { it.id == id }
                book?.let {
                    EditBookScreen(book, viewModel) { navController.popBackStack() }
                }
            }
        }
    }
}
