sealed class ScreenState

object Loading : ScreenState()

data class Success(val data: String) : ScreenState()

fun main() {
    val state1: ScreenState = Loading
    val state2: ScreenState = Success("Data Loaded")

    println(state1)
    println(state2)
}
