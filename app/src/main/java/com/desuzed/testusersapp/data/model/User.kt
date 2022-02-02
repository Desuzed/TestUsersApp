package com.desuzed.testusersapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Изначально было 3 объекта User:
 * 1. Этот объект как POJO;
 * 2. UserDto для БД;
 * 3. UserRetrofitDto для работы с сетью;
 * В рамках тестового проекта было принято решение упростить и объединить User и UserDto из-за
 * большого количества мапперов и boilerplate кода
  **/
@Entity(tableName = "user_table")
class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "avatar") val avatar: String
)
