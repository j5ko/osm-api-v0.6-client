package petko.osm.api.client.v06.request.executor;

/**
 * The access credentials, which are generated after user grants access to the
 * client
 * 
 * @author 5ko
 *
 */
public class AccessCredentials {
	private String accessToken;
	private String tokenSecret;

	public AccessCredentials(String accessToken, String tokenSecret) {
		this.accessToken = accessToken;
		this.tokenSecret = tokenSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}
}