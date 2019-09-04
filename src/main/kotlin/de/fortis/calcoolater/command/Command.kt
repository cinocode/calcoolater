package de.fortis.calcoolater.command

fun String.dropWhitespace() = this.replace(" ", "")

interface Command {
    fun conditionMet(input: String) : Boolean
    fun processInput(input: String) : String
}

class Greeting : Command {

    override fun conditionMet(input: String) = input.toLowerCase().startsWith("my name is")

    override fun processInput(input: String) = "Hey " + input.substring(11)
}

class Addition : Command {

    override fun conditionMet(input: String) = input.contains("+")

    override fun processInput(input: String) : String {
        var sum = 0
        input.dropWhitespace().split("+").forEach {
            sum += Integer.valueOf(it)
        }
        return "Sum is: $sum"
    }
}

