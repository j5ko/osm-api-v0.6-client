package petko.osm.api.client.v06.xml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import petko.osm.model.facade.OsmNode;
import petko.osm.model.facade.OsmRelation;
import petko.osm.model.facade.OsmRelationMember;
import petko.osm.model.facade.OsmRelationMemberType;
import petko.osm.model.facade.OsmTag;
import petko.osm.model.facade.OsmWay;
import petko.osm.model.impl.simple.OsmNodeImpl;
import petko.osm.model.impl.simple.OsmRelationImpl;
import petko.osm.model.impl.simple.OsmRelationMemberImpl;
import petko.osm.model.impl.simple.OsmTagImpl;
import petko.osm.model.impl.simple.OsmWayImpl;
import petko.osm.model.impl.xml.OsmDataStoreSimpleXml;
import petko.osm.model.impl.xml.OsmDataStoreXml;
import petko.osm.model.impl.xml.OsmObjectFactoryXml;

public class XmlMarshallerTest {

	@Test
	public void testNode() {
		List<OsmTag> tags = createNodeTags();
		Date date = Calendar.getInstance().getTime();
		OsmNode node = new OsmNodeImpl(1L, tags, 3645356l, "Testuser", 514254L, 72365L, date, false, 18.d, 49.3d);
		OsmNode nodeXml;
		try {
			nodeXml = OsmObjectFactoryXml.createNodeXml(node);
			XmlMarshaler m = new XmlMarshaler();
			String xmlString = m.marshal(nodeXml);
			Assert.assertNotNull(xmlString);
			System.out.println(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testOsmData() {
		List<OsmTag> nodeTags = createNodeTags();
		Date date = Calendar.getInstance().getTime();
		try {
			List<OsmNode> nodes = new ArrayList<>();
			nodes.add(new OsmNodeImpl(1L, nodeTags, 3645356l, "Testuser", 514254L, 72365L, date, false, 18.d, 49.3d));
			List<OsmWay> ways = new ArrayList<>();
			ways.add(new OsmWayImpl(12L, createWayTags(), 23232L, "testuser", 1L, 2L, Calendar.getInstance().getTime(),
					true, nodes));
			List<OsmRelation> relations = new ArrayList<>();
			List<OsmRelationMember> members = createRelmembers();
			relations.add(new OsmRelationImpl(3423L, createRelTags(), 34332L, "user Test", 2L, 3L,
					Calendar.getInstance().getTime(), true, members));
			OsmDataStoreXml osmDataXml = OsmObjectFactoryXml.createDataStore(nodes, ways, relations);
			osmDataXml.init(0.6f, "generator", "copyright", "attribution", "license");
			XmlMarshaler m = new XmlMarshaler();
			String xmlString = m.marshal(osmDataXml);
			Assert.assertNotNull(xmlString);
			System.out.println(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testOsmDataSimple() {
		List<OsmTag> nodeTags = createNodeTags();
		Date date = Calendar.getInstance().getTime();
		try {
			List<OsmNode> nodes = new ArrayList<>();
			nodes.add(new OsmNodeImpl(1L, nodeTags, 3645356l, "Testuser", 514254L, 72365L, date, false, 18.d, 49.3d));
			List<OsmWay> ways = new ArrayList<>();
			ways.add(new OsmWayImpl(12L, createWayTags(), 23232L, "testuser", 1L, 2L, Calendar.getInstance().getTime(),
					true, nodes));
			List<OsmRelation> relations = new ArrayList<>();
			List<OsmRelationMember> members = createRelmembers();
			relations.add(new OsmRelationImpl(3423L, createRelTags(), 34332L, "user Test", 2L, 3L,
					Calendar.getInstance().getTime(), true, members));
			OsmDataStoreSimpleXml osmDataXml = OsmObjectFactoryXml.createDataStoreSimple(nodes, ways, relations);
			XmlMarshaler m = new XmlMarshaler();
			String xmlString = m.marshal(osmDataXml);
			Assert.assertNotNull(xmlString);
			System.out.println(xmlString);
			// test unmarshall
			OsmDataStoreSimpleXml nodeUnmarshalled = m.unmarshal(xmlString, OsmDataStoreSimpleXml.class);
			Assert.assertNotNull(nodeUnmarshalled);
			Assert.assertNotNull(nodeUnmarshalled.getNodes());
			OsmNode unmarshalledNode = nodeUnmarshalled.getNodes().get(0);
			Assert.assertNotNull(unmarshalledNode);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private List<OsmRelationMember> createRelmembers() {
		List<OsmRelationMember> members = new ArrayList<>();
		members.add(new OsmRelationMemberImpl(OsmRelationMemberType.NODE, "testrole", 1212L));
		members.add(new OsmRelationMemberImpl(OsmRelationMemberType.WAY, "testrole2", 35786L));
		members.add(new OsmRelationMemberImpl(OsmRelationMemberType.RELATION, "testrole3", 76937L));
		return members;
	}

	private List<OsmTag> createRelTags() {
		List<OsmTag> tags = new ArrayList<>();
		tags.add(new OsmTagImpl("name", "relation"));
		return tags;
	}

	private List<OsmTag> createWayTags() {
		List<OsmTag> tags = new ArrayList<>();
		tags.add(new OsmTagImpl("name", "way"));
		return tags;
	}

	private List<OsmTag> createNodeTags() {
		List<OsmTag> tags = new ArrayList<>();
		tags.add(new OsmTagImpl("name", "node"));
		return tags;
	}
}