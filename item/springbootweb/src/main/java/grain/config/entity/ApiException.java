package grain.config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * API Exception
 *
 * @author wangcong
 * @version 1.0.0 2018/3/26
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ApiException extends RuntimeException {
	protected Long code;
	protected Object data;

	public ApiException(Long code, String message) {
		super(message);
		this.code = code;
	}

	public ApiException(String message, Long code, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public ApiException(String message, Throwable cause, Long code) {
		super(message, cause);
		this.code = code;
	}

	public ApiException(Throwable cause, Long code, Object data) {
		super(cause);
		this.code = code;
		this.data = data;
	}

	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
						Long code, Object data) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.data = data;
	}
}
