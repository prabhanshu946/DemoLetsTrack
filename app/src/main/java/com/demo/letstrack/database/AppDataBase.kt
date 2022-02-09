package com.demo.letstrack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.letstrack.database.dao.QuestionDao
import com.demo.letstrack.database.dao.QuestionOwnerDao
import com.demo.letstrack.database.dao.TagsDao
import com.demo.letstrack.database.entity.Question
import com.demo.letstrack.database.entity.QuestionOwner
import com.demo.letstrack.database.entity.Tags

@Database(
    entities = [Question::class, QuestionOwner::class, Tags::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract val questionDao: QuestionDao
    abstract val questionOwnerDao: QuestionOwnerDao
    abstract val tagsDao: TagsDao

    companion object {
        private const val DB_NAME = "letsTrack.db"

        @Volatile
        var instance: AppDataBase? = null

        @Synchronized
        internal fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()//in case you don't want to clear all table in version upgrade
                .addMigrations(MIGRATION)
                .build()
        }
    }

    object MIGRATION : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {}
    }
}