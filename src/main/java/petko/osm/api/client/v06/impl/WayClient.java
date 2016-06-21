package petko.osm.api.client.v06.impl;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.request.handler.cud.CUDRequestHandler;
import petko.osm.api.client.v06.request.handler.cud.DataStoreCreator;
import petko.osm.api.client.v06.request.handler.read.WayReadHandler;
import petko.osm.model.facade.OsmWay;

/**
 * OSM API client handling execution of way CRUD operations
 * 
 * @author 5ko
 *
 */
public class WayClient {
	private RequestExecutor requestExecutor;

	public WayClient(RequestExecutor requestExecutor) {
		this.requestExecutor = requestExecutor;
	}

	public Long createWay(OsmWay way) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(way);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/way/create");
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public Long updateWay(OsmWay way) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(way);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/way/" + way.getId());
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public Long deleteWay(OsmWay way) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(way);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executeDelete(dc, "/way/" + way.getId());
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public OsmWay readWay(Long wayId) throws OsmClientException {
		WayReadHandler readhandler = new WayReadHandler(this.requestExecutor);
		return readhandler.executeRead(wayId);
	}
}