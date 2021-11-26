val data = mutableMapOf<Int, String>()
val slots = mutableMapOf<Int, String>()
val numbers = mutableMapOf<Int, String>()

fun main() {
    while (true) {
        val a = readLine()!!
        if (a == "exit") {
            break
        } else {
            val b = a.split(" ")
            when (b.first()) {
                "exit" -> break
                "" -> continue
                "create" -> create(b)
                "status" -> status()
                "park" -> park(b)
                "leave" -> leave(b)
                "reg_by_color" -> reg(b)
                "spot_by_color" -> spotColor(b)
                "spot_by_reg" -> spotReg(b)
            }
        }
    }
}

fun spotReg(b: List<String>) {
    if (check()) {
        var res = ""
        if (check()) {
            for((key, value) in numbers){
                if (value == b.last()) {
                    res += " $key,"
                }
            }
            if (res.isEmpty()) {
                println("No cars with registration number ${b.last()} were found.")
            } else {
                println(res.trim().removeSuffix(","))
            }
        }
    }
}

fun spotColor(b: List<String>) {
    var res = ""
    if (check()) {
        for((key, value) in slots){
            if (value == b.last().uppercase()) {
                res += " $key,"
            }
        }
        if (res.isEmpty()) {
            println("No cars with color ${b.last().uppercase()} were found.")
        } else {
            println(res.trim().removeSuffix(","))
        }
    }
}

fun reg(b: List<String>) {
    var res = ""
    if (check()) {
        for((key, value) in slots){
            if (value == b.last().uppercase()) {
                res += " ${numbers[key]},"
            }
        }
        if (res.isEmpty()) {
            println("No cars with color ${b.last().uppercase()} were found.")
        } else {
            println(res.trim().removeSuffix(","))
        }
    }
}

fun check(): Boolean {
    if (data.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return false
    }
    return true
}

fun create(b: List<String>) {
    data.clear()
    var num = 1
    for (i in 0 until b.last().toInt()) {
        data[num] = "empty"
        num += 1
    }
    println("Created a parking lot with ${b.last()} spots.")
}

fun status() {
    var total = 0
    if (check()) {
        for ((key, value) in data) {
            if (value != "empty") {
                println("$key ${data[key]}")
            } else {
                total += 1
            }
        }
        if (total == data.size) {
            println("Parking lot is empty.")
        }
    }
}

fun park(b: List<String>) {
    var str = ""
    for (i in 1 until b.size) {
        str += b[i] + " "
    }
    if (check()) {
        if (data.containsValue("empty")) {
            for ((key, value) in data) {
                if (value == "empty") {
                    data[key] = str.trim()
                    slots[key] = b[2].uppercase()
                    numbers[key] = b[1]
                    println("${b.last()} car parked in spot ${key}.")
                    break
                }
            }
        } else {
            println("Sorry, the parking lot is full.")
        }
    }
}

fun leave(b: List<String>) {
    val d = b.last().toInt()
    if (check()) {
        when {
            data[d] != "empty" -> {println("Spot $d is free.")
                data[d] = "empty"
                slots.remove(d)
                numbers.remove(d)
            }
            data[d] == "empty" -> println("There is no car in spot $d.")
        }
    }
}