package petko.osm.api.client.v06.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.simple.OsmNodeImpl;
import petko.osm.model.impl.simple.OsmNodeRef;
import petko.osm.model.impl.simple.OsmTagImpl;
import petko.osm.model.impl.simple.OsmWayImpl;

public class OsmClientWayTest extends OsmClientTestAbstract {

	@Test
	public void testCreateWay() {
		OsmNode node1 = null;
		OsmNode node2 = null;
		try {
			node1 = createNode(4302249626L);
			node2 = createNode(4301508286L);
			Long changeSetId = client.createChangeset("test5ko", "comment", null);
			List<OsmNode> nodes = new ArrayList<>();
			nodes.add(node1);
			nodes.add(node2);
			OsmWay way = new OsmWayImpl(1l, createNodeTags(), 3645356l, "Testuser", 514254L, changeSetId,
					Calendar.getInstance().getTime(), false, nodes);
			Long wayId = this.client.createWay(way);
			Assert.assertNotNull(wayId);
			// close changeset
			this.client.closeChangeset(changeSetId);
			OsmWay wayCreated = this.client.readWay(wayId);
			Assert.assertNotNull(wayCreated);
			Assert.assertEquals(2, wayCreated.getNodes().size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private OsmNode createNode(long id) {
		return new OsmNodeRef(id);
	}

	public OsmNode createNode(double lon, double lat) throws Exception {
		// create changeset
		Long changeSetId = client.createChangeset("test5ko", "comment", null);
		System.out.println("CS-ID:" + changeSetId);
		OsmNodeImpl node1 = new OsmNodeImpl(null, createNodeTags(), 3645356l, "Testuser", 514254L, changeSetId,
				Calendar.getInstance().getTime(), true, lon, lat);
		// create node
		Long nodeId = this.client.createNode(node1);
		node1.setId(nodeId);
		Assert.assertNotNull(nodeId);
		// close changeset
		this.client.closeChangeset(changeSetId);
		return node1;
	}

	private List<OsmTag> createNodeTags() {
		List<OsmTag> tags = new ArrayList<>();
		tags.add(new OsmTagImpl("name", "node"));
		tags.add(new OsmTagImpl("creator", "testuser"));
		return tags;
	}
}