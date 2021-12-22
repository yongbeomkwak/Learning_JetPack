#   [MVVM+Repo](https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#16)

## Volatile
- volatile 키워드의 경우 접근가능한 변수의 값을 cache를 통해 사용하지 않고 thread가 직접 main memory에 접근하여 읽고 씁니다.


## [ListAdapter](https://youngest-programming.tistory.com/474)

### 역할
-   요약하면 안드로이드 어댑터에서 현재 데이터 리스트와 교체될 데이터 리스트를 비교하여 무엇이 다른지(바꼈는지) 알아내는 클래스로 리사이클러뷰에서 기존 아이템리스트에 수정 혹은 변경이 있을 시 전체를 갈아치우는게 아니라 변경되야하는 데이터만 빠르게 바꿔주는 역할을 합니다.

### 특징
ListAdapter는  안드로이드 문서 그대로 번역하면 RecyclerView.Adapter 를 베이스로 한 클래스로 RecyclerView 의 

List 데이터를 표현해주며 List를 백그라운드 스레드에서 diff(차이)를 처리하는 특징이 있습니다.

