package de.fortis.calcoolater.command;

import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping
class CommandController {

    val commands = listOf(Greeting(), Addition())

    @PostMapping
    fun doStuff(@RequestBody request: Request): Reply {
        log.info("processing '${request.cmd}'")

        val reply = pickReply(request.cmd)

        log.info("processed '${request.cmd}' - returning '${reply.response}'")

        return reply
    }

    fun pickReply(input: String): Reply {
        commands.forEach{
            if (it.conditionMet(input)) {
                return Reply(it.processInput(input), whoami())
            }
        }

        return Reply("Unknown input", whoami())
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
