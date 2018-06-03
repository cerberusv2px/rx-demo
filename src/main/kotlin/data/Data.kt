package data

import entity.Repo
import entity.User
import io.reactivex.Observable

class Data {
    companion object {
        fun getUserData1(): Observable<List<User>> {
            return Observable.just(listOf(
                    User(1, "Aasdf", listOf(Repo(1, "Test"), Repo(2, "Test2"))),
                    User(2,"Nigachiga", emptyList())
            ))
        }


        fun getUserData2(): Observable<List<User>> {
            return Observable.just(listOf(
                    User(2, "Dfsfa", listOf(Repo(1, "TestSaf"), Repo(2, "TestSfsf")))
            ))
        }

        fun getUserData3(): Observable<List<User>> {
            return Observable.just(listOf(
                    User(3, "Dfsfaaaa", listOf(Repo(1, "TeaastSaf")))
            ))
        }
    }
}