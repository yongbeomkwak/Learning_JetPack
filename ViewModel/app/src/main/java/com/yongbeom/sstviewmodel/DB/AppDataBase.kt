package com.yongbeom.sstviewmodel.DB

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yongbeom.sstviewmodel.DB.DAO.TodoDao
import com.yongbeom.sstviewmodel.DB.Entities.Todo


@Database(entities = [Todo::class],version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract  fun toDoDao():TodoDao
    companion object {
        private var instance: AppDataBase? = null //싱글톤

        /**
         * 객체를 생성할 때 databaseBuilder라는 static 메서드를 사용하는데

        context와, database 클래스 그리고 데이터 베이스를 저장할 때 사용할 데이터베이스의 이름을 정해서 넘겨주면 된다.

        다른 데이터베이스랑 이름이 겹치면 꼬여버리니 주의하자
         * */
        @Synchronized
                /**
                 *  메서드가 정의된 인스턴스의 모니터(static 메서드, class)를 통해
                 *  여러 스레드에 의해 메서드가 동시에 실행되지 않도록 보호합니다.

                 * */
        fun getInstance(context: Context): AppDataBase? {
            if (instance == null) {
                Log.e("Instance","Initial")
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "todo-database"

                    ).build()
                }
            }
            return instance
        }
    }
}