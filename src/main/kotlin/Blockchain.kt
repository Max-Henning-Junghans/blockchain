import java.io.File
import java.security.MessageDigest

/**
 * This class represents a blockchain.
 * @param path This is the path to the blockchain on the disk. Tested with absolute paths on Windows.
 * @author Max Henning Junghans
 */
class Blockchain(private val path: String) {
	private var listOfBlocks: ArrayList<Block> = ArrayList()
	init {
		val files = File(path).listFiles()
		if (files != null) {
			if (files.isNotEmpty()) {
				files.forEach { file ->
					if (!file.isDirectory) {
						var fileData = ""
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

	/**
	 * This method adds a block to the blockchain.
	 * The method calculates the block id and the previous hash itself.
	 * @param data The data part of the block.
	 */
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

	/**
	 * This method checks, if the rules for are followed.
	 * @return If the rules are followed, return true, else false.
	 */
	fun isValid(): Boolean {
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

	/**
	 * This method converts an integer into a String and ads a padding 0 in front so that the String is two digits long.
	 * @param number The integer that should be converted.
	 * @return The converted integer.
	 */
	private fun convertIntToTwoDigitString(number: Int): String {
		return if (number < 10) {
			"0$number"
		} else {
			number.toString()
		}
	}

	/**
	 * This method checks if a block-id String is identical to its number.
	 * @param blockNumber The id that should be checked.
	 * @param numberToCompare The integer it should be compared with.
	 * @return If the number is correct, true is returned. Else false.
	 */
	private fun isBlockNumberCorrect(blockNumber: String, numberToCompare: Int): Boolean {
		return blockNumber == convertIntToTwoDigitString(numberToCompare)
	}

	/**
	 * This method was taken from:
	 * https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25
	 * It uses SHA-256 to hash a String.
	 * @param input The String that should be hashed.
	 * @return The hashed String.
	 */
	private fun hash(input: String): String {
		val bytes = input.toByteArray()
		val md = MessageDigest.getInstance("SHA-256")
		val digest = md.digest(bytes)
		return digest.fold("") { str, it -> str + "%02x".format(it) }
	}
}