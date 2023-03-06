class Block(val block: String, val previousHash: String, private val data: String, val hash: String) {

	fun printBlock() {
		println(toString())
		println("Hash of the block: $hash")
	}

	fun validateBlock(): Boolean {
		return true
	}

	override fun toString(): String {
		return "block=$block" +
				System.lineSeparator() +
				"previous_hash=$previousHash" +
				System.lineSeparator() +
				"data=$data"
	}
}