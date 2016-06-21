package petko.osm.api.client.v06.request.executor;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import petko.osm.api.client.v06.api.OsmClientException;

/**
 * Signs and executes HTTP request to OSM API server
 * 
 * @author 5ko
 *
 */
public class RequestExecutor {
	private OAuthConsumer oauthConsumer;
	private HttpClient httpClient = new DefaultHttpClient();
	private String apiUrl;

	public RequestExecutor(AccessCredentials access, ConsumerCredentials consumerCreds, String apiUrl) {
		this.oauthConsumer = initOauthConsumer(access, consumerCreds);
		this.apiUrl = apiUrl;
	}

	private OAuthConsumer initOauthConsumer(AccessCredentials access, ConsumerCredentials consumerCreds) {
		OAuthConsumer oa = new CommonsHttpOAuthConsumer(consumerCreds.getConsumerKey(),
				consumerCreds.getConsumerSecret());
		oa.setTokenWithSecret(access.getAccessToken(), access.getTokenSecret());
		return oa;
	}

	public ApiResponse executeGet(String requestUrlPart) throws OsmClientException {
		HttpGet getRequest = new HttpGet(apiUrl + requestUrlPart);
		return executeRequest(getRequest);
	}

	public ApiResponse executePut(String requestBody, String requestUrlPart) throws OsmClientException {
		HttpPut putRequest = new HttpPut(apiUrl + requestUrlPart);
		if (requestBody != null) {
			HttpEntity entity;
			try {
				entity = new StringEntity(requestBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new OsmClientException(e);
			}
			putRequest.setEntity(entity);
		}
		return executeRequest(putRequest);
	}

	public ApiResponse executeDelete(String requestBody, String requestUrlPart) throws OsmClientException {
		HttpDeleteWithBody deleteRequest = new HttpDeleteWithBody(apiUrl + requestUrlPart);
		if (requestBody != null) {
			HttpEntity entity;
			try {
				entity = new StringEntity(requestBody, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new OsmClientException(e);
			}
			deleteRequest.setEntity(entity);
		}
		return executeRequest(deleteRequest);
	}

	private ApiResponse executeRequest(HttpUriRequest request) throws OsmClientException {
		signRequest(request);
		HttpResponse httpResponse;
		try {
			httpResponse = this.httpClient.execute(request);
		} catch (IOException e) {
			throw new OsmClientException(e);
		}
		int code = httpResponse.getStatusLine().getStatusCode();
		String respString = getResponseString(httpResponse);
		return new ApiResponse(respString, code);
	}

	protected void signRequest(HttpUriRequest request) throws OsmClientException {
		try {
			this.oauthConsumer.sign(request);
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
			throw new OsmClientException(e);
		}
	}

	private String getResponseString(HttpResponse response) throws OsmClientException {
		InputStream respIS = null;
		try {
			respIS = response.getEntity().getContent();
			List<String> reponseLines = IOUtils.readLines(respIS);
			return toString(reponseLines);
		} catch (Exception e) {
			throw new OsmClientException(e);
		} finally {
			IOUtils.closeQuietly(respIS);
		}
	}

	private String toString(List<String> lines) {
		StringBuilder strBuilder = new StringBuilder();
		for (String lineString : lines) {
			strBuilder.append(lineString);
		}
		return strBuilder.toString();
	}
}