fun main(args: Array<String>) {
	if (args.isNotEmpty()) {
		val blockchain = Blockchain(args[0])
		run(blockchain)
	} else {
		println("To use the program, please provide the path to the Blockchain as a CLI argument.")
	}
}

fun run(blockchain: Blockchain) {
	var finished = false
	println("Type on of the following options: !print, !add, !validate, !fin.")
	while (!finished) {
		when (readln()) {
			"!print" -> blockchain.printBlockchain()
			"!add" -> blockchain.addBlock(askUserForData())
			"!add2" -> blockchain.addBlock(askUserForDataWithLinebreaks())
			"!validate" -> {
				if (blockchain.validateBlockchain()) {
					println("This blockchain is valid.")
				} else {
					println("This blockchain is not valid.")
				}
			}
			"!fin" -> finished = true
			else -> {
				println("I didnt understand your Input. Please try again.")
			}
		}
	}
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