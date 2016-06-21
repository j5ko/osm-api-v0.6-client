package petko.osm.api.client.v06.request.handler.read;

import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;

public class ChangeSetReadHandler extends ReadHandler<Changeset> {

	public ChangeSetReadHandler(RequestExecutor re, Long id, boolean includeDiscussion) {
		super(re, "/changeset/" + OBJECT_ID_PLACEHOLDER + "?include_discussion=" + includeDiscussion);
	}

	@Override
	protected Changeset retrieveData(OsmDataStoreSimpleXml osmData) {
		return osmData.getChangeSet();
	}
}