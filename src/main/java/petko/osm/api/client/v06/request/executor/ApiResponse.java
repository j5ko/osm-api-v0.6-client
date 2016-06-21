package petko.osm.api.client.v06.request.executor;

public class ApiResponse {
	public static final int RESPONSE_CODE_OK = 200;
	private int code;
	private String message = null;

	public ApiResponse(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return this.message;
	}

	public boolean isSuccess() {
		return this.code == RESPONSE_CODE_OK;
	}
}