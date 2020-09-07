package grain.configs;

/**
 * @author wulifu
 */
public enum Msg {
    /**
     * 操作参数返回值
     */
    SUCCESS("000000", "成功"),
    UNKNOWN("100001", "");

    String code;
    String description;

    Msg(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
