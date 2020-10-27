package bootstrap;

public class SaoLei {
    public int rows;        // 行
    public int columns;     // 列
    public int count;       // 地雷数
    public int[][] data;    // 存放数据信息
    static int tipNum = 0;  // 提示数字

    public static void main(String[] args) {
        new SaoLei(50, 50, 2400);
    }

    public SaoLei(int rows, int columns, int count) {
        this.rows = Math.min(rows, 100);            // 最大24行
        this.columns = Math.min(columns, 100);   // 最大30列
        this.count = count;

        // 布置地雷
        run();

        // 输出
        printRes();
    }

    public void run() {
        data = new int[this.rows + 1][this.columns + 1];

        // 地雷
        putMines();

        // 数字
        putNumber();
    }

    // 输出结果
    public void printRes() {
        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[i].length; j++) {
                switch (data[i][j]) {
                    case -1:
                        System.out.print("*");
                        break;
                    case -2:
                        System.out.print("~");
                        break;
                    default:
                        System.out.print(data[i][j]);
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // 设置提示数字
    public void putNumber() {
        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[i].length; j++) {
                if (data[i][j] == 0) {
                    judge(i, j);
                    data[i][j] = tipNum;
                }
            }
        }

        // 将所有0变为-2
        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[i].length; j++) {
                data[i][j] = data[i][j] == 0 ? -2 : data[i][j];
            }
        }
    }

    // 布置地雷,count-->地雷数,随机生成数为0则布置一颗地雷,data中-1存放
    public void putMines() {
        int t_count = 0;

        while (t_count < count) {
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    if (data[i][j] == 0 && (int) (Math.random() * (Math.sqrt(rows) * columns)) == 0 && judge(i, j)) {
                        data[i][j] = -1;
                        t_count++;
                    }
                    if (t_count == count) return;
                }
            }
        }
    }

    // 示数字
    public boolean judge(int r, int j) {
        boolean flag = false;
        tipNum = 0;

        // up
        if (r - 1 >= 0) {
            if (data[r - 1][j] != -1) {
                flag = true;
            } else tipNum++;
        }

        // left
        if (j - 1 >= 0) {
            if (data[r][j - 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        // down
        if (r + 1 <= this.rows) {
            if (data[r + 1][j] != -1) {
                flag = true;
            } else tipNum++;
        }

        // right
        if (j + 1 <= this.columns) {
            if (data[r][j + 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        // left+up
        if (r - 1 >= 0 && j - 1 >= 0) {
            if (data[r - 1][j - 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        // left+down
        if (r + 1 <= this.rows && j - 1 >= 0) {
            if (data[r + 1][j - 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        // right+up
        if (r - 1 >= 0 && j + 1 <= this.columns) {
            if (data[r - 1][j + 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        // right+down
        if (r + 1 <= this.rows && j + 1 <= this.columns) {
            if (data[r + 1][j + 1] != -1) {
                flag = true;
            } else tipNum++;
        }

        return flag;
    }

}
