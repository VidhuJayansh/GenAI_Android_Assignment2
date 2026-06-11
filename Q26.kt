import kotlinx.coroutines.*

data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int
)

fun List<CartItem>.calculateTotal(): Double {
    return sumOf { it.price * it.quantity }
}

sealed class CheckoutResult

object Processing : CheckoutResult()

data class Success(val receiptId: String) : CheckoutResult()

data class Failed(val reason: String) : CheckoutResult()

object PaymentProcessor {

    suspend fun processPayment(
        cart: List<CartItem>
    ): CheckoutResult {

        delay(2000)

        val total = cart.calculateTotal()

        return if (total > 1000) {
            Failed("Insufficient funds for large transaction")
        } else {
            Success("TXN-${(1000..9999).random()}")
        }
    }
}

fun main() {

    runBlocking {

        val cheapCart = listOf(
            CartItem("Book", 50.0, 2),
            CartItem("Pen", 10.0, 3)
        )

        val expensiveCart = listOf(
            CartItem("Laptop", 1200.0, 1),
            CartItem("Monitor", 300.0, 1)
        )

        launch {

            println("Cheap Cart Processing...")

            when (val result =
                PaymentProcessor.processPayment(cheapCart)) {

                is Success ->
                    println("Cheap Cart Success: ${result.receiptId}")

                is Failed ->
                    println("Cheap Cart Failed: ${result.reason}")

                is Processing ->
                    println("Processing")
            }
        }

        launch {

            println("Expensive Cart Processing...")

            when (val result =
                PaymentProcessor.processPayment(expensiveCart)) {

                is Success ->
                    println("Expensive Cart Success: ${result.receiptId}")

                is Failed ->
                    println("Expensive Cart Failed: ${result.reason}")

                is Processing ->
                    println("Processing")
            }
        }
    }
}
