package data.io

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.io.PrintStream

class Interceptor(private val action: (String) -> Unit) : PrintStream(System.out) {
    private val sysOut: PrintStream = System.out

    init {
        System.setOut(this)
    }

    override fun print(obj: Any) {
        action(obj.toString())
        sysOut.print(obj)
    }

    override fun println() {
        println("\n")
        sysOut.println()
    }

    override fun println(obj: Any) {
        action("$obj\n")
        sysOut.println(obj)
    }
}

@Composable
fun rememberInterceptor(block: (String) -> Unit): Interceptor =
    remember { Interceptor(block) }