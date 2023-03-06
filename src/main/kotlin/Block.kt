/**
 * This class represents a block of a blockchain.
 * @param block This is the id of the block. It should be a two digit integer as a String.
 * @param previousHash This is the hash of the previous block in the blockchain.
 * @param data This is the data of the block. It can be arbitrary.
 * @param hash This is the hash of the block. It only gets used for validation purposes.
 * @author Max Henning Junghans
 */
class Block(val block: String, val previousHash: String, private val data: String, val hash: String) {

	/**
	 * This method prints the block and adds the hash of the block at the end.
	 */
	fun printBlock() {
		println(toString())
		println("Hash of the block: $hash")
	}

	/**
	 * Converts the block to a String, while not including the hash.
	 * It follows the syntax of a block in the blockchain.
	 * @return The block as a String.
	 */
	override fun toString(): String {
		return "block=$block" +
				System.lineSeparator() +
				"previous_hash=$previousHash" +
				System.lineSeparator() +
				"data=$data"
	}
}