package com.marcelorbenites.conference

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConferencesController {

    @CrossOrigin(origins = ["*"])
    @RequestMapping("/conferences")
    fun conferences() = listOf(Conference("1", "Droidcon"))
}