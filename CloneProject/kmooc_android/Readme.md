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


##  2-1  KmoocListActivity
-   무한스크롤 구현 OnScrolled 과 layoutManager이용 
-   Pull to Refreshing

## 2-2  activity_kmook_list.xml
-   androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    -   Refresh에 사용하는 Layout


##  3.ImageLoader
-   코루틴과 withContext를 사용하여 비동기 작업