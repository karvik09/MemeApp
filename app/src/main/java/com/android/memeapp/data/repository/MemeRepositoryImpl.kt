package com.android.memeapp.data.repository

import com.android.memeapp.constant.AppConstant
import com.android.memeapp.data.network.*
import com.android.memeapp.data.repository.source.db.IDatabaseSource
import com.android.memeapp.data.repository.source.network.INetworkSource
import com.android.memeapp.db.entity.MemeObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val dbSource: IDatabaseSource,
    private val networkSource: INetworkSource
) : IMemeRepository {

    override suspend fun fetchFromRemote():
            Resource<ArrayList<MemeObject>> = withContext(Dispatchers.IO) {
        try {
            val memeList: ArrayList<MemeObject>?
            val response = networkSource.fetchMeme()
            memeList = response.data?.memes
            if (memeList?.isNotEmpty() == true) {
                dbSource.saveMeme(memeList)
                return@withContext fetchFromDB(AppConstant.PAGE_SIZE,0)
            } else {
                return@withContext Success<ArrayList<MemeObject>>(body = null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val localData = dbSource.fetchMeme(AppConstant.PAGE_SIZE, 0)
            if (localData.isNotEmpty()) {
                return@withContext Success(body = localData)
            } else {
                return@withContext Failure()
            }
        }
    }

    override suspend fun fetchFromDB(pageSize: Int, pageOffset: Int)
            : Resource<ArrayList<MemeObject>> = withContext(Dispatchers.IO) {
        val memeList = dbSource.fetchMeme(pageSize, pageOffset)
        Success(body = memeList)
    }

}