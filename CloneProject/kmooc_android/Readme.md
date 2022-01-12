#   [KMOOC](https://whyprogrammer.tistory.com/626?category=858796)

##  1.  KmoocRepository
-   JsonParse에 관하여 
    -   각종 get 함수
        -    getString(name)
        -    getJSONObject(name)
        -    getInt(name)
        -    getJSONArray(name)
   -  has(name):해당 name에 대항 속성 존재하냐?
   -  DateUtil.parseDate(getString("start")):해당 문자열을 Date타입으로 

   -    Lambda 함수를 이용한 익명함수 

##  2-1  KmoocListActivity
-   무한스크롤 구현 OnScrolled 과 layoutManager이용 
-   Pull to Refreshing

## 2-2  activity_kmook_list.xml
-   androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    -   Refresh에 사용하는 Layout

## 2-3 KmoocListViewModel.kt
-   ViewModel 및 ViewModelProvider를 이용한 팩토리 클래스 생성 
-   .isAssignableFrom(targetClass::class.java) 해당 targetClass와 같은 놈인지 확인

##  3.ImageLoader
-   코루틴과 withContext를 사용하여 비동기 작업

##  4.KmoocDetailActivity
-   WebView 다루는 법
-   Observer 시 간단히 하는 방법 
    -   viewModel.lecture.observe(this,this::setDetailInfo)

##  5.ViewUtil
-   기본 타입의 확장함수 ,Boolean.xx 하면 Boolean 타입 모두 다 해당 함수 접근 가능