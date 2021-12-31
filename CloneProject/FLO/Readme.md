#   FLO

## 새로 배운 것
-   drawable 에서 layer-list 와 item을 이용하여 image 리사이즈
-   SeekBar 색 설정하기(theme 속성) ,Switch 테마 ,style.xml 참고
-   main_activity.xml
    -   FragmentContainerView의 name 속성은 
        -   "androidx.navigation.fragment.NavHostFragment" 으로 한다
- MainActivity.kt
  - MediaPlayer   
    - [볼륨조절](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ksseo63&logNo=120133161294)
  - SeekBar 컨트롤(Thread 이용)

-   MainFragment.kt
    -   Fragment에서 Activity 접근 후 observe

-   LyricsViewModel.kt
    -   다양한 자료형 및 Regex 정규표현식 사용,split까지

-   LyricsFragment.kt
    -   [Switch](https://lktprogrammer.tistory.com/181)
    -   Fragment는 context=requireContext()
    -   ReyclerView 아이템간 간격 RecyclerDecoration 이용

-   LyricsAdapter.kt
    -   RecylcerView 관련 구성
    -   데이터 섞임 방지 코드 getItemViewType 
    -   밑에 짤림 현상은 LyricsFragment.xml에서 paddingBottom을 줌 