package android_questions

fun main(){
//    val result = testHigherOrderFunction(2,7){ value1,value2 ->
//        value1+value2
//    }
//    print("Result : $result")
//    val inlineResult = doSomethingInline(11,9)
//    print("Result : $inlineResult")

    displayGenericType(String::class.java,"Samriddha String")
    displayGenericTypeWithRefied(1223)
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