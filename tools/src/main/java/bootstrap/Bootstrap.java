package bootstrap;


import java.util.Random;
import java.util.Scanner;

/**
 * @author wulifu
 */
public class Bootstrap {
    public static int score = 0;
    public static Random random = new Random();

    public static void print(int[][] arr) {
        int m = arr[0].length;
        for (int[] ints : arr) {
            for (int j = 0; j < m; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    public static String input(Scanner sc) {
        return sc.next();
    }

    public static boolean isFinish(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for (int[] ints : arr) {
            for (int j = 0; j < m - 1; j++) {
                if ((ints[j] == 0) || (ints[j] == ints[j + 1])) {
                    return false;
                }
            }
        }
        for(int j = 0;j < m;j++){
            for(int i = 0;i < n-1;i++){
                if ((arr[i][j] == 0) || (arr[i][j] == arr[i+1][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int makeData(Random random) {
        int r = random.nextInt(10);
        if(r >= 7){
            return 2;
        }
        return 0;
    }

    public static void moveUp(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for(int j = 0;j < m;j++){
            //上移
            for(int i = 0;i < n;i++){
                if(arr[i][j] == 0){
                    for(int k = i+1;k < n;k++){
                        if(arr[k][j] != 0){
                            arr[i][j] = arr[k][j];
                            arr[k][j] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for(int i = 0;i < n-1;i++){
                if(arr[i][j] == arr[i+1][j]){
                    arr[i][j] *= 2;
                    arr[i+1][j] = 0;
                    score += arr[i][j];
                }
            }
            //产生随机数
            for(int i = n-1;i >= 0;i--){
                if(arr[i][j] == 0){
                    arr[i][j] += makeData(random);
                }
            }
        }
    }

    public static void moveDown(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for(int j = 0;j < m;j++){
            //下移
            for(int i = n-1;i >= 0;i--){
                if(arr[i][j] == 0){
                    for(int k = i-1;k >= 0;k--){
                        if(arr[k][j] != 0){
                            arr[i][j] = arr[k][j];
                            arr[k][j] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for(int i = n-1;i > 0;i--){
                if(arr[i][j] == arr[i-1][j]){
                    arr[i][j] *= 2;
                    arr[i-1][j] = 0;
                    score += arr[i][j];
                }
            }
            //产生随机数
            for(int i = 0;i < n;i++){
                if(arr[i][j] == 0){
                    arr[i][j] += makeData(random);
                }
            }
        }
    }

    public static void moveLeft(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for(int i = 0;i < n;i++){
            //左移
            for(int j = 0;j < m;j++){
                if(arr[i][j] == 0){
                    for(int k = j+1;k < m;k++){
                        if(arr[i][k] != 0){
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for(int j = 0;j < m-1;j++){
                if(arr[i][j] == arr[i][j+1]){
                    arr[i][j] *= 2;
                    arr[i][j+1] = 0;
                    score += arr[i][j];
                }
            }
            //产生随机数
            for(int j = m-1;j >= 0;j--){
                if(arr[i][j] == 0){
                    arr[i][j] += makeData(random);
                }
            }
        }
    }

    public static void moveRight(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        for(int i = 0;i < n;i++){
            //左移
            for(int j = m-1;j >= 0;j--){
                if(arr[i][j] == 0){
                    for(int k = j-1;k >= 0;k--){
                        if(arr[i][k] != 0){
                            arr[i][j] = arr[i][k];
                            arr[i][k] = 0;
                            break;
                        }
                    }
                }
            }
            //归并
            for(int j = m-1;j > 0;j--){
                if(arr[i][j] == arr[i][j-1]){
                    arr[i][j] *= 2;
                    arr[i][j-1] = 0;
                    score += arr[i][j];
                }
            }
            //产生随机数
            for(int j = 0;j < m;j++){
                if(arr[i][j] == 0){
                    arr[i][j] += makeData(random);
                }
            }
        }
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[4][4];
        while(true){
            if (!isFinish(arr)) {
                String input = input(sc);
                switch (input) {
                    case "5":
                        moveUp(arr);
                        break;
                    case "2":
                        moveDown(arr);
                        break;
                    case "1":
                        moveLeft(arr);
                        break;
                    case "3":
                        moveRight(arr);
                        break;
                    default:
                        break;
                }
                print(arr);
            }else {
                System.out.println("score:" + score);
                break;
            }
        }
    }

    public static void main(String[] args) {
        run();
    }

}
