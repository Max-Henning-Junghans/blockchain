fun main(args: Array<String>) {
	val blockchain = Blockchain(args[0])
	blockchain.printBlockchain()
	val userInput = askUserForData()
	blockchain.addBlock(userInput)
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