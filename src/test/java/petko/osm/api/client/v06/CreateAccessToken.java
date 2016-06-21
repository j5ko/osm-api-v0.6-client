package petko.osm.api.client.v06;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

public class CreateAccessToken {

	private static final String AUTHORIZATION_ENDPOINT = "http://api06.dev.openstreetmap.org/oauth/authorize";

	private static final String ACCESS_TOKEN_ENDPOINT = "http://api06.dev.openstreetmap.org/oauth/access_token";

	private static final String REQUEST_TOKEN_ENDPOINT = "http://api06.dev.openstreetmap.org/oauth/request_token";

	// add you keys here
	private static final String CONSUMER_KEY = null;
	private static final String CONSUMER_SECRET = null;

	public static void main(final String[] args) throws Exception {

		final OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

		final OAuthProvider provider = new DefaultOAuthProvider(REQUEST_TOKEN_ENDPOINT, ACCESS_TOKEN_ENDPOINT,
				AUTHORIZATION_ENDPOINT);

		System.out.println("Fetching request token from " + REQUEST_TOKEN_ENDPOINT);
		final String authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);

		System.out.println("Auth URL: " + authUrl);
		System.out.println("Request token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());

		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final String pin = br.readLine();
		br.close();

		provider.retrieveAccessToken(consumer, pin);

		System.out.println("Access token: " + consumer.getToken());
		System.out.println("Token secret: " + consumer.getTokenSecret());
	}
}