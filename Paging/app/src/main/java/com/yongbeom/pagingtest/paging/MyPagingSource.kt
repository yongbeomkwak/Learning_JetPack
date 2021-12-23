package com.yongbeom.pagingtest.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yongbeom.pagingtest.api.SimpleApi
import com.yongbeom.pagingtest.model.Post
import retrofit2.HttpException
import java.io.IOException



class MyPagingSource(
    /*
simpleApi : 데이터를 제공하는 인스턴스
userId : 쿼리를 위한 값
 */
    private val simpleApi : SimpleApi,
    private val userId : Int
) :PagingSource<Int, Post>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try{
            // 키 값이 없을 경우 기본값을 0로 사용함
            val now = params.key ?: 1

            // 데이터를 제공하는 인스턴스의 메소드 사용
            val response = simpleApi.getCustomPost2(
                userId = userId,
                sort = "id",
                order = "asc"
            )
            val post = response?.body()

            /* 로드에 성공 시 LoadResult.Page 반환
            data : 전송되는 데이터
            prevKey : 이전 값 (위 스크롤 방향)
            nextKey : 다음 값 (아래 스크롤 방향)
             */
            Log.e("now",now.toString())
            LoadResult.Page(

                data = post!!,
                prevKey = if (now ==1) null else now - 1,
                nextKey = null
            )
        }
        catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    // 데이터가 새로고침되거나 첫 로드 후 무효화되었을 때 키를 반환하여 load()로 전달
    /***
     * 스와이프 Refresh나 데이터 업데이트 등으로 현재 목록을 대체할 새 데이터를 로드할 때 사용된다.
    PagingData는 Component에서 설명한 것처럼 새로고침 될 때마다 상응하는 PagingData를 생성해야한다.
    즉, 수정이 불가능하고 새로운 인스턴스를 만들어야한다.
    가장 최근에 접근한 인덱스인 anchorPosition으로 주변 데이터를 다시 로드한다.
     */
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {

        /**
         * // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
         * */
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}