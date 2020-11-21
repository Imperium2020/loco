package com.imperium.loco

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.imperium.loco.database.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var pantryDao: PantryDao
    private lateinit var passengerDao: PassengerDao
    private lateinit var ticketDao: TicketDao
    private lateinit var trainDao: TrainDao
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        pantryDao = db.pantryDao
        passengerDao = db.passengerDao
        ticketDao = db.ticketDao
        trainDao = db.trainDao
        userDao = db.userDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun createUser() {
        val user = User(
            fullName = "Test User",
            email = "test.user@gmail.com",
            userName = "tester",
            password = "testerPass!234",
            phone = "9876543210"
        )
        userDao.createUser(user)
        val testUser: User? = userDao.get(user.id)
        assertEquals(testUser?.fullName, user.fullName)
        assertEquals(testUser?.email, user.email)
        assertEquals(testUser?.userName, user.userName)
        assertEquals(testUser?.password, user.password)
        assertEquals(testUser?.phone, user.phone)
    }
}
