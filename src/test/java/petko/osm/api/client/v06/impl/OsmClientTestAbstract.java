package petko.osm.api.client.v06.impl;

import petko.osm.api.client.v06.api.OsmClientFacade;
import petko.osm.api.client.v06.api.OsmClientFactory;
import petko.osm.api.client.v06.request.executor.AccessCredentials;
import petko.osm.api.client.v06.request.executor.ConsumerCredentials;

public abstract class OsmClientTestAbstract {
	protected OsmClientFacade client;

	{
		AccessCredentials access = new AccessCredentials("D6zvtClNlJ7LNntvFWATC5rbPtxJtfwZIBQn7EJS",
				"5c4XzuTT6JeQ1vxkDan9ZMcrV5OvyTzpKrWNCezm");
		ConsumerCredentials consumerCreds = new ConsumerCredentials("KvYeiPmVjKzLWFaYMY1eUBy9rHOxQbqJpLWo831F",
				"bUWcMdQq3XKXVb1inygd5yfb5kUdIUqxF4YhJxPj");
		this.client = OsmClientFactory.newClient(access, consumerCreds);
	}
}