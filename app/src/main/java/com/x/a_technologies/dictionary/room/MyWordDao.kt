package com.x.a_technologies.dictionary.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyWordDao {

    @Insert
    fun addWord(myWordDb: MyWordDb)

    @Query("select * from myworddb")
    fun getAllMyWordDb():List<MyWordDb>

    @Update
    fun editWord(wordDb: MyWordDb)
}