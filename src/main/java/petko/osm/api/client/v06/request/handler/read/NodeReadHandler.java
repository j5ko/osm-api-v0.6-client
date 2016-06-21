package petko.osm.api.client.v06.request.handler.read;

import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.model.facade.OsmNode;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;

public class NodeReadHandler extends ReadHandler<OsmNode> {

	public NodeReadHandler(RequestExecutor re) {
		super(re, "/node/" + OBJECT_ID_PLACEHOLDER);
	}

	@Override
	protected OsmNode retrieveData(OsmDataStoreSimpleXml osmData) {
		return osmData.getNodes().get(0);
	}

}
