import kotlin.math.pow

data class Matrix(val rows: List<List<Number>>) {

    init {
        if (rows.isEmpty() || rows.first().isEmpty()) {
            throw IllegalArgumentException("Columns and rows cannot be empty")
        }
        if (rows.zipWithNext().any { it.first.size != it.second.size }) {
            throw IllegalArgumentException("Rows must be same size")
        }
    }

    private val columns: List<List<Number>> = List(rows.first().size) { index -> rows.map{ row -> row[index] } }

    fun transpose(): Matrix {
        return Matrix(columns)
    }

    fun determinant(): Number {
        if (rows.size != columns.size) {
            throw UnsupportedOperationException("Determinant can only be calculated for square matrix")
        }

        return when (rows.size) {
            1 -> rows[0][0]
            else -> rows.first()
                .mapIndexed { index, number -> number * cofactor(0, index) }
                .reduce { x, y -> x + y }
        }

    }

    fun inverse(): Matrix {
        val cofactorMatrix = Matrix(
            rows.mapIndexed { i, row -> List(row.size) { j -> cofactor(i, j) } }
        )

        return cofactorMatrix.transpose() / this.determinant()
    }

    operator fun plus(other: Matrix): Matrix {
        if (this.rows.size != other.rows.size || this.columns.size != other.columns.size) {
            throw IllegalArgumentException("Matrix dimensions must be equal")
        }

        val result: List<List<Number>> = this.rows.zip(other.rows)
            .map { it.first.zip(it.second).map { it.first + it.second } }

        return Matrix(result)
    }

    operator fun minus(other: Matrix): Matrix {
        return this + -other
    }

    operator fun unaryMinus(): Matrix {
        return this * -1
    }

    operator fun times(scalar: Number): Matrix {
        return Matrix(rows.map { it.map { it * scalar } })
    }

    operator fun div(scalar: Number): Matrix {
        return Matrix(rows.map { it.map { it / scalar } })
    }

    operator fun times(other: Matrix): Matrix {
        if (this.columns.size != other.rows.size) {
            throw IllegalArgumentException("Number of columns in first matrix must match number of rows in second matrix")
        }

        return Matrix(this.rows.map {
                row -> other.columns.map{
                column -> row.zip(column).map {
                pair -> pair.first * pair.second }
                .reduce{ x, y -> x + y } } })
    }

    operator fun get(i: Int): List<Number> {
        return rows[i]
    }

    operator fun get(i: Int, j: Int): Number {
        return rows[i][j]
    }

    private fun minor(i: Int, j: Int): Number {
        return Matrix(
            rows.filterIndexed { index, _ -> index != i }
                .map { it.filterIndexed { index, _ -> index != j } }
        ).determinant()
    }

    private fun cofactor(i: Int, j: Int): Number {
        return minor(i, j) * ((-1.0).pow(i + 1 + j + 1).toLong())
    }
}