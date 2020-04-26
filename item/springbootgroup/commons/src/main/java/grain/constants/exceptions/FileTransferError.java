package grain.constants.exceptions;

/**
 * @author wulifu
 */
public class FileTransferError extends RuntimeException {
    public FileTransferError() {
        super("文件加载异常");
    }

    public FileTransferError(String string) {
        super("文件加载异常 " + string);
    }
}
