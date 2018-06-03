import com.sun.org.apache.xpath.internal.functions.Function2Args
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.jws.soap.SOAPBinding

fun main(args: Array<String>) {
    zipOperator()
}

fun checkObservabeSubscribeOnSequence() {
    Observable.just("Test")
            .map {
                println("First map: ${Thread.currentThread().name}")
                return@map it
            }
            .observeOn(Schedulers.io())
            .map {
                println("Second map: ${Thread.currentThread().name}")
                return@map it
            }
            .observeOn(Schedulers.newThread())
            .subscribeOn(Schedulers.computation())
            /*.map {
                println("Third map: ${Thread.currentThread().name}")
                return@map it
            }*/
            .subscribe({
                println("Subscribe: ${Thread.currentThread().name}")
            })
}

fun zipOperator() {
    Observable.zip(
            getUserData1(),
            getUserData2(),
            getUserData3(),
            Function3<List<User>, List<User>, List<User>, Unit> { t1, t2, t3 ->
                println("List1: $t1")
                println("List2: $t2")
                println("List3: $t3")
            }).subscribe({

    })
}

fun getUserData1(): Observable<List<User>> {
    return Observable.just(listOf(
            User(1, "Aasdf", listOf(Repo(1, "Test"), Repo(2, "Test2")))
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