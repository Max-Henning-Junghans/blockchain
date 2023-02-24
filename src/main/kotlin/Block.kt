class Block(private val block: String, private val previousHash: String, private val data: String) {

    fun printBlock() {
        println("block=$block" +
        System.lineSeparator() +
        "previous_hash=$previousHash" +
        System.lineSeparator() +
        "data=$data")
    }

}