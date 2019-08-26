package de.fortis.calcoolater;

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class CmdController {

    @PostMapping
    fun doStuff(@RequestBody request: StringHolder): StringHolder {
        var ret = ""
        if (request.cmd.toLowerCase().startsWith("my name is")) {
            ret = "Hey " + request.cmd.substring(11)
        } else if (request.cmd.contains(" + ")) {
            val parts = request.cmd.split(" + ");
            var sum = 0
            parts.forEach {
                sum += Integer.valueOf(it)
            }
            ret = "Sum is: $sum"
        } else {
            ret = "Unknown input"
        }
        return StringHolder(ret)
    }
}

data class StringHolder(
    val cmd: String
)
