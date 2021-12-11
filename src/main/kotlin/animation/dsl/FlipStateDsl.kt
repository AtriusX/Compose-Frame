package animation.dsl

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

@DslMarker
annotation class FlipStateDsl

typealias MappingFunction<M> =
    (M) -> Boolean

@FlipStateDsl
data class FlipMapping<M>(
    val states: MappingFunction<M>,
    val state: FlipState,
)

@FlipStateDsl
data class FlipState(
    val enter: EnterTransition = fadeIn(),
    val exit: ExitTransition = fadeOut(),
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
        content: @Composable () -> Unit
    ) {
        val func: MappingFunction<T> = { it in listOf(*mapping) }
        on(func, enter, exit, content)
    }

    @FlipStateDsl
    fun on(
        mapping: MappingFunction<T>,
        enter: EnterTransition = fadeIn(),
        exit: ExitTransition = fadeOut(),
        content: @Composable () -> Unit
    ) {
        val state = FlipState(enter, exit, content)
        on(mapping then state)
    }

    @FlipStateDsl
    infix fun T.then(state: FlipState): FlipMapping<T> {
        val func: MappingFunction<T> = { it == this }
        return func then state
    }

    @FlipStateDsl
    private infix fun MappingFunction<T>.then(state: FlipState): FlipMapping<T> =
        FlipMapping(this, state = state)

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
    content: @Composable () -> Unit,
) = on(
    mapping = { it in range },
    enter = enter,
    exit = exit,
    content = content
)

@FlipStateDsl
fun BuildState<Long>.on(
    range: LongRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit,
) = on(
    mapping = { it in range },
    enter = enter,
    exit = exit,
    content = content
)

@FlipStateDsl
fun BuildState<Char>.on(
    range: CharRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit,
) = on(
    mapping = { it in range },
    enter = enter,
    exit = exit,
    content = content
)

@FlipStateDsl
fun BuildState<UInt>.on(
    range: UIntRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit,
) = on(
    mapping = { it in range },
    enter = enter,
    exit = exit,
    content = content
)


@FlipStateDsl
fun BuildState<ULong>.on(
    range: ULongRange,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    content: @Composable () -> Unit,
) = on(
    mapping = { it in range },
    enter = enter,
    exit = exit,
    content = content
)