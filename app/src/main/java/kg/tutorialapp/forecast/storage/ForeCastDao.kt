package kg.tutorialapp.forecast.storage

import androidx.room.Dao
import androidx.room.Insert
import kg.tutorialapp.forecast.ForeCast

@Dao
interface ForeCastDao {

    @Insert
    fun insert(foreCast: ForeCast)
}