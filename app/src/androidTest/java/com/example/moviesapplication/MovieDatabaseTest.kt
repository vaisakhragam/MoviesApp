package com.example.moviesapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.moviesapplication.data.local.db.MoviesDB
import com.example.moviesapplication.data.local.db.dao.MoviesDAO
import com.example.moviesapplication.data.local.db.entities.Movies
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDatabaseTest  {
    private lateinit var database: MoviesDB
    private var moviesDao: MoviesDAO? = null
    val movies = Movies(1001, 1.0F, 1,"test","/123.jpg",
            "1","/123.jpg","en","Title1","Title",1.0F,"lorem lipsum")
    @Before fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, MoviesDB::class.java).build()
        moviesDao = database.moviesDAO

        // Insert plants in non-alphabetical order to test that results are sorted by name
        //plantDao.insertAll(listOf(plantB, plantC, plantA))
    }

    @After
    fun tearDown() {

    }

    @Test
    fun should_Insert_Movies_Item() {

     runBlocking {
         moviesDao?.insertMovies(movies)
         Assert.assertEquals(moviesDao?.getMoviesCount(), 1)
         Assert.assertEquals(movies.id, 1001)
     }



    }

    @Test
    fun should_Flush_All_Data(){

        runBlocking {

            moviesDao?.delete(movies)
            Assert.assertEquals(moviesDao?.getMoviesCount(), 0)
        }

    }


}