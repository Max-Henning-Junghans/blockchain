class Block(private val name: Int, private val previousHash: String, private val data: String) {

    fun printBlock() {
        println("name=$name" +
        System.lineSeparator() +
        "previous_hash=$previousHash" +
        System.lineSeparator() +
        "data=$data")
    }

}