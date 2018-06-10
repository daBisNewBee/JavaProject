package algorithm;
import java.util.Scanner;

/**
 *
 * n    ...3    4   5   6   7   8
 * 方案数   0    2   10  4   40  92
 *
 *
 * 回溯法实例―n皇后算法 (java实现)：
 * https://blog.csdn.net/catkint/article/details/51334768
 *
 * https://blog.csdn.net/yangjingjing9/article/details/76153158
 *
 */
public class BacktrackingTest {


        public static void main(String [] agrs){
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入n:");
            int n = sc.nextInt();
            System.out.println(n+"皇后的解决方案有"+track(n)+"种");
        }


        public static long track(int n){
            Queen X = new Queen();//使用递归方法
            Queen Y = new Queen();//使用迭代方法4
            X.n = n;
            X.sum = 0;
            Y.n = n;
            Y.sum = 0;
            int[] arr = new int[n+1];
            for(int i=0;i<=n;i++){
                arr[i]=0;
            }
            X.x = arr;
            Y.x = arr;
            long startTime = System.currentTimeMillis();
            X.backTrack(1);
            long endTime = System.currentTimeMillis();
            System.out.println("使用递归方法运行时间:"+(endTime-startTime)+"ms");
            for(int i=0;i<=n;i++){
                Y.x[i]=0;
            }
            startTime = System.currentTimeMillis();
            Y.whileBackTrack();
            endTime = System.currentTimeMillis();
            System.out.println("使用循环方法运行时间:"+(endTime-startTime)+"ms");
            return X.sum;
        }
    }

    class Queen{
        public int n; //皇后个数
        public int[] x;//当前解    index代表行  x[index]代表列
        public long sum;//当前已找到的可行方案数

        private boolean Place(int k){//检查在第k行的可行性
            for(int j=1;j<k;j++){
                int xj = x[j];
                int xk = x[k];
                boolean first = (Math.abs(k-j)==Math.abs(x[j]-x[k]));
                boolean second = (xj == xk);
                if (first || second)
                    return false;
//                if(Math.abs(k-j)==Math.abs(x[j]-x[k])||(x[j]==x[k]))return false;//说明不符合条件
            }
            return true;//说明符合条件
        }
        public void backTrack(int t){//递归方法
            if(t>n) {//当其抵达叶子结点的时候，即所有层都搜索完毕
                sum++;//可行方案数+1
                printWay();//打印出当前的解
            }
            else{
                for(int i=1;i<=n;i++){//每次循环右移一列
                    x[t] = i;
                    if(Place(t))backTrack(t+1);//深度优先递归地对子树搜索
                }
            }
        }

        public long whileBackTrack(){//循环方法
            x[1] = 0;
            int k=1;
            while(k>0){
                x[k] += 1;
                while((x[k]<=n)&&!(Place(k)))x[k]+=1;//不符合条件就一直右移一列，直到符合条件或越界
                if(x[k]<=n){//退出while循环时没有越界
                    if(k==n)sum++;//抵达最后一行，可行方案数+1
                    else{
                        k++;//下移一行
                        x[k] = 0;//将初值赋值为0
                    }
                }else k--;//说明这个方法行不通，向上退一行
            }
            return sum;
        }

        public void printWay(){
            for(int i=1;i<=n;i++){
                System.out.print("("+i+","+x[i]+") ");
            }
            System.out.println();
        }


}
