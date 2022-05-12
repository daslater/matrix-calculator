fun createMatrixFromStrings(stringMatrix: List<List<String>>): Matrix {
    return Matrix(
        stringMatrix.map { row -> row.map {
            if (it.contains('.')) it.toDouble() else it.toLong() } }
    )
}