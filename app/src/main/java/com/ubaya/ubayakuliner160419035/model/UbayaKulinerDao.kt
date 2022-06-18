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






}