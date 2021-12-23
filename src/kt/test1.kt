package kt

// �����캯��
class Player(val name:String) {

    var id = "111"

    init {
        // ��ʼ������飬��Ϊʵ��������ʱ��һ����
        println("$id  init---->")
    }

    companion object {
        fun classMethod() {
            println("classMethod ---> ")
        }
    }

    // �ι��캯��
    constructor(gender:String, name:String) : this(name) {
        // �����ι��캯��ͬ�´��ڵ�ʱ�򣬴ι��캯������ֱ�ӻ��߼�ӵ�ί�е������캯��
        println("constructor gender:$gender name:$name")
    }

    fun play() {
        println("$name start play")
    }
}


// inline�ô�������ջ�ĵ��ã��Լ�������Ϊ����ʱ�ģ�ʵ����������Ķ����ڴ�����
fun foo(body:() -> Unit) {
    println("foo called")
    // ��֤"inline������������������ڲ���"
    ordinaryFunction {
        println("input func11")
    }
    /**
     * ��֤"��inline�������к�������ʱ�������������ڲ���"
     * "CHECKCAST kotlin/jvm/functions/Function0"
     *
     */
    normalFucWith0Argu {
        println("input func22")
    }
    /**
     *
     * "CHECKCAST kotlin/jvm/functions/Function1"
     *
     */
    normalFucWith1Argu {
        println("input func33 param:$it")
    }
}

inline fun ordinaryFunction(block: () -> Unit) {
    println("haha")
    block.invoke()
    println("hahahaha")
}

fun normalFucWith0Argu(block: () -> Unit) {
    println("normalFucWith0Argu called")
    block.invoke()
}

fun normalFucWith1Argu(block: (str:String) -> Unit) {
    println("normalFucWith1Argu called")
    block.invoke("caonima")
}

/**
 * ��չ���������޸����ø�ԭ�п�������Ӻ�������ǿ�����Ϊ
 *
 * ʵ���Ͼ���һ����Ӧ Java �еľ�̬�����������̬��������Ϊ���������͵Ķ���
 * Ȼ�������������Ϳ��Է���������еĳ�Ա���Ժͷ�����
 *
 * ��"��Ա����"������
 *
 * ��չ�����Ǹ�static ����������Ա����������ʵ������
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
    println("��չ���� 100.dp() : $ans")

    // �������� run
    var player = Player("lwb").run {
        id = "newId"
        "$name $id"
    }
    println(player)

    foo{}
}