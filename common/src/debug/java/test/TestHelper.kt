package test

 import java.io.InputStreamReader

object TestHelper {
    fun readFileResource(fileName : String) : String
    {
        val inputStream = TestHelper::class.java.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream,"UTF-8")
        reader.readLines().forEach{
            builder.append(it)
        }
        return builder.toString()
    }
}