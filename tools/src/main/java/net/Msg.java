package net;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wulifu
 */
public class Msg {
    private static int data_size = 1024;
    private static byte padding = 0x20;
    @Getter
    @Setter
    private byte[] msg = new byte[data_size];

    public static void main(String[] args) {
        byte[] bytes = new byte[99];
        for (int i = 0 ; i< 99  ; i++){
            bytes[i] = padding;
        }
        bytes[98] = '#';
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        System.out.println(padding);
    }

    public void setData(int begin, int length, byte[] data) {
        for (int i = 0; i < length; i++) {
            msg[i + begin] = data[i];
        }
    }

    public byte[] getData(int begin,int length){
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = this.getMsg()[begin + i];
        }
        return result;
    }
}
