package android_questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

sealed class Result(val msg: String){
    class Success(val message: String): Result(message)
    class Failure(val message: String): Result(message)
    class Loading(val message: String): Result(message)
}

suspend fun doSomething(): Result {
    delay(2000)
    return Result.Success("Ya got the result");
}

fun main() = runBlocking {
    val re = doSomething()
    println(" Output - "+re.msg)
    val result = lambdaFunction(23) { val1, val2 ->
        print("1212")
        return@lambdaFunction val1-val2
    }
    print("This is a result $result")
}

fun testHigherOrderFunction(value1:Int,value2:Int,doSomething: (Int,Int)->Int):Int{
    return doSomething(value1,value2)
}

inline fun doSomethingInline(value1: Int,value2: Int) = value1 + value2

/**
 * Either pass the type manually for a generic class.
* */
fun <T> displayGenericType(classType:Class<T>,value:T){
    print("Type  Of ${classType}")
}

/**
 * Or use refied keyword along with inline to preserve the class defination during compilation.
 * */
inline fun <reified  T> displayGenericTypeWithRefied(value:T){
    print("Type  Of ${T::class.java}")
}

fun lambdaFunction(value1:Int,lambdaFun:(Int,Int)->Int):Int{
    return value1+lambdaFun(10,10)
}