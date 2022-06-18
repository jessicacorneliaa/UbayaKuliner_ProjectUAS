package com.ubaya.ubayakuliner160419035.model

import androidx.room.*

@Dao
interface UbayaKulinerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTenants(vararg tenant:Tenant)

    @Query("SELECT * FROM tenants")
    suspend fun selectAllTenants(): List<Tenant>

    @Query("SELECT * FROM tenants where id= :id")
    suspend fun selectTenant(id:String): Tenant

    @Delete
    suspend fun deleteTenants(tenant: Tenant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(vararg review: Review)

    @Query("SELECT * FROM reviews where tenantId= :tenantId")
    suspend fun selectReviewsTenant(tenantId:String): List<Review>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReserve(vararg reservation: Reservation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPromo(vararg promo: Promo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAccount(vararg accounts: Account)

    @Query("SELECT * FROM account WHERE idAccount=:id")
    suspend fun selectAccount(id:String): Account

    @Query("SELECT * FROM promo")
    suspend fun selectAllPromo():List<Promo>

    @Query("SELECT * FROM promo WHERE idPromo=:id")
    suspend fun selectPromo(id:String):Promo

    @Query("SELECT * FROM reservation")
    suspend fun selectReservation():List<Reservation>




}