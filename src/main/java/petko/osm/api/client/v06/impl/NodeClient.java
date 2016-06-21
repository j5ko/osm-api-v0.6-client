package petko.osm.api.client.v06.impl;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.request.handler.cud.CUDRequestHandler;
import petko.osm.api.client.v06.request.handler.cud.DataStoreCreator;
import petko.osm.api.client.v06.request.handler.read.NodeReadHandler;
import petko.osm.model.facade.OsmNode;

/**
 * OSM API client handling execution of node CRUD operations
 * 
 * @author 5ko
 *
 */
class NodeClient {
	private RequestExecutor requestExecutor;

	public NodeClient(RequestExecutor requestExecutor) {
		this.requestExecutor = requestExecutor;
	}

	public Long createNode(OsmNode node) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(node);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/node/create");
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public Long updateNode(OsmNode node) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(node);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/node/" + node.getId());
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public Long deleteNode(OsmNode node) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(node);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executeDelete(dc, "/node/" + node.getId());
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public OsmNode readNode(Long nodeId) throws OsmClientException {
		NodeReadHandler readhandler = new NodeReadHandler(this.requestExecutor);
		return readhandler.executeRead(nodeId);
	}
}