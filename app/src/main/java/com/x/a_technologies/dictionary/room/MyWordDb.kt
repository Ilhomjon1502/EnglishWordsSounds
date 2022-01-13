package com.x.a_technologies.dictionary.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyWordDb{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var word:String? = null
    var definition:String? = null
    var example:String? = null
    var audioLink:String? = null
    var save:Boolean = false

    constructor(
        word: String?,
        definition: String?,
        example: String?,
        audioLink: String?,
        save: Boolean
    ) {
        this.word = word
        this.definition = definition
        this.example = example
        this.audioLink = audioLink
        this.save = save
    }
}