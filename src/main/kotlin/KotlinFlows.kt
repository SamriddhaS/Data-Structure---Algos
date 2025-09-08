package android_questions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlin.math.pow

val stateFlow = MutableStateFlow(0)
val sharedFlow = MutableSharedFlow<Int>()

suspend fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        //observeSynchronous()
        //observeAsync()
        //ChannelFlow
//        flowBuilder4().collect {
//            println("Channel Flow : $it")
//        }

//        flowBuilder3().map {
//            // perform to the pow of 2 operation to each emitted element
//            (it.toDouble()).pow(2.toDouble())
//        }.collect {
//            println("Map Operator : $it")
//        }
//
//        flowBuilder3().filter {
//            // perform to the pow of 2 operation to each emitted element
//            it%2 == 0
//        }.collect {
//            println("Filter Operator : $it")
//        }
//
//        val some = flowBuilder3().reduce { previous,current ->
//            previous+current
//        }
//        println("Reduced Value $some")

//        launch {
//            flowBuilder5()
//        }
//
//        // This collect is a terminal operator so it will suspend the coroutine infinitely.
//        // This is why we need to close this coroutine thread to make sure no memory leak.
//        launch {
//            stateFlow.collect {
//                println("Collector 1 - State Flow : $it")
//            }
//        }
//
//        // StateFlow is a hot flow so when we start collecting with another collector it will not
//        // receive the values that are already produced between 0-5 sec.
//        // It will only receive those values that are produced after 5sec.
//        launch {
//            delay(5000)
//            stateFlow.collect {
//                println("Collector 2 - State Flow : $it")
//            }
//        }
//
//        // This flow will only receive the final value that was emitted. As we have given a 15sec delay all the values
//        // will be already produced before this observer starts collecting.
//        launch {
//            delay(15000)
//            stateFlow.collect {
//                println("Collector 3 - State Flow : $it")
//            }
//        }

        launch {
            flowBuilder6()
        }

        // Shared flow has no initial data so it wouldn't print unless it starts emitting.
        launch {
            sharedFlow.collect {
                println("Collector 1 - Shared Flow : $it")
            }
        }

        // This flow will not receive any value. As we have given a 15sec delay all the values
        // has already been produced before this observer starts collecting and sharedFlow doesn't hold
        // any value once it's been emitted.
        launch {
            delay(15000)
            sharedFlow
                .collect {
                println("Collector 2 - Shared Flow : $it")
            }
        }

    }
    job.join()
}

/**
 * Both the collect operation will be made synchronously like the 2nd collection will be starting
 * only after the first collection is done.
 * */
suspend fun observeSynchronous() {
    flowBuilder().map { // operator
        it * it
    }.collect { // collector
        println("Value : $it")
    }

    flowBuilder().map { // operator
        it * it
    }.collect { // collector
        println("Value 1 : $it")
    }
}

/**
 * Both the collect operations will get started in parallel as we have used async{} block
 * */
suspend fun observeAsync() {
    coroutineScope {
        val async1 = async {
            flowBuilder().map { // operator
                it * it
            }.collect { // collector
                println("Async 1 : $it")
            }
            111111
        }

        delay(10000)
        val async2 = async {
            flowBuilder().map { // operator
                it * it
            }.collect { // collector
                println("Async 2 : $it")
            }
            222222
        }

//        val value = async1.await() // suspend the coroutine until async1 is completed
//        val value1 = async2.await() // suspend the coroutine until async2 is completed
    }
}

// Flow Builder : 1
suspend fun flowBuilder(): Flow<Int> {
    return flow {
        (0..5).forEach {
            emit(it)
            delay(100)
        }
    }
}

// Flow Builder : 2
suspend fun flowBuilder2(): Flow<Int> {
    return flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
}

// Flow Builder : 3
@OptIn(FlowPreview::class)
suspend fun flowBuilder3(): Flow<Int> {
    return (1..5).asFlow()
}

// Flow Builder : 4
// We can observe multiple sources of data using channel flow.
// We can observe both the emitted values inside both the launch block using a single collect operator.
fun flowBuilder4() = channelFlow {
    launch {
        (0..20).forEach { number ->
            send(number)
            delay(1000)
        }
    }

    launch {
        (15..30).forEach { number ->
            send(number)
            delay(1000)
        }
    }

}

suspend fun flowBuilder5() {
    (1..25).forEach {
        delay(500)
        stateFlow.value = it
    }
}

suspend fun flowBuilder6() {
    (1..25).forEach {
        delay(500)
        sharedFlow.emit(it)
    }
}