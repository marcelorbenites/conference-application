package com.marcelorbenites.conference

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ConferencesController {

//    @Autowired
//    lateinit var repository: ConferenceRepository

    @CrossOrigin(origins = ["*"])
    @RequestMapping("/conferences")
    fun conferences() = listOf<Conference>()
}