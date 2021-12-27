#   [WorkManager](https://danggai.github.io/android/Android%EC%97%90%EC%84%9C-WorkManager-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0/)

## 규격
즉시 실행해야한다 ? -> 코루틴, WorkManager

정확한 시간에 실행해야한다? -> AlaramManager

지연된 작업(Deferred)으로 실행해야한다? -> WorkManager

## 과정
WorkManager 클래스 생성 -> WorkManager Request 생성 -> Equeue Request (워크매니저 실행 요청) -> 상태 업데이트 얻기(작업 대기 및 수신)


##  순서 
-   1.   WorkManager 클래스를 만드려면은Worker 클래스를 상속받고 context와 WorkerParameters를 필요로합니다. 
    -   WorkerParamenteres는 말 그대로 인텐트 getExtras() 처럼 데이터를 전달받는데 사용됩니다.  그리고 doWork() 메서드를 오버라이딩 해야합니다.이것은 WorkManager에서 제공하는 백그라운드 스레드에서 동기식으로 실행됩니다.즉 WorkManger의 백그라운드 작업을 만들려면 Worker 클래스를 상속받고 doWork() 메서드를 재정의가 필요합니다.

