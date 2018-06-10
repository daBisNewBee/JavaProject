package dataStruct;


import java.util.*;

public class BinTree {
    private BinTree lChild;//左孩子
    private BinTree rChild;//右孩子
    private BinTree root;//根节点
    private Object data; //数据域
    private List<BinTree> datas;//存储所有的节点

    public BinTree(BinTree lChild, BinTree rChild, Object data) {
        super();
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }
    public BinTree(Object data) {
        this(null, null, data);
    }
    public BinTree() {
        super();
    }

    public void createTree(Object[] objs){
        datas=new ArrayList<BinTree>();
        for (Object object : objs) {
            datas.add(new BinTree(object));
        }
        root=datas.get(0);//将第一个作为根节点
        for (int i = 0; i < objs.length/2; i++) {
            datas.get(i).lChild=datas.get(i*2+1);
            if(i*2+2<datas.size()){//避免偶数的时候 下标越界
                datas.get(i).rChild=datas.get(i*2+2);
            }
        }
    }
    //先序遍历
    public void preorder(BinTree root){
        if(root!=null){
            visit(root.getData());
            preorder(root.lChild);
            preorder(root.rChild);
        }

    }
    //中序遍历
    public void inorder(BinTree root){
        if(root!=null){
            inorder(root.lChild);
            visit(root.getData());
            inorder(root.rChild);
        }

    }
    //后序遍历
    public void afterorder(BinTree root){
        if(root!=null){
            afterorder(root.lChild);
            afterorder(root.rChild);
            visit(root.getData());
        }

    }
    private void visit(Object obj) {
        System.out.print(obj+" ");
    }
    public Object getData() {
        return data;
    }
    public BinTree getRoot() {
        return root;
    }

    /**
     *
     * 深度优先。
     * 需要的数据结构：栈
     *
     */
    public void depthFirst(){
        Stack<BinTree> nodeStack = new Stack<>();
        nodeStack.add(getRoot());

        while (!nodeStack.isEmpty()){

            // 区别：取出的元素。栈：后进入的；队列：先进入的
            BinTree node = nodeStack.pop();
            visit(node.getData());

            // 此处，先遍历左子树，再遍历右子树。因此右子树先入栈
            if (node.rChild != null){
                nodeStack.push(node.rChild);
            }

            if (node.lChild != null){
                nodeStack.push(node.lChild);
            }
        }
    }

    /**
     * 广度优先。
     *
     * 需要的数据结构：队列
     *
     */
    public void breadthFirst(){

        Queue<BinTree> nodeQueue = new ArrayDeque<>();

        nodeQueue.add(getRoot());

        while (!nodeQueue.isEmpty()){

            BinTree node = nodeQueue.poll();

            visit(node.getData());

            // 注意：先左子树，再右子树。与深度优先不同
            if (node.lChild != null){
                nodeQueue.add(node.lChild);
            }

            if (node.rChild != null){
                nodeQueue.add(node.rChild);
            }

        }

    }

    public static void main(String[] args) {
        BinTree binTree=new BinTree();
        Object[] objs={0,1,2,3,4,5,6,7};
        binTree.createTree(objs);
        System.out.println("先序：");
        binTree.preorder(binTree.getRoot()); // 先序遍历
        System.out.println("\n中序");
        binTree.inorder(binTree.getRoot()); // 中序遍历
        System.out.println("\n后序");
        binTree.afterorder(binTree.getRoot()); // 后序遍历
        System.out.println("\n深度优先");
        binTree.depthFirst();
        System.out.println("\n广度优先");
        binTree.breadthFirst();

    }

}