sealed class ScreenState

object Loading : ScreenState()

data class Success(val data: String) : ScreenState()

data class Error(val errorMsg: String) : ScreenState()

fun renderUI(state: ScreenState) {
    when (state) {
        is Loading -> println("Loading...")
        is Success -> println("Success: ${state.data}")
        is Error -> println("Error: ${state.errorMsg}")
    }
}

fun main() {
    renderUI(Loading)
    renderUI(Success("Data Loaded"))
    renderUI(Error("Network Error"))
}
