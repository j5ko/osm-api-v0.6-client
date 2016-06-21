package petko.osm.api.client.v06.impl;

import java.util.ArrayList;
import java.util.List;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.request.executor.ApiResponse;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.request.handler.cud.CUDRequestHandler;
import petko.osm.api.client.v06.request.handler.cud.DataStoreCreator;
import petko.osm.api.client.v06.request.handler.read.ChangeSetReadHandler;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmObjectFactoryXml;
import petko.osm.model.impl.xml.OsmTagXml;

public class ChangesetClient {
	private RequestExecutor requestExecutor;

	public ChangesetClient(RequestExecutor requestExecutor) {
		this.requestExecutor = requestExecutor;
	}

	public Long createChangeset(String createdBy, String comment, List<OsmTag> additionalTags)
			throws OsmClientException {
		try {
			List<OsmTag> tags = completeTags(createdBy, comment, additionalTags);
			Changeset changeSet = OsmObjectFactoryXml.createChangeSet(tags);
			DataStoreCreator dc = new DataStoreCreator(changeSet);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/changeset/create");
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public Changeset readChangeset(Long id, boolean includeDiscussion) throws OsmClientException {
		ChangeSetReadHandler handler = new ChangeSetReadHandler(this.requestExecutor, id, includeDiscussion);
		return handler.executeRead(id);
	}

	private List<OsmTag> completeTags(String createdBy, String comment, List<OsmTag> additionalTags) {
		List<OsmTag> tags = new ArrayList<>();
		OsmTagXml createdByTag = OsmObjectFactoryXml.createTag("created_by", createdBy);
		tags.add(createdByTag);
		OsmTagXml commentTag = OsmObjectFactoryXml.createTag("comment", comment);
		tags.add(commentTag);
		if (additionalTags != null) {
			tags.addAll(additionalTags);
		}
		return tags;
	}

	public Long updateChangeset(Changeset changeSet) throws OsmClientException {
		try {
			DataStoreCreator dc = new DataStoreCreator(changeSet);
			CUDRequestHandler handler = new CUDRequestHandler(this.requestExecutor);
			return handler.executePut(dc, "/changeset/create");
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
	}

	public void closeChangeset(Long id) throws OsmClientException {
		ApiResponse response = null;
		try {
			response = this.requestExecutor.executePut(null, "/changeset/" + id + "/close");
		} catch (Exception e) {
			throw new OsmClientException(e);
		}
		if (response.isSuccess()) {
			return;
		} else {
			throw new OsmClientException(response.getCode(), response.getMessage());
		}
	}
}