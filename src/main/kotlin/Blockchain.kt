import java.io.File

class Blockchain(path: String) {
    init {
        File(path).walk().forEach { file ->
            var fileData: String = ""
            file.forEachLine {
                fileData += it
            }
            val name: Int = fileData.substringAfter("name=").substringBefore(System.lineSeparator()).toInt()
            val previousHash: String = fileData.substringAfter("previous_hash=").substringBefore(System.lineSeparator())
            val data: String = fileData.substringAfter(System.lineSeparator()).substringAfter(System.lineSeparator())
            listOfBlocks.add(Block(name, previousHash, data))
        }
    }

    private lateinit var listOfBlocks: MutableList<Block>

    fun printBlockchain() {
        listOfBlocks.forEach{block -> block.printBlock()}
    }
}