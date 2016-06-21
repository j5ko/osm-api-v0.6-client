package petko.osm.api.client.v06.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import petko.osm.model.facade.OsmTag;
import petko.osm.model.impl.xml.Changeset;
import petko.osm.model.impl.xml.OsmTagXml;

public class OsmClientChangesetTest extends OsmClientTestAbstract {

	@Test
	public void testCreateChangeset() {
		try {
			// create set
			List<OsmTag> additionalTags = new ArrayList<OsmTag>();
			additionalTags.add(new OsmTagXml("test_key", "Test value"));
			Long setId = this.client.createChangeset("Test user", "Test changest", additionalTags);
			Assert.assertNotNull(setId);
			Changeset setRead1 = this.client.readChangeset(setId, false);
			Assert.assertNotNull(setRead1);
			Assert.assertNotNull(setRead1.getId());
			Assert.assertNotNull(setRead1.getUid());
			Assert.assertNotNull(setRead1.getCreatedAt());
			Assert.assertNotNull(setRead1.getMinLat());
			Assert.assertNotNull(setRead1.getMaxLat());
			Assert.assertNotNull(setRead1.getTags());
			Assert.assertTrue(setRead1.getTags().size() == 3);
			// update set
			setRead1.getTags().add(new OsmTagXml("test_key2", "test value 2"));
			Long setIdUpdated = this.client.updateChangeset(setRead1);
			Assert.assertNotNull(setIdUpdated);
			Changeset setRead2 = this.client.readChangeset(setIdUpdated, false);
			Assert.assertNotNull(setRead2);
			Assert.assertNotNull(setRead2.getTags());
			Assert.assertTrue(setRead2.getTags().size() == 4);
			// close set
			this.client.closeChangeset(setRead2.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}