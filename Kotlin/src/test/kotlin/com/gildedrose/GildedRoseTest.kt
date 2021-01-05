package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter

internal class GildedRoseTest {

    @Test
    fun goldenMasterTest() {
        val fileReader: BufferedReader?
        var line: String?
        val items = ArrayList<Item>()
        val fileBasePath = "src/test/kotlin/com/gildedrose/"
        val input = fileBasePath + "fixture.csv"
        fileReader = BufferedReader(FileReader(input))
        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(";")
            if (tokens.size > 0) {
                val item = Item(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2]))
                items.add(item)
            }

            line = fileReader.readLine()
        }

        val app = GildedRose(items.toTypedArray())
        app.updateQuality()
        val fileWriter: FileWriter?
        val actual = fileBasePath + "result.csv"
        fileWriter = FileWriter(actual)
        for (item in app.items) {
            fileWriter.append(item.name)
            fileWriter.append(',')
            fileWriter.append(item.sellIn.toString())
            fileWriter.append(',')
            fileWriter.append(item.quality.toString())
            fileWriter.append('\n')
        }
        fileWriter.flush()
        fileWriter.close()

        val expected = fileBasePath + "expected_result.csv"
        val expectedContent = getFileContent(expected)
        val actualContent = getFileContent(actual)
        assertEquals(expectedContent, actualContent)
    }

    private fun getFileContent(
        filePath: String
    ): List<String> {
        val fileReader: BufferedReader?
        fileReader = BufferedReader(FileReader(filePath))
        return fileReader.readLines()
    }

}


