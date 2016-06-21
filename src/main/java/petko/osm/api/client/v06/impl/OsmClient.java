package petko.osm.api.client.v06.impl;

import java.util.List;

import javax.xml.bind.JAXBException;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.api.OsmClientFacade;
import petko.osm.api.client.v06.api.model.capabilities.Capatibilities;
import petko.osm.api.client.v06.request.executor.AccessCredentials;
import petko.osm.api.client.v06.request.executor.ApiResponse;
import petko.osm.api.client.v06.request.executor.ConsumerCredentials;
import petko.osm.api.client.v06.request.executor.RequestExecutor;
import petko.osm.api.client.v06.xml.XmlMarshaler;
import petko.osm.model.facade.OsmDataStore;
import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.simple.OsmNodeImpl;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;

/**
 * OSM API v0.6 client
 * 
 * @author 5ko
 *
 */
public class OsmClient implements OsmClientFacade {
	private RequestExecutor requestExecutor;
	private final NodeClient nodeClient;
	private final WayClient wayClient;
	private final ChangesetClient changeSetClient;

	public OsmClient(AccessCredentials access, ConsumerCredentials consumerCreds) {
		this.requestExecutor = new RequestExecutor(access, consumerCreds, "http://api06.dev.openstreetmap.org/api/0.6");
		this.nodeClient = new NodeClient(requestExecutor);
		this.wayClient = new WayClient(requestExecutor);
		this.changeSetClient = new ChangesetClient(requestExecutor);
	}

	@Override
	public Long createChangeset(String createdBy, String comment, List<OsmTag> additionalTags)
			throws OsmClientException {
		return this.changeSetClient.createChangeset(createdBy, comment, additionalTags);
	}

	@Override
	public Long updateChangeset(Changeset changeSet) throws OsmClientException {
		return this.changeSetClient.updateChangeset(changeSet);
	}

	@Override
	public Changeset readChangeset(Long id, boolean includeDiscussion) throws OsmClientException {
		return this.changeSetClient.readChangeset(id, includeDiscussion);
	}

	@Override
	public void closeChangeset(Long id) throws OsmClientException {
		this.changeSetClient.closeChangeset(id);
	}

	@Override
	public Long createNode(OsmNode node) throws OsmClientException {
		return this.nodeClient.createNode(node);
	}

	@Override
	public Long updateNode(OsmNode node) throws OsmClientException {
		return nodeClient.updateNode(node);
	}

	@Override
	public Long deleteNode(OsmNode node) throws OsmClientException {
		return nodeClient.deleteNode(node);
	}

	@Override
	public Long deleteNode(Long nodeId, String createdBy, String comment) throws OsmClientException {
		OsmNode node = readNode(nodeId);
		Long changeSetId = createChangeset(createdBy, comment, null);
		OsmNodeImpl nodeToDelete = new OsmNodeImpl(node.getId(), node.getTags(), node.getUid(), node.getUser(),
				node.getVersion(), changeSetId, node.getTimestamp(), node.isVisible(), node.getLongitude(),
				node.getLatitude());
		return deleteNode(nodeToDelete);
	}

	@Override
	public OsmNode readNode(Long nodeId) throws OsmClientException {
		return nodeClient.readNode(nodeId);
	}

	@Override
	public Long createWay(OsmWay way) throws OsmClientException {
		return this.wayClient.createWay(way);
	}

	@Override
	public Long updateWay(OsmWay way) throws OsmClientException {
		return this.wayClient.updateWay(way);
	}

	@Override
	public Long deleteWay(OsmWay way) throws OsmClientException {
		return this.wayClient.deleteWay(way);
	}

	@Override
	public OsmWay readWay(Long wayId) throws OsmClientException {
		return this.wayClient.readWay(wayId);
	}

	@Override
	public Capatibilities getCapabilities() throws OsmClientException {
		ApiResponse response;
		response = this.requestExecutor.executeGet("/capabilities");
		XmlMarshaler marshaller = new XmlMarshaler();
		if (response.isSuccess()) {
			try {
				return marshaller.unmarshal(response.getMessage(), Capatibilities.class);
			} catch (JAXBException e) {
				throw new OsmClientException(e);
			}
		}
		throw new OsmClientException(response.getCode(), response.getMessage());
	}

	@Override
	public OsmDataStore map(float left, float bottom, float right, float top) throws OsmClientException {
		ApiResponse resp = this.requestExecutor
				.executeGet("/map?bbox=" + left + "," + bottom + "," + right + "," + top);
		String responseStr = resp.getMessage();
		XmlMarshaler marshaller = new XmlMarshaler();
		if (resp.isSuccess()) {
			try {
				return marshaller.unmarshal(responseStr, OsmDataStoreSimpleXml.class);
			} catch (JAXBException e) {
				throw new OsmClientException(e);
			}
		}
		throw new OsmClientException(resp.getCode(), responseStr);
	}
}