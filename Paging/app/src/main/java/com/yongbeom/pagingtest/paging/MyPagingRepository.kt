package com.yongbeom.pagingtest.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.yongbeom.pagingtest.api.RetrofitInstance

class MyPagingRepository {

    /**
     * Pager를 사용하여 데이터를 변환해준다.
    PagingConfig 로 PagingSource 구성 방법을 정의해주어야 하는데

    pageSize 는 미리 로드할 데이터 개수 값으로,

    만약 5로 설정했다면 PagingData는 미리 5개의 항목을 로드하려고 할 것이다.

    일반적으로 보이는 항목의 여러배로 설정해야 한다.
    maxSize 는 페이지를 삭제하기 전에 PagingData 에 로드 할 수있는 최대 항목 수를 정의한다.

    페이지를 삭제하여 메모리에 보관되는 항목 수를 제한하는 데 사용할 수 있다.

    최소 pageSize + (2 * prefetchDistance) 보다 높게 설정해야 한다.
     * */
    fun getPost(userId : Int) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            // 사용할 메소드 선언
            pagingSourceFactory = { MyPagingSource(RetrofitInstance.api,userId)}
        ).liveData
}