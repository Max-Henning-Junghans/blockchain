import java.io.File

class Blockchain(path: String) {
    private var listOfBlocks: ArrayList<Block> = ArrayList()
    init {
        File(path).walk().forEach { file ->
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
                listOfBlocks.add(Block(name, previousHash, data))
            }
        }
    }

    fun printBlockchain() {
        listOfBlocks.forEach{block -> block.printBlock()}
    }
}