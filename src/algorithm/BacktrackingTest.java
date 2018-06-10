package algorithm;
import java.util.Scanner;

/**
 *
 * n    ...3    4   5   6   7   8
 * ������   0    2   10  4   40  92
 *
 *
 * ���ݷ�ʵ���Dn�ʺ��㷨 (javaʵ��)��
 * https://blog.csdn.net/catkint/article/details/51334768
 *
 * https://blog.csdn.net/yangjingjing9/article/details/76153158
 *
 */
public class BacktrackingTest {


        public static void main(String [] agrs){
            Scanner sc = new Scanner(System.in);
            System.out.println("������n:");
            int n = sc.nextInt();
            System.out.println(n+"�ʺ�Ľ��������"+track(n)+"��");
        }


        public static long track(int n){
            Queen X = new Queen();//ʹ�õݹ鷽��
            Queen Y = new Queen();//ʹ�õ�������4
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
            System.out.println("ʹ�õݹ鷽������ʱ��:"+(endTime-startTime)+"ms");
            for(int i=0;i<=n;i++){
                Y.x[i]=0;
            }
            startTime = System.currentTimeMillis();
            Y.whileBackTrack();
            endTime = System.currentTimeMillis();
            System.out.println("ʹ��ѭ����������ʱ��:"+(endTime-startTime)+"ms");
            return X.sum;
        }
    }

    class Queen{
        public int n; //�ʺ����
        public int[] x;//��ǰ��    index������  x[index]������
        public long sum;//��ǰ���ҵ��Ŀ��з�����

        private boolean Place(int k){//����ڵ�k�еĿ�����
            for(int j=1;j<k;j++){
                int xj = x[j];
                int xk = x[k];
                boolean first = (Math.abs(k-j)==Math.abs(x[j]-x[k]));
                boolean second = (xj == xk);
                if (first || second)
                    return false;
//                if(Math.abs(k-j)==Math.abs(x[j]-x[k])||(x[j]==x[k]))return false;//˵������������
            }
            return true;//˵����������
        }
        public void backTrack(int t){//�ݹ鷽��
            if(t>n) {//����ִ�Ҷ�ӽ���ʱ�򣬼����в㶼�������
                sum++;//���з�����+1
                printWay();//��ӡ����ǰ�Ľ�
            }
            else{
                for(int i=1;i<=n;i++){//ÿ��ѭ������һ��
                    x[t] = i;
                    if(Place(t))backTrack(t+1);//������ȵݹ�ض���������
                }
            }
        }

        public long whileBackTrack(){//ѭ������
            x[1] = 0;
            int k=1;
            while(k>0){
                x[k] += 1;
                while((x[k]<=n)&&!(Place(k)))x[k]+=1;//������������һֱ����һ�У�ֱ������������Խ��
                if(x[k]<=n){//�˳�whileѭ��ʱû��Խ��
                    if(k==n)sum++;//�ִ����һ�У����з�����+1
                    else{
                        k++;//����һ��
                        x[k] = 0;//����ֵ��ֵΪ0
                    }
                }else k--;//˵����������в�ͨ��������һ��
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
