fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val blockchain = Blockchain(args[0])
    blockchain.printBlockchain()
    val test = askUserForData()
}

fun askUserForData(): String {
    println("Write the data you would like to add.")
    return readln()
}

fun askUserForDataWithLinebreaks(): String{
    println("Write the data you would like to add. If you are done, write !end")
    var data = ""
    while (true) {
        val input = readln()
        if (input == "!end") {
            break
        }
        data += input + System.lineSeparator()
    }

    data = data.substringBefore(System.lineSeparator())
    return data
}