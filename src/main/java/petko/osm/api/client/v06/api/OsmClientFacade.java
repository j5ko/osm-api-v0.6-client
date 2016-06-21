package petko.osm.api.client.v06.api;

import java.util.List;

import javax.xml.bind.JAXBException;

import petko.osm.api.client.v06.api.model.capabilities.Capatibilities;
import petko.osm.model.facade.OsmDataStore;
import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.xml.Changeset;

/**
 * OSM client API
 * 
 * @author 5ko
 *
 */
public interface OsmClientFacade {
	/**
	 * @param createdBy
	 * @param comment
	 * @param additionalTags
	 * @return ID of changeset
	 * @throws OsmClientException
	 * @throws JAXBException
	 *             if marshaling fails
	 * @throws OsmRequestException
	 *             if OSm API request fails
	 */
	Long createChangeset(String createdBy, String comment, List<OsmTag> additionalTags) throws OsmClientException;

	Long updateChangeset(Changeset changeSet) throws OsmClientException;

	Changeset readChangeset(Long id, boolean includeDiscussion) throws OsmClientException;

	void closeChangeset(Long id) throws OsmClientException;

	Long createNode(OsmNode node) throws OsmClientException;

	Long updateNode(OsmNode node) throws OsmClientException;

	OsmNode readNode(Long nodeId) throws OsmClientException;

	Long deleteNode(OsmNode node) throws OsmClientException;

	Capatibilities getCapabilities() throws OsmClientException;

	Long deleteNode(Long nodeId, String createdBy, String comment) throws OsmClientException;

	Long createWay(OsmWay way) throws OsmClientException;

	Long updateWay(OsmWay way) throws OsmClientException;

	Long deleteWay(OsmWay way) throws OsmClientException;

	OsmWay readWay(Long wayId) throws OsmClientException;

	public OsmDataStore map(float left, float bottom, float right, float top) throws OsmClientException;

}