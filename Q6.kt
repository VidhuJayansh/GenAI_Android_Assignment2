data class User(
    val id: Int,
    val username: String,
    val isActive: Boolean
)

fun main() {
    val user = User(1, "Jyoti", true)
    println(user)
}
