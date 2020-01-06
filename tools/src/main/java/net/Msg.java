package net;


import net.utils.DataUtils;

/**
 * @author wulifu
 */
public class Msg {
    private static byte padding = 0x20;
    private byte[] msg = new byte[99];

    public byte[] getMsg() {
        return msg;
    }

    private void setData(int begin, int length, byte[] data) {
        for (int i = 0; i < length; i++) {
            msg[i + begin] = data[i];
        }
    }

    public void setIpId(String string) {
        setData(0, 3, DataUtils.padRight(DataUtils.getBytes(string), 3, padding));
    }

    public void setMessageId(int id) {
        setData(3, 4, DataUtils.getBytes(id));
    }

    public void setCMD(String string) {
        setData(7, 1, DataUtils.padRight(string, 1, padding));
    }

    public void setANI(String string) {
        setData(8, 20, DataUtils.padRight(string, 20, padding));
    }

    public void setDNIS(String string) {
        setData(28, 20, DataUtils.padRight(string, 20, padding));
    }

    public void setRingID(String string) {
        setData(48, 30, DataUtils.padRight(string, 30, padding));
    }

    public void setPlayTime(String string) {
        setData(78, 3, DataUtils.padRight(string, 3, padding));
    }

    public void setCallTime(String string) {
        setData(81, 17, DataUtils.padRight(string, 17, padding));
    }

    public void setEndFlag() {
        setData(98, 1, DataUtils.getBytes('#'));
    }
}
