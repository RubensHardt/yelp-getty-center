package com.rubenshardt.yelpgettycenter.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rubenshardt.yelpgettycenter.model.business.Business

@Dao
interface BusinessDao {

    @Query("SELECT * FROM business where id =:id LIMIT 1")
    fun getBusiness(id: String): LiveData<Business>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusiness(business: Business)
}