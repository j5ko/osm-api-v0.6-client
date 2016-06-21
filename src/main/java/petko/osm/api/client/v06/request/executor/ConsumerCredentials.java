package petko.osm.api.client.v06.request.executor;

public class ConsumerCredentials {
	private String consumerKey;
	private String consumerSecret;

	public ConsumerCredentials(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}
}