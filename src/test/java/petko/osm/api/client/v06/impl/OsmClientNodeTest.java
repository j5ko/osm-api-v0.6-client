package petko.osm.api.client.v06.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.impl.simple.OsmNodeImpl;
import petko.osm.model.impl.simple.OsmTagImpl;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmTagXml;

public class OsmClientNodeTest extends OsmClientTestAbstract {
	@Test(expected = Exception.class)
	public void testCreateNodeFails() throws Exception {
		OsmNodeImpl node = new OsmNodeImpl(1L, createNodeTags(), 3645356l, "Testuser", 514254L, 0L,
				Calendar.getInstance().getTime(), false, 14.79769d, 47.94853d);
		// try create node without changeset
		this.client.createNode(node);
		Assert.fail();
	}

	@Test
	public void testCreateNode() {
		try {
			// create changeset
			Long changeSetId = client.createChangeset("test5ko", "comment", null);
			System.out.println("CS-ID:" + changeSetId);
			OsmNodeImpl node = new OsmNodeImpl(1L, createNodeTags(), 3645356l, "Testuser", 514254L, changeSetId,
					Calendar.getInstance().getTime(), false, 14.82769d, 47.97853d);
			// create node
			Long nodeId = this.client.createNode(node);
			Assert.assertNotNull(nodeId);
			System.out.println("Node ID:" + nodeId);
			// read node
			OsmNode nodeRead = this.client.readNode(nodeId);
			Assert.assertEquals("node", nodeRead.getTagValue("name"));
			// update node
			nodeRead.getTags().add(new OsmTagXml("author", "test"));
			long newVersion = this.client.updateNode(nodeRead);
			Assert.assertNotNull(newVersion);
			Assert.assertEquals(2L, newVersion);
			System.out.println("Updated Node version:" + newVersion);
			// read node again
			nodeRead = this.client.readNode(nodeId);
			// delete node
			newVersion = this.client.deleteNode(nodeRead);
			Assert.assertNotNull(newVersion);
			Assert.assertEquals(3L, newVersion);
			System.out.println("Deleted Node version:" + newVersion);
			// close changeset
			this.client.closeChangeset(changeSetId);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void deleteNode() {
		try {
			Long newId = client.deleteNode(4301509392L, "5ko", "delete test");
			System.out.println("new node id:" + newId);
		} catch (OsmClientException e) {
			e.printStackTrace();
		}
	}

	private List<OsmTag> createNodeTags() {
		List<OsmTag> tags = new ArrayList<>();
		tags.add(new OsmTagImpl("name", "node"));
		tags.add(new OsmTagImpl("creator", "testuser"));
		return tags;
	}

	@Test
	public void testReadChangeset() {
		try {
			Changeset cs = this.client.readChangeset(69257L, false);
			Assert.assertNotNull(cs);
			Assert.assertNotNull(cs.getTags());
		} catch (OsmClientException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}