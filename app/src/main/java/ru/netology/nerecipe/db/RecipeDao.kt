package ru.netology.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction


@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY indexNumber DESC")
    fun getAll(): LiveData<List<RecipeWithSteps>>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insertStep(step: StepEntity)

    @Query(
        "UPDATE recipes SET " +
                "author = :author, " +
                "title = :title, " +
                "category = :category " +
                "WHERE id = :id"
    )
    fun update(
        id: Long,
        author: String,
        title: String,
        category: String
    )

    @Query(
        "UPDATE steps SET " +
                "stepText = :stepText, " +
                "picture = :picture, " +
                "idRecipe = :idRecipe " +
                "WHERE idStep = :idStep"
    )
    fun updateStep(
        idStep: Long,
        idRecipe: Long,
        stepText: String,
        picture: String
    )

    @Query(
        """
        UPDATE recipes SET
        isFavorite = CASE WHEN isFavorite THEN 0 ELSE 1 END
        WHERE id = :id
    """
    )
    fun addFavorite(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM steps WHERE idStep = :idStep")
    fun deleteStep(idStep: Long)
}