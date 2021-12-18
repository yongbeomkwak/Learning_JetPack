# [LiveData](https://dev-imaec.tistory.com/39)
-   LiveData는 RxJava와 같은 observable 형태로 사용하며, 안드로이드 Lifecycle에 따라 데이터를 관리한다.
-   LiveData는 observable 패턴을 사용하기에 데이터의 변화를 구독한 곳으로 통지하고, 업데이트한다.
-   메모리 누수 없는 사용을 보장한다.
-   Lifecycle에 따라 LiveData의 이벤트를 제어한다.
-   항상 최신 데이터를 유지한다.
-   기기 회전이 일어나도 최신 데이터를 처리할 수 있도록 도와준다.(AAC-ViewModel과 함께 활용 시)
-   LiveData의 확장도 지원한다.

##  [종류](https://thdev.tech/android/2021/02/01/LiveData-Intro/)
-   MutableLiveData : 값의 get/set 모두를 할 수 있다.
-   LiveData : 값의 get()만을 할 수 있다.

## 선언
- MutableLiveData 생성 시 타입과 생성자를 호출하자   

        MutableLiveData:MutableLiveData<Type>=MutableLiveData()

#   [ViewModel](https://todaycode.tistory.com/33)
## 역할
-   UI 관련 데이터를 저장하고 관리해주는 역할

##  세팅
-   ViewModel과 LiveData [lifecycle 라이브러리](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko#declaring_dependencies)를 implementation

## 정의
-   CustomViewModel 클래스를 만든다. 단 ViewModel 클래스를 상속해야하 한다
-   뷰 모델을 각각 다른 소유자가 생성하면 이는 별개의 메모리 공간을 사용하는 다른 객체가 된다.
-   하나의 액티비티를 소유자로 지정해 사용하면 같은 ViewModel을 공유할 수 있다. = 데이터 공유 가능





## 주의
###     뷰 모델은 Activity, Fragment, Context를 참조하면 안 된다.
 
 뷰 모델은 액티비티나 프래그먼트보다 긴 생명주기를 가지고 있다.

만약 뷰 모델이 액티비티에 대한 참조를 가지고 있다고 했을 때

화면을 가로로 했다가 세로로 했다가 가로로 했다가 세로로 했다가.... x 100번을 했다고 해보자.

액티비티는 종료와 생성을 반복하겠지만 뷰 모델은 쭉 살아있기 때문에 이미 종료되어 사라진 액티비티의 참조를 그만큼 가지고 있을 것이다. 쓸데없는 것이 메모리를 차지하고 있는 현상, 즉 Memory Leak이 발생하기 때문에 참조를 하면 안 된다는 것이다.

 

단, applicationContext는 액티비티의 생명주기가 아닌 애플리케이션의 생명주기를 가지기 때문에 참조를 해도 괜찮다. 이 경우는 뷰 모델을 만들 때 ViewModel을 상속받는 것이 아니라 **AndroidViewModel**을 상속받으면 된다.