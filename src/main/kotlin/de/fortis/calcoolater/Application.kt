package de.fortis.calcoolater;

import mu.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import java.util.TimeZone
import javax.annotation.PostConstruct

private val log = KotlinLogging.logger {}

@SpringBootApplication
class Application : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(Application::class.java)
    }

    @PostConstruct
    internal fun started() {
        log.info("Timezone: Europe/Berlin")
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
