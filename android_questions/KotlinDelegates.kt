package android_questions

import kotlin.reflect.KProperty

// Define actions of an animal
interface AnimalAction {
    fun eat()
    fun sleep()
    fun walk()
}

// Base class of an animal
class Animal(val name: String) : AnimalAction {
    override fun eat() {
        println("$name can eat.")
    }

    override fun sleep() {
        println("$name needs sleep.")
    }

    override fun walk() {
        println("$name can walk.")
    }
}

/**
 * ## Class Delegates ##
 * Now if we create a new animal if we use normal inheritance we will need to make animal class open.
 * So instead to keep it simple we can use power of delegates to utilise the features of Animal class.
 * This will also help us to maintain O(Open/close) principle of SOLID principle's.
 * */
class Dog(val dogName: String) : AnimalAction by Animal(dogName)

/**
 * ## Property Delegation ##
 * This is a Custom delegate class that can help to add isNotEmpty() & isLetter() functionality to string variables.
 * Now if we use this delegate class with some string it will get the functionality written in the delegate class.
 * */
class NameDelegate {

    private lateinit var name: String

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return name
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        if (value.isNotEmpty() && value.all { it.isLetter() }) {
            name = value.uppercase()
        } else {
            throw IllegalArgumentException("Name must not be empty and should only contains letter")
        }
    }
}

// Another example of how lazy property works using delegates.
class VeryLazy<out T:Any>(
    private val init:()->T
){
    private var currValue:T? = null

    operator fun getValue(thisRef: Any?,property: KProperty<*>):T{
        return if (currValue==null){
            currValue=init()
            currValue!!
        }else{
            currValue!!
        }
    }
}


fun main() {
    val dog = Dog("Bruno")
    dog.walk()
    dog.sleep()
    dog.eat()

    var myName: String by NameDelegate()
    myName = "somethingwentwrong"

    println(myName)

    val someValue by VeryLazy{
        9.99
    }

    println(someValue)

}