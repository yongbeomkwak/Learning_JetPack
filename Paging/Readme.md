#   [Paging](https://www.charlezz.com/?p=44684)
## [Example](https://genius-dev.tistory.com/entry/Android-Jetpack-Paging-3-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0?category=978660)
## [Example2](https://hanyeop.tistory.com/219)

## 0. Extra
-   [suspend fun](https://kotlinworld.com/144)
-   [shiled class](https://androidtest.tistory.com/107)
-   [Flow](https://medium.com/hongbeomi-dev/kotlin-coroutine-flow-ac07cfdca42d)
##  1.Paging Source()
-   PagingSource<Key, Value> 를 상속받는 클래스를 선언해준다.
-   Key는 데이터를 로드하는 데 사용되는 식별자(페이지번호) 이며, Value는 데이터 클래스이다.
-   load 함수와 getRefreshKey 함수를 override
 
##  2.Repository 생성
---
##  3.데이터 모델 정의하기
### -   Enum Data Type class 와 Data Model class 을 정의한다
---
## 4.   ViewModel 정의하기 And ViewModelFactory
----
## 5. PagingAdapter & LoadStateAdapter 정의

### -   PagingDataAdapter 는 기존 RecyclerView.Adapter의 구현과 동일하다.
### -   차이점은 diffUtil을 구현해줘야 하는 것뿐이다.
---

## 6. MainActivity(사용)