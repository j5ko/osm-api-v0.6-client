package petko.osm.api.client.v06.impl;

import org.junit.Assert;
import org.junit.Test;

import petko.osm.api.client.v06.api.OsmClientException;
import petko.osm.api.client.v06.api.model.capabilities.Api.Area;
import petko.osm.api.client.v06.api.model.capabilities.Api.Changesets;
import petko.osm.api.client.v06.api.model.capabilities.Api.Status;
import petko.osm.api.client.v06.api.model.capabilities.Api.Timeout;
import petko.osm.api.client.v06.api.model.capabilities.Api.TracePoints;
import petko.osm.api.client.v06.api.model.capabilities.Api.Version;
import petko.osm.api.client.v06.api.model.capabilities.Api.Waynodes;
import petko.osm.api.client.v06.api.model.capabilities.Capatibilities;
import petko.osm.api.client.v06.api.model.capabilities.Policy.Imagery;
import petko.osm.model.facade.OsmDataStore;

public class OsmClientTest extends OsmClientTestAbstract {

	@Test
	public void testMap() {
		try {
			OsmDataStore data = this.client.map(14.7f, 47.8f, 14.9f, 48f);
			System.out.println(data.getNodes().get(0).getUser());
			System.out.println(data.getNodes().get(0).getTags().get(0).getValue());
		} catch (OsmClientException e) {
			Assert.fail();
		}
	}

	@Test
	public void testGetCapabilities() {
		try {
			Capatibilities capabilities = this.client.getCapabilities();
			Assert.assertNotNull(capabilities);
			Assert.assertNotNull(capabilities.getVersion());
			Assert.assertNotNull(capabilities.getAttribution());
			Assert.assertNotNull(capabilities.getCopyright());
			Assert.assertNotNull(capabilities.getGenerator());
			Assert.assertNotNull(capabilities.getLicense());
			// api.version
			Version version = capabilities.getApi().getVersion();
			Assert.assertNotNull(version.getMax());
			Assert.assertNotNull(version.getMin());
			// api.area
			Area area = capabilities.getApi().getArea();
			Assert.assertNotNull(area.getMaximum());
			// api.tracepoints
			TracePoints tp = capabilities.getApi().getTracepoints();
			Assert.assertTrue(tp.getPerPage() > 0);
			// api.Waynodes
			Waynodes wn = capabilities.getApi().getWaynodes();
			Assert.assertTrue(wn.getMaximum() > 0);
			// api.Changesets
			Changesets cs = capabilities.getApi().getChangesets();
			Assert.assertTrue(cs.getMaximumElements() > 0);
			// api.timeout
			Timeout to = capabilities.getApi().getTimeout();
			Assert.assertTrue(to.getSeconds() > 0);
			// api.status
			Status status = capabilities.getApi().getStatus();
			Assert.assertNotNull(status.getApi());
			Assert.assertNotNull(status.getDatabase());
			Assert.assertNotNull(status.getGpx());
			// api.policy
			Imagery imagery = capabilities.getPolicy().getImagery();
			Assert.assertNotNull(imagery);
			Assert.assertNotNull(imagery.getBlacklists());
			Assert.assertTrue(imagery.getBlacklists().size() > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}