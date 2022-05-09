operator fun Number.plus(other: Number): Number {
    return when (this) {
        is Double -> when (other) {
            is Double   -> this + other
            is Long     -> this + other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        is Long   -> when (other) {
            is Long     -> this + other
            is Double   -> this + other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        else      -> throw IllegalArgumentException("Unsupported numeric type")
    }
}

operator fun Number.minus(other: Number): Number {
    return this + -other
}

operator fun Number.unaryMinus(): Number {
    return when (this) {
        is Double   -> -this
        is Long     -> -this
        else        -> throw IllegalArgumentException("Unsupported numeric type")
    }
}

operator fun Number.times(other: Number): Number {
    return when (this) {
        is Double -> when (other) {
            is Double   -> this * other
            is Long     -> this * other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        is Long   -> when (other) {
            is Long     -> this * other
            is Double   -> this * other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        else      -> throw IllegalArgumentException("Unsupported numeric type")
    }
}

operator fun Number.div(other: Number): Number {
    return when (this) {
        is Double -> when (other) {
            is Double   -> this / other
            is Long     -> this / other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        is Long   -> when (other) {
            is Long     -> this / other
            is Double   -> this / other
            else        -> throw IllegalArgumentException("Unsupported numeric type")
        }
        else      -> throw IllegalArgumentException("Unsupported numeric type")
    }
}