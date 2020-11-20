package com.imperium.loco.database

data class PantryItem(val item: Pantry, val count: Int)

data class Seats(var seat_a: Int = 0, var seat_b: Int = 0, var seat_c: Int = 0)