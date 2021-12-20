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

## [선언](https://readystory.tistory.com/176)

## 종류
-  일반적인 ViewModel
-  Android ViewModel
-  차이: 큰 차이는 없지만 AndroidViewModel은 context나 Activity 객체를 사용할 수 있다
-  단, 기본적으로 일반적인 ViewModel을 지향하며, Android ViewModel은 메모리 누수를 일으킬 수 있다.
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

# [Room](https://todaycode.tistory.com/39)
- Room은 스마트폰 내장 DB에 데이터를 저장하기 위해 사용하는 라이브러리이다.
- Room은 완전히 새로운 개념은 아니고, SQLite를 활용해서 객체 매핑을 해주는 역할을 한다.
아무튼 이러한 이유들로 구글에서는 SQLite 대신 Room을 사용할 것을 권장하고 있다.

## 구성
- Room Database, 
- Data Access Objects
- Entities
  > 한국말로 하면 '개체'인 Entity는 관련이 있는 속성들이 모여 하나의 정보 단위를 이룬 것이다.
  >   예를 들어 위와 같이 사람의 이름, 나이, 번호라는 속성이 모여서 하나의 정보 단위를 이루면 이것을 Entity라고 한다.

## 세팅
-   build.gradle(module)
    -   plugIn 설정
            
            plugins {id 'kotlin-kapt'}
    
    -   dependency
            
            def room_version = "2.4.0" // check latest version from docs

            implementation "androidx.room:room-ktx:$room_version"
            
            kapt "androidx.room:room-compiler:$room_version"

##  선언
### 1.  Entity선언 
    
    -   dataClass를 생성한 후 
        
            @Entity(tableName="테이블명") 설정
    -   이후 primaryKey 설정 
            
            @PrimaryKey(autoGenerate = true) var id: Int = 0

### 2.DAO(DataAccessObject)
-   인터페이스와 @Dao 어노테이션 사용
-   사용 어노테이션 
    -   @Insert: 테이블에 데이터 삽입
    -   @Update:테이블의 데이터 수정
    -   @Delete:테이블의 데이터 삭제
    -   @Query("해당 sql 문법"): 삽입/수정/삭제 외에 다른 기능

### 3.DataBase
-   추상클래스 이용 ,RoomDatabase() 상속
-   @Database 어노테이션으로 데이터베이스임을 표시한다.

        @Database(entities = [User::class], version = 1)
> 어노테이션 괄호 안을 보면 entities가 있는데 여기에 에서 만든 entity를 넣어주면 된다.
version은 앱을 업데이트하다가 entity의 구조를 변경해야 하는 일이 생겼을 때 이전 구조와 현재 구조를 구분해주는 역할을 한다. 만약 구조가 바뀌었는데 버전이 같다면 에러가 뜨며 디버깅이 되지 않는다.
처음 데이터베이스를 생성하는 상황이라면 그냥 1을 넣어주면 된다.

-    데이터베이스 객체를 인스턴스 할 때 [싱글톤](https://tecoble.techcourse.co.kr/post/2020-11-07-singleton/)으로 구현하기를 권장하고 있다.
-    또한 @Synchronized 와 비동기 [Coroutine](https://wooooooak.github.io/kotlin/2019/08/25/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%BD%94%EB%A3%A8%ED%8B%B4-%EA%B0%9C%EB%85%90-%EC%9D%B5%ED%9E%88%EA%B8%B0/)을 사용한다




# Extra
-   build.gradle(module) 에서 변수 사용시 def 이용

        def room_version = "2.3.0"

- build.gradle(project) 에서 변수 선언시 ext{}에 넣어주고 
- build.gradle(module)쪽에서 사용시 ${}로 불러온다.


        ext{
            room_version="2.4.0"
        }


        kapt "androidx.room:room-compiler:${room_version}"


### [MVVM 패턴](https://aonee.tistory.com/48)
### [Repository Pattern](https://developer88.tistory.com/210)