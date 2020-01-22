package com.shahin.teammanagement.data.db.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.shahin.teammanagement.User
import com.shahin.teammanagement.data.network.model.UserData


/**
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User")
     fun getAll(): List<User>

    @Insert
    fun insert(task: List<UserData>?)

    @Delete
    fun delete(task: User)

    @Update
    fun update(task: User)

}
