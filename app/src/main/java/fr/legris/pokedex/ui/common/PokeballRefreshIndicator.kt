package fr.legris.pokedex.ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.swiperefresh.*
import fr.legris.pokedex.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


/**
 * A class to encapsulate details of different indicator sizes.
 *
 * @param size The overall size of the indicator.
 * @param arcRadius The radius of the arc.
 * @param strokeWidth The width of the arc stroke.
 * @param arrowWidth The width of the arrow.
 * @param arrowHeight The height of the arrow.
 */
@Immutable
private data class SwipeRefreshIndicatorSizes(
    val size: Dp,
    val arcRadius: Dp,
    val strokeWidth: Dp,
    val arrowWidth: Dp,
    val arrowHeight: Dp,
)

private val sizes = SwipeRefreshIndicatorSizes(
    size = 56.dp,
    arcRadius = 11.dp,
    strokeWidth = 3.dp,
    arrowWidth = 12.dp,
    arrowHeight = 6.dp,
)

/**
 * Indicator composable which is typically used in conjunction with [SwipeRefresh].
 *
 * @param state The [SwipeRefreshState] passed into the [SwipeRefresh] `indicator` block.
 * @param modifier The modifier to apply to this layout.
 * @param scale Whether the indicator should scale up/down as it is scrolled in. Defaults to false.
 * @param elevation The size of the shadow below the indicator.
 */
@ExperimentalCoilApi
@Composable
fun PokeballRefreshIndicator(
    state: SwipeRefreshState,
    refreshTriggerDistance: Dp,
    modifier: Modifier = Modifier,
    scale: Boolean = false,
    refreshingOffset: Dp = 16.dp,
    elevation: Dp = 6.dp,
) {
    val adjustedElevation = when {
        state.isRefreshing -> elevation
        state.indicatorOffset > 0.5f -> elevation
        else -> 0.dp
    }

    val indicatorRefreshTrigger = with(LocalDensity.current) { refreshTriggerDistance.toPx() }

    val indicatorHeight = with(LocalDensity.current) { sizes.size.roundToPx() }
    val refreshingOffsetPx = with(LocalDensity.current) { refreshingOffset.toPx() }

    val slingshot = rememberUpdatedSlingshot(
        offsetY = state.indicatorOffset,
        maxOffsetY = indicatorRefreshTrigger,
        height = indicatorHeight,
    )

    var offset by remember { mutableStateOf(0f) }

    // If the user is currently swiping, we use the 'slingshot' offset directly
    if (state.isSwipeInProgress) {
        offset = slingshot.offset.toFloat()
    }

    LaunchedEffect(state.isSwipeInProgress, state.isRefreshing) {
        // If there's no swipe currently in progress, animate to the correct resting position
        if (!state.isSwipeInProgress) {
            animate(
                initialValue = offset,
                targetValue = when {
                    state.isRefreshing -> indicatorHeight + refreshingOffsetPx
                    else -> 0f
                }
            ) { value, _ ->
                offset = value
            }
        }
    }

    Surface(
        modifier = modifier
            .size(size = sizes.size)
            .graphicsLayer {
                translationY = offset - indicatorHeight

                val scaleFraction = if (scale && !state.isRefreshing) {
                    val progress = offset / indicatorRefreshTrigger.coerceAtLeast(1f)

                    // We use LinearOutSlowInEasing to speed up the scale in
                    LinearOutSlowInEasing
                        .transform(progress)
                        .coerceIn(0f, 1f)
                } else 1f

                scaleX = scaleFraction
                scaleY = scaleFraction
            },
        color = Color.Transparent,
        elevation = adjustedElevation
    ) {
        if (state.isRefreshing) {
            PokeballPulsingRotating()
        } else {
            PokeballRotating(offset - indicatorHeight)
        }
    }
}

private const val CrossfadeDurationMs = 100

@Composable
internal fun rememberUpdatedSlingshot(
    offsetY: Float,
    maxOffsetY: Float,
    height: Int
): Slingshot {
    val offsetPercent = min(1f, offsetY / maxOffsetY)
    val adjustedPercent = max(offsetPercent - 0.4f, 0f) * 5 / 3
    val extraOffset = abs(offsetY) - maxOffsetY

    // Can accommodate custom start and slingshot distance here
    val tensionSlingshotPercent = max(
        0f, min(extraOffset, maxOffsetY * 2) / maxOffsetY
    )
    val tensionPercent = (
            (tensionSlingshotPercent / 4) -
                    (tensionSlingshotPercent / 4).pow(2)
            ) * 2
    val extraMove = maxOffsetY * tensionPercent * 2
    val targetY = height + ((maxOffsetY * offsetPercent) + extraMove).toInt()
    val offset = targetY - height
    val strokeStart = adjustedPercent * 0.8f

    val startTrim = 0f
    val endTrim = strokeStart.coerceAtMost(MaxProgressArc)

    val rotation = (-0.25f + 0.4f * adjustedPercent + tensionPercent * 2) * 0.5f
    val arrowScale = min(1f, adjustedPercent)

    return remember { Slingshot() }.apply {
        this.offset = offset
        this.startTrim = startTrim
        this.endTrim = endTrim
        this.rotation = rotation
        this.arrowScale = arrowScale
    }
}

@Stable
internal class Slingshot {
    var offset: Int by mutableStateOf(0)
    var startTrim: Float by mutableStateOf(0f)
    var endTrim: Float by mutableStateOf(0f)
    var rotation: Float by mutableStateOf(0f)
    var arrowScale: Float by mutableStateOf(0f)
}

internal const val MaxProgressArc = 0.8f

@ExperimentalCoilApi
@Composable
fun PokeballPulsingRotating() {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
                delayMillis = 200
            ),
            repeatMode = RepeatMode.Restart,
        )
    )

    Image(
        painter = painterResource(id = R.drawable.ic_pokeball_loading),
        contentDescription = "Chargement",
        modifier = Modifier
            .fillMaxWidth(0.50f)
            .fillMaxHeight()
            .scale(scale)
            .rotate(rotate),
        contentScale = ContentScale.Inside
    )
}

@ExperimentalCoilApi
@Composable
fun PokeballRotating(rotate: Float) {
    Image(
        painter = painterResource(id = R.drawable.ic_pokeball_loading),
        contentDescription = "Chargement",
        modifier = Modifier
            .fillMaxWidth(0.50f)
            .fillMaxHeight()
            .rotate(rotate),
        contentScale = ContentScale.Inside
    )
}

