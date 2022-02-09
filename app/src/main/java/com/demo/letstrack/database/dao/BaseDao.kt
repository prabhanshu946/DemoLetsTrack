package com.demo.letstrack.database.dao

import androidx.room.*
@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(type: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(repoList: List<T>) :List<Long>


}