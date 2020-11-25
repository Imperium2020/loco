package com.imperium.loco.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlin.concurrent.thread

@Database(
    entities = [Pantry::class, Passenger::class, Ticket::class, Train::class, User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val pantryDao: PantryDao
    abstract val passengerDao: PassengerDao
    abstract val ticketDao: TicketDao
    abstract val trainDao: TrainDao
    abstract val userDao: UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "AppUserDatabase"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        thread {
                            getInstance(context).trainDao.scheduleTrains(PREPOPULATE_DATA)
                        }
                    }
                })
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        }

        val PREPOPULATE_DATA = listOf(
            Train(id = 100,
                src = "Ernakulam", dest = "Calicut", arrival = "0800",  departure = "0805",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Ernakulam", dest = "Kannur", arrival = "1000",  departure = "1005",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Ernakulam", dest = "Chalakudy", arrival = "1300",  departure = "1315",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Thrissur", dest = "Calicut", arrival = "1500",  departure = "1510",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Thrissur", dest = "Kannur", arrival = "1600",  departure = "1605",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Thrissur", dest = "Chalakudy", arrival = "1700",  departure = "1710",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Kottayam", dest = "Calicut", arrival = "1830",  departure = "1840",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Kottayam", dest = "Kannur", arrival = "2000",  departure = "2010",
                seats = Seats(15, 25, 30)
            ),
            Train(src = "Kottayam", dest = "Chalakudy", arrival = "2200",  departure = "2215",
                seats = Seats(15, 25, 30)
            ),
        )
    }
}