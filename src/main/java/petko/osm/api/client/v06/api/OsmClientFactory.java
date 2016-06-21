package petko.osm.api.client.v06.api;

import petko.osm.api.client.v06.impl.OsmClient;
import petko.osm.api.client.v06.request.executor.AccessCredentials;
import petko.osm.api.client.v06.request.executor.ConsumerCredentials;

public class OsmClientFactory {

	/**
	 * @param access
	 * @param consumerCreds
	 * @return new instance of OsmClientFacade
	 */
	public static OsmClientFacade newClient(AccessCredentials access, ConsumerCredentials consumerCreds) {
		return new OsmClient(access, consumerCreds);
	}
}