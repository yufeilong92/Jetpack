package com.example.myapplication

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.properties.ReadOnlyProperty

object DataStoreUtils {

    private val str_Data = preferencesKey<String>("str")

    private val SP_NAME = "sp_name"


    private var mDataStore: DataStore<Preferences>? = null
    suspend fun writaDataStore(mContext: Context, msg: String) {
        if (null == mDataStore) {
            mDataStore = mContext.createDataStore(name = SP_NAME)
        }
        mDataStore?.edit {
            it[str_Data] = msg
        }
    }

    fun readDataStore(mContext: Context): Flow<String> {
        if (null == mDataStore) {
            mDataStore = mContext.createDataStore(name = SP_NAME)
        }
        val map = mDataStore!!.data.map {
            it[str_Data] ?: ""
        }
        return map
    }



}