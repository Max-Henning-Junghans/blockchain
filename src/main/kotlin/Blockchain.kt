import java.io.File
import java.security.MessageDigest

class Blockchain(private val path: String) {
	private var listOfBlocks: ArrayList<Block> = ArrayList()
	init {
		val files = File(path).listFiles()
		if (files != null) {
			if (files.isNotEmpty()) {
				files.forEach { file ->
					if (!file.isDirectory) {
						var fileData = ""
						println(file.name)
						println(file.absolutePath)
						file.forEachLine {
							fileData += it + System.lineSeparator()
						}
						val fileDataLines = fileData.lines()
						val name: String = fileDataLines[0].substringAfter("block=")
						val previousHash: String = fileDataLines[1].substringAfter("previous_hash=")
						val data: String =
							fileData.substringAfter("data=")
						listOfBlocks.add(Block(name, previousHash, data, hash(file.readText())))
					}
				}
			}
		}
	}

	fun addBlock(data: String) {
		val blockNumber: String
		val previousHash: String
		if (listOfBlocks.isEmpty()) {
			blockNumber = "00"
			previousHash = "null"
		} else {
			blockNumber= convertIntToTwoDigitString(listOfBlocks.size)
			previousHash = listOfBlocks[listOfBlocks.lastIndex].hash
		}
		val hash = hash("block=$blockNumber" +
				System.lineSeparator() +
				"previous_hash=$previousHash" +
				System.lineSeparator() +
				"data=$data")
		val block = Block(blockNumber, previousHash, data, hash)
		val fileData: String = block.toString()
		listOfBlocks.add(block)
		File(path + "\\" + blockNumber).printWriter().use { out ->
			fileData.forEach {
				out.print(it)
			}
		}
	}

	fun validateBlockchain(): Boolean {
		if(listOfBlocks[0].block != "00" || listOfBlocks[0].previousHash != "null") {
			return false
		}
		for (i in 1..listOfBlocks.lastIndex) {
			if (!isBlockNumberCorrect(listOfBlocks[i].block, i) || listOfBlocks[i].previousHash != listOfBlocks[i-1].hash) {
				println("Hier: " + !isBlockNumberCorrect(listOfBlocks[i].block, i))
				return false
			}
		}
		return true
	}

	fun printBlockchain() {
		listOfBlocks.forEach{block -> block.printBlock()}
	}

	private fun convertIntToTwoDigitString(number: Int): String {
		return if (number < 10) {
			"0$number"
		} else {
			number.toString()
		}
	}

	private fun isBlockNumberCorrect(blockNumber: String, numberToCompare: Int): Boolean {
		return blockNumber == convertIntToTwoDigitString(numberToCompare)
	}

	/**
	 * https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25
	 */
	private fun hash(input: String): String {
		val bytes = input.toByteArray()
		val md = MessageDigest.getInstance("SHA-256")
		val digest = md.digest(bytes)
		return digest.fold("") { str, it -> str + "%02x".format(it) }
	}
}