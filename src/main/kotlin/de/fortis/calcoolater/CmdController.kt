package de.fortis.calcoolater;

import mu.KotlinLogging
import java.net.InetAddress;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping
class CmdController {

    @PostMapping
    fun doStuff(@RequestBody request: Request): Reply {
        log.info("processing '${request.cmd}'")
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

        log.info("processed '${request.cmd}' - returning '${ret}'")

        return Reply(ret, whoami())
    }

    fun whoami() = InetAddress.getLocalHost().getHostName();
}

data class Request(
    val cmd: String
)

data class Reply(
    val response: String,
    val host: String
)
