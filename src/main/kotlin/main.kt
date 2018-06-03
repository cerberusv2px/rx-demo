import data.Data
import entity.User
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    zipOperator()
    mergeOperator()
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
            Data.getUserData1(),
            Data.getUserData2(),
            Data.getUserData3(),
            Function3<List<User>, List<User>, List<User>, List<User>> { t1, t2, t3 ->
                return@Function3 t1.union(t2).union(t3).toList()
            }).subscribe({
                println(it)
    })
}

fun mergeOperator() {
    Observable.merge(Data.getUserData1(), Data.getUserData2())
            .subscribe {
                println("List:${it.size}")
            }
}

