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

#   ViewModel
## 역할
-   UI 관련 데이터를 저장하고 관리해주는 역할

##  세팅
-   ViewModel과 LiveData [lifecycle 라이브러리](https://developer.android.com/jetpack/androidx/releases/lifecycle?hl=ko#declaring_dependencies)를 implementation