package grain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author wulifu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    @NonNull
    private int code;
    private String message;
    private Object data;
    public static final int code_success = 200;
    public static final int code_unhandled_error = 500;
    public static final int code_file_transfer_error = 501;
    public static final int code_task_info_error = 502;

    public static Msg success() {
        return new Msg(code_success, "success", null);
    }

    public static Msg success(Object data) {
        return new Msg(code_success, "success", data);
    }

    public static Msg error(int code, String message) {
        return new Msg(code, message, null);
    }
}
