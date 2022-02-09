package com.demo.letstrack.database.dao

import androidx.room.Dao
import com.demo.letstrack.database.entity.Tags

@Dao
abstract class TagsDao : BaseDao<Tags>() {
}