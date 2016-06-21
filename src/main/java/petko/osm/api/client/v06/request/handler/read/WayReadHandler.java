package petko.osm.api.client.v06.request.handler.read;

import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;

public class WayReadHandler extends ReadHandler<OsmWay> {

	public WayReadHandler(RequestExecutor re) {
		super(re, "/way/" + OBJECT_ID_PLACEHOLDER);
	}

	@Override
	protected OsmWay retrieveData(OsmDataStoreSimpleXml osmData) {
		return osmData.getWays().get(0);
	}
}