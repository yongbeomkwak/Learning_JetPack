#   [Paging](https://www.charlezz.com/?p=44684)

## 0. Extra
-   [suspend fun](https://kotlinworld.com/144)
-   [shiled class](https://androidtest.tistory.com/107)
-   [Flow](https://medium.com/hongbeomi-dev/kotlin-coroutine-flow-ac07cfdca42d)
##  1.Repository 생성

##  2.Paging Source()
-   추상 클래스인 PagingSource<Key : Any, Value : Any> 를 상속받아서 CustomPagingSource를 만들었다.

##  3.데이터 모델 정의하기
-   Enum Data Type class 와 Data Model class 을 정의한다

## 4.   ViewModel 정의하기

## 5. PagingAdapter
-   PagingDataAdapter 는 기존 RecyclerView.Adapter의 구현과 동일하다.
-   차이점은 diffUtil을 구현해줘야 하는 것뿐이다.

