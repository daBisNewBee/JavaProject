import java.util.*
import kotlin.collections.HashSet

//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 826 👎 0


class P_127_WordLadder {
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        /**
         *
         * 重要！！
         * 很好的一道题，涉及知识点比较多：BFS、最短路径、图
         *
         * 广度优先遍历(双向可以了解，不作为要求掌握)
         *
         * 为什么要用到queue？
         * "无向图中两个顶点之间的最短路径的长度，可以通过广度优先遍历得到"
         * 联系"生活中找人的例子"：要找医生或者教室，先遍历找可能可能认识的朋友
         *
         * ps：
         *
         * 1. 为什么 BFS 可以找到最短路径？
         * https://www.bilibili.com/video/av416763568/
         * 反证法：
         *  如果 p1路径为有效路径:a->b-c 组成
         *  p2 为到a的最短路径，
         *  由于p2最短，step为1，因此在BFS第一层遍历a的neighbor的时候就一定已经遍历到
         *  即p2 comes first
         *  所以BFS 可以找到最短路径
         *
         * 2. 图是什么？
         * 和树可以互相转化；结点可能有多个父辈
         * 场景：图最经典的实现是找两个点之间的路径、最短路径
         *
         */


        // 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
    fun changeWordEveryOneLetter(beginWord: String, endWord: String,
                                 visited:HashSet<String>,
                                 queue:LinkedList<String>,
                                 wordSet: Set<String>):Boolean {
        var array = beginWord.toCharArray()
        for (i in array.indices) {
            var originalChar = beginWord[i]
            for (k in 'a'..'z') {
                if (k == originalChar) {
                    continue
                }
                array[i] = k
                var nextWord = String(array)
                if (wordSet.contains(nextWord)) {
                    // 找到最后一个单词了，直接返回
                    if (nextWord.equals(endWord)) {
                        return true
                    }
                    if (!visited.contains(nextWord)) {
                        // 放到已访问队列，以便后面继续遍历
                        queue.offer(nextWord)
                        visited.add(nextWord)
                    }
                }
                array[i] = originalChar
            }
        }
        return false
    }

    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if (wordList.isEmpty() || !wordList.contains(endWord)) return 0

        // 1. 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        var wordSet = HashSet<String>(wordList)
        // list查询时间是o(n)，set的是o(1)，所以要set

        wordSet.remove(beginWord)

        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        var queue = LinkedList<String>()
        queue.offer(beginWord)

        var visited = HashSet<String>()
        visited.add(beginWord)

        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        var step = 1

        while (queue.isNotEmpty()) {
            var levelSize = queue.size
            // 为什么要levelSize？是平级的两条路径
            // 比如hot后的dot、lot，dog、log
            while (levelSize-- > 0) {
                // 依次遍历当前队列中的单词
                var curWord = queue.poll()
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (changeWordEveryOneLetter(curWord, endWord, visited, queue, wordSet)) {
                    return step + 1
                }
            }
            step++
        }
        return 0
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    println(P_127_WordLadder.Solution().ladderLength("hit","cog",
            listOf("hot","dot","dog","lot","log","cog")))
}