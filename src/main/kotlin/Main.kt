import java.util.*
import kotlin.Exception

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        printMainMenu()
        val choice = scanner.nextInt()
        scanner.nextLine()
        if (choice == 0) break
        try {
            when(choice) {
                1 -> {
                    val (matrix1, matrix2) = getTwoMatricesInput(scanner)
                    println("The result is:")
                    println(matrix1 + matrix2)
                }
                2 -> {
                    val matrix = getOneMatrixInput(scanner)
                    val constant = getConstant(scanner)
                    println("The result is:")
                    println(matrix * constant)
                }
                3 -> {
                    val (matrix1, matrix2) = getTwoMatricesInput(scanner)
                    println("The result is:")
                    println(matrix1 * matrix2)
                }
                4 -> {
                    val matrix = getOneMatrixInput(scanner)
                    println("The result is:")
                    println(matrix.transpose())
                }
                5 -> {
                    val matrix = getOneMatrixInput(scanner)
                    println("The result is: \n${matrix.determinant()}")
                }
                6 -> {
                    try {
                        val matrix = getOneMatrixInput(scanner)
                        println("The result is:")
                        println(matrix.inverse())
                    } catch (e: ArithmeticException) {
                        println("This matrix doesn't have an inverse.")
                    }
                }
                else -> continue
            }
            println()
        } catch (e: Exception) {
            when (e) {
                is IllegalArgumentException, is UnsupportedOperationException -> {
                    println("Error: " + e.message)
                    continue
                }
                else -> throw e
            }
        }
    }
}

fun printMainMenu() {
    print("""
        1. Add matrices
        2. Multiply matrix by a constant
        3. Multiply matrices
        4. Transpose matrix
        5. Calculate a determinant
        6. Inverse matrix
        0. Exit
        Your choice: 
    """.trimIndent())
}

fun getOneMatrixInput(scanner: Scanner): Matrix {
    print("Enter size of matrix: ")
    val (numRows, numColumns) = getDimensions(scanner)
    println("Enter matrix:")

    return Matrix.fromStrings(getMatrixString(scanner, numRows))
}

fun getTwoMatricesInput(scanner: Scanner): Pair<Matrix, Matrix> {
    print("Enter size of first matrix: ")
    val (numRows1, numColumns1) = getDimensions(scanner)
    println("Enter first matrix:")
    val matrix1 = Matrix.fromStrings(getMatrixString(scanner, numRows1))

    print("Enter size of second matrix: ")
    val (numRows2, numColumns2) = getDimensions(scanner)
    println("Enter second matrix:")
    val matrix2 = Matrix.fromStrings(getMatrixString(scanner, numRows2))

    return matrix1 to matrix2
}

fun getDimensions(scanner: Scanner): Pair<Int, Int> {
    val numRows = scanner.nextInt()
    val numColumns = scanner.nextInt()
    scanner.nextLine()
    return numRows to numColumns
}

fun getMatrixString(scanner: Scanner, numRows: Int): List<List<String>> {
    return List(numRows) { scanner.nextLine().split(" ") }
}

fun getConstant(scanner: Scanner): Number {
    println("Enter constant:")
    val numString = scanner.nextLine()
    return if (numString.contains('.')) numString.toDouble() else numString.toLong()
}
