package animation.dsl

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

@DslMarker
annotation class FlipStateDsl

@FlipStateDsl
data class FlipMapping<M>(val mapping: M, val state: FlipState)

@FlipStateDsl
data class FlipState(
    @FlipStateDsl val enter: EnterTransition = fadeIn(),
    @FlipStateDsl val exit: ExitTransition = fadeOut(),
    val content: @Composable () -> Unit,
)

data class BuildState<T>(
    val states: ArrayList<FlipMapping<T>> = arrayListOf(),
    var default: @Composable () -> Unit = {},
) {
    @FlipStateDsl
    infix fun on(flipMapping: FlipMapping<T>) {
        states += flipMapping
    }

    @FlipStateDsl
    fun on(
        vararg mapping: T,
        enter: EnterTransition = fadeIn(),
        exit: ExitTransition = fadeOut(),
        content: @Composable () -> Unit,
    ) {
        val state = FlipState(enter, exit, content)
        mapping.forEach { on(it then state) }
    }

    @FlipStateDsl
    infix fun T.then(state: FlipState): FlipMapping<T> =
        FlipMapping(this, state)

    @FlipStateDsl
    infix fun base(default: @Composable () -> Unit) {
        this.default = default
    }
}

@FlipStateDsl
fun BuildState<Int>.on(
    range: IntRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
) = range.forEach {
    on(it, enter = enter, exit = exit, content = content)
}

@FlipStateDsl
fun BuildState<Long>.on(
    range: LongRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
) = range.forEach {
    on(it, enter = enter, exit = exit, content = content)
}

@FlipStateDsl
fun BuildState<Char>.on(
    range: CharRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
) = range.forEach {
    on(it, enter = enter, exit = exit, content = content)
}

@FlipStateDsl
fun BuildState<UInt>.on(
    range: UIntRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
) = range.forEach {
    on(it, enter = enter, exit = exit, content = content)
}

@FlipStateDsl
fun BuildState<ULong>.on(
    range: ULongRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
) = range.forEach {
    on(it, enter = enter, exit = exit, content = content)
}