package kt

// 主构造函数
class Player(val name:String) {

    var id = "111"

    init {
        // 初始化代码块，作为实例化对象时的一部分
        println("$id  init---->")
    }

    companion object {
        fun classMethod() {
            println("classMethod ---> ")
        }
    }

    // 次构造函数
    constructor(gender:String, name:String) : this(name) {
        // 主、次构造函数同事存在的时候，次构造函数必须直接或者间接的委托到主构造函数
        println("constructor gender:$gender name:$name")
    }

    fun play() {
        println("$name start play")
    }
}


// inline好处：减少栈的调用；以及函数作为参数时的，实例对象带来的额外内存消耗
fun foo(body:() -> Unit) {
    println("foo called")
    ordinaryFunction(body)
}

inline fun ordinaryFunction(block: () -> Unit) {
    println("haha")
    block.invoke()
    println("hahahaha")
}

/**
 * 扩展函数：毫无副作用给原有库的类增加函数，增强类的行为
 *
 * 实际上就是一个对应 Java 中的静态函数，这个静态函数参数为接收者类型的对象，
 * 然后利用这个对象就可以访问这个类中的成员属性和方法了
 *
 * 与"成员函数"的区别：
 *
 * 扩展函数是个static 方法，而成员函数是属于实例方法
 *
    public static final float dp(int $this$dp) {
    return 1.5F * (float)$this$dp;
    }
 */
fun Int.dp() :Float = 1.5f * this

fun String?.isNotNull():Boolean = this != null

fun foo(s:String?) {
    if (s.isNotNull()) s?.length
}


fun main() {
    /*
    foo {
        println("This is input func")
    }

    Player.classMethod()
    Player("liuwb").play()
    Player("male", "liuwbbbb").play()
    */

    foo("123")

    var ans = 100.dp()
    println("扩展函数 100.dp() : $ans")

    // 作用域函数 run
    var player = Player("lwb").run {
        id = "newId"
        "$name $id"
    }
    println(player)
}