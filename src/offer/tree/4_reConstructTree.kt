package offer.tree

import offer.TreeNode
import offer.printTreeInnOrder
import offer.printTreePostOrder
import offer.printTreePreOrder


/*
*
*
* 4���ؽ�������
*
* ����ĳ��������ǰ���������������Ľ�������ؽ����ö�������
* ���������ǰ���������������Ľ���ж������ظ������֡�
* ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ������������ظ���㡣
*
* ˼·��
* 1. ����ǰ�����е�һ�����ȷ�������
  2. ���ݸ���������������е�λ�÷ָ����������������
  3. �����������������ֱ�ݹ�ʹ��ͬ���ķ��������ֽ�
*
* �ݹ�ʵ�֣�
*
*   // ������α����
    TreeNode* build(1...) {
        if (2...) return nullptr;
        TreeNode *root = new TreeNode(3...);
        root->left = build(4...); // �ݹ齨��������
        root->right = build(5...); // �ݹ齨��������
        return root;
    }
*
* */

fun reConstructBinaryTree(pre:List<Int>?, inn:List<Int>?): TreeNode? {
    if (pre == null || inn == null) return null
//    return reConstructBinaryTree2(pre, inn, 0, pre.size-1, 0, inn.size-1)
    return reConstructBinaryTree(pre, inn, 0, pre.size-1, 0, inn.size-1)

}

fun reConstructBinaryTree(pre: List<Int>, inn: List<Int>,
                          pre_begin:Int, pre_end:Int, inn_begin:Int, inn_end:Int):TreeNode? {
    //�ݹ��������
    if (pre_begin > pre_end || inn_begin > inn_end) return null

    var rootNode = TreeNode(pre[pre_begin])
    var rootValue = pre[pre_begin]

    if (pre_begin == pre_end || inn_begin == inn_end) return rootNode

    // �����������У��ҵ�root��ǰ��ľ������������ұߵľ���������
    var rootIndex = inn_begin //root�����������е�λ�� fixme:����д��pre_begin����
    while (rootIndex <= inn_end
            && inn[rootIndex] != rootValue) {
        rootIndex++
    }
    var leftCount = rootIndex - inn_begin // fixme:����д��pre_begin����
    // �ֱ𹹽���������������
    rootNode.left = reConstructBinaryTree(pre, inn,
            pre_begin+1, pre_begin+leftCount,inn_begin, rootIndex-1)
    rootNode.right = reConstructBinaryTree(pre, inn,
            pre_begin+leftCount+1, pre_end, rootIndex+1, inn_end)
    return rootNode
}

// fixme:��������
fun reConstructBinaryTree2(pre: List<Int>, inn: List<Int>,
                          pre_begin:Int, pre_end:Int, inn_begin:Int, inn_end:Int):TreeNode? {
    if (pre_begin > pre_end || inn_begin > inn_end) return null
    var rootNode = TreeNode(pre[pre_begin])
    for (index in inn_begin..inn_end) {
        if (inn[index] == pre[pre_begin]) {
            rootNode.left = reConstructBinaryTree2(pre, inn,
                    pre_begin+1,pre_begin+index,inn_begin,index-1)
            rootNode.right = reConstructBinaryTree2(pre, inn,
                    pre_begin+index+1,pre_end,index+1,inn_end)
            break
        }
    }
    return rootNode
}



fun main(args:Array<String>) {

    var preData = listOf(1,2,4,7,3,5,6,8)
    var innData = listOf(4,7,2,1,5,3,8,6)

    var root = reConstructBinaryTree(preData, innData)

    printTreePreOrder(root)

    println()

    printTreeInnOrder(root)

    println()

    printTreePostOrder(root)

}