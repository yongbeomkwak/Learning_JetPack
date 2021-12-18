#   [DataBinding](https://developer.android.com/topic/libraries/data-binding/expressions?hl=ko)

## 0.   Required
-   자바 11이상으로 해야함

## 1.   build.gradle(module) 세팅
             buildFeatures {
                    dataBinding = true
                }

##  2.  data Class를 만든다
    -   User.kt

##  3.  .xml 세팅
-   layout 태그를 최상위로
-   \<data\> 태그 안에 사용할 package를 import 또는 변수를 선언한다
-   import 시 \<import\> 태그 사용
-     <variable
            변수로 사용할 이름
            name="user"
            
            어떤 타입을 변수로 사용할 것인지
            해당 데이트 클래스 또는 타입
            type="com.yongbeom.
            sstdatabinding.User"/>

-   변수 사용
    -   사용 시 "@{변수명.해당필드}로 접근"


            android:text="@{user.firstName}"

## 4.   Activity.kt 설정(MainActivity.kt)

-   바인딩

         private lateinit var binding: ActivityMainBinding

-   DataBindingUtil 이용하여 screen 그린다
-   id 접근도 binding.xx로 접근 가능
            
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

- 실질적인 바인딩
    binding.변수명(.xml에서 variable 태그의 name값)

        binding.user=User("Hello Kotlin")