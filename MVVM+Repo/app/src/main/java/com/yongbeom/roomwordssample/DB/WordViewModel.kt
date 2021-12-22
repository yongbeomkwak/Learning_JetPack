package com.yongbeom.roomwordssample.DB

import androidx.lifecycle.*
import com.yongbeom.roomwordssample.DB.Entity.Word
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository):ViewModel() {
    //장소의 allWords 흐름을 사용하여 LiveData를 초기화했습니다.
    // 그런 다음 asLiveData().를 호출하여 Flow를 LiveData로 변환했습니다

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()



    /**
     * 저장소의 insert() 메서드를 호출하는 래퍼 insert() 메서드를 만들었습니다. 이렇게 하면 insert() 구현이 UI에서 캡슐화됩니다.
     * 새 코루틴을 실행하고 정지 함수인 저장소의 insert를 호출합니다.
     * 앞서 언급한 바와 같이 ViewModel에는 viewModelScope이라는 수명 주기 기반의 코루틴 범위가 있으며 여기서 사용합니다.
     * */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
    /**
     * ViewModel을 만들고 WordViewModel을 만드는 데 필요한 종속 항목(WordRepository)을 매개변수로 가져오는
     * ViewModelProvider.Factory를 구현했습니다.
    viewModels와 ViewModelProvider.Factory를 사용하여 프레임워크에서
    ViewModel의 수명 주기를 처리합니다.
    구성 변경에도 유지되고 Activity가 다시 생성되더라도 항상 WordViewModel 클래스의 올바른 인스턴스를 가져오게 됩니다.


     * */
    class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}