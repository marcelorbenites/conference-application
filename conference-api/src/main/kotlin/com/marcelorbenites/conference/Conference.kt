package com.marcelorbenites.conference

//@Entity
//@Table(name = "conference")
//data class Conference(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long, @Column(name = "name") val name: String)

data class Conference(val id: Long, val name: String)