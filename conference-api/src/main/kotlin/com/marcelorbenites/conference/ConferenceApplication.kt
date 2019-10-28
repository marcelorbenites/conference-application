package com.marcelorbenites.conference

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConferenceApplication

fun main(args: Array<String>) {
	runApplication<ConferenceApplication>(*args)
}
