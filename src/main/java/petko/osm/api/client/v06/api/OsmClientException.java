package petko.osm.api.client.v06.api;

public class OsmClientException extends Exception {
	private static final long serialVersionUID = 1L;
	private Integer statusCode;

	public OsmClientException(Throwable t) {
		super(t);
	}

	public OsmClientException(Integer code, String errorMessage) {
		super(errorMessage);
		this.statusCode = code;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
}