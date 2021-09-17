package com.example.hwproject.lesson_12.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {

    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun findUserById(userId: Int): Flow<User>

    @Insert
    fun addUser(user: User)

    @Delete
    fun removeUser(user: User)

    @Query("DELETE FROM users where firstName = :firstName AND secondName =:lastName")
    fun deleteByNameAndLastName(firstName: String, lastName: String)
}