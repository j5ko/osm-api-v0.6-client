package petko.osm.api.client.v06.request.handler.cud;

import java.util.Collection;

import javax.xml.datatype.DatatypeConfigurationException;

import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmRelation;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;
import petko.osm.model.impl.xml.OsmNodeXml;
import petko.osm.model.impl.xml.OsmObjectFactoryXml;
import petko.osm.model.impl.xml.OsmWayXml;

/**
 * Utility to create datastore JAXB class
 * 
 * @author 5ko
 *
 */
public class DataStoreCreator {
	private OsmDataStoreSimpleXml dataStore = new OsmDataStoreSimpleXml();

	public DataStoreCreator(OsmNode node) throws DatatypeConfigurationException {
		addNode(node);
	}

	public DataStoreCreator(OsmWay way) throws DatatypeConfigurationException {
		addWay(way);
	}

	public DataStoreCreator(Changeset cs) throws DatatypeConfigurationException {
		addChangeSet(cs);
	}

	public DataStoreCreator(Collection<OsmNode> nodes, Collection<OsmWay> ways, Collection<OsmRelation> relations)
			throws DatatypeConfigurationException {
		addNodes(nodes);
		addWays(ways);
		// addRelations(relations);
	}

	private void addWays(Collection<OsmWay> ways) throws DatatypeConfigurationException {
		if (ways != null) {
			for (OsmWay osmWay : ways) {
				addWay(osmWay);
			}
		}
	}

	private void addWay(OsmWay osmWay) throws DatatypeConfigurationException {
		OsmWayXml wayXml = OsmObjectFactoryXml.createWayXml(osmWay);
		this.dataStore.getWays().add(wayXml);
	}

	private void addChangeSet(Changeset cs) {
		this.dataStore.setChangeSet(cs);
	}

	private void addNodes(Collection<OsmNode> nodes) throws DatatypeConfigurationException {
		if (nodes != null) {
			for (OsmNode osmNode : nodes) {
				addNode(osmNode);
			}
		}
	}

	private void addNode(OsmNode osmNode) throws DatatypeConfigurationException {
		OsmNodeXml nodeXml = OsmObjectFactoryXml.createNodeXml(osmNode);
		dataStore.getNodes().add(nodeXml);
	}

	public OsmDataStoreSimpleXml createDatastore() {
		return this.dataStore;
	}
}