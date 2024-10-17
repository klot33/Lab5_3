import kotlin.math.sqrt

// Класс для точки
data class Point(val x: Double, val y: Double) {

    // Метод для вычисления расстояния до другой точки
    fun distanceTo(other: Point): Double {
        return sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y))
    }
}

// Функция для безопасного ввода координат с обработкой ошибок
fun readPointCoordinate(prompt: String): Double {
    while (true) {
        try {
            print(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Ошибка: введите корректное число.")
        }
    }
}

// Функция для безопасного ввода количества точек
fun readNumberOfPoints(): Int {
    while (true) {
        try {
            print("Введите количество точек (больше двух): ")
            val count = readLine()?.toInt() ?: throw NumberFormatException()
            if (count > 2) return count
            else println("Ошибка: количество точек должно быть больше двух.")
        } catch (e: NumberFormatException) {
            println("Ошибка: введите корректное целое число.")
        }
    }
}

fun main() {
    // Ввод количества точек
    val numberOfPoints = readNumberOfPoints()

    // Список для хранения точек
    val points = mutableListOf<Point>()

    // Ввод координат точек с проверкой на уникальность
    for (i in 1..numberOfPoints) {
        println("Введите координаты точки №$i:")
        val x = readPointCoordinate("x: ")
        val y = readPointCoordinate("y: ")

        val newPoint = Point(x, y)

        // Проверка, что такой точки еще нет в списке
        if (points.contains(newPoint)) {
            println("Ошибка: такая точка уже существует. Введите другую точку.")
        } else {
            points.add(newPoint)
        }
    }

    // Инициализация минимального и максимального расстояний
    var minDistance = Double.MAX_VALUE
    var maxDistance = Double.MIN_VALUE

    // Поиск минимального и максимального расстояний между парами точек
    for (i in 0 until points.size) {
        for (j in i + 1 until points.size) {
            val distance = points[i].distanceTo(points[j])
            if (distance < minDistance) {
                minDistance = distance
            }
            if (distance > maxDistance) {
                maxDistance = distance
            }
        }
    }

    // Вывод результатов
    println("Минимальное расстояние между точками: $minDistance")
    println("Максимальное расстояние между точками: $maxDistance")
}