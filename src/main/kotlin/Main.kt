/**@author Max Henning Junghans */
/**
 * This is the entry point of the application.
 * It loads a blockchain from a given path.
 */
fun main(args: Array<String>) {
	if (args.isNotEmpty()) {
		val blockchain = Blockchain(args[0])
		run(blockchain)
	} else {
		println("To use the program, please provide the path to the Blockchain as a CLI argument.")
	}
}

/**
 * This method reads the input of the user and acts on that input.
 * @param blockchain The Blockchain on which the operations should be executed.
 */
fun run(blockchain: Blockchain) {
	var finished = false
	println("Type on of the following options: !print, !add, !validate, !fin.")
	while (!finished) {
		when (readln()) {
			"!print" -> blockchain.printBlockchain()
			"!add" -> blockchain.addBlock(askUserForData())
			"!add2" -> blockchain.addBlock(askUserForDataWithLinebreaks())
			"!validate" -> {
				if (blockchain.isValid()) {
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

/**
 * This method prompts the User to input the data of a block.
 * @return The input of the User.
 */
fun askUserForData(): String {
	println("Write the data you would like to add.")
	return readln()
}

/**
 * This method prompts the User to input the data of a block.
 * It is an alternative to askUserForData().
 * Here the user can input multiple lines of data.
 * @return The input of the User.
 */
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