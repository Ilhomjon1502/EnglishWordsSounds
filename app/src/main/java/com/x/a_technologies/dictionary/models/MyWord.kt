package com.x.a_technologies.dictionary.models

data class MyWord(
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>,
    val word: String
)