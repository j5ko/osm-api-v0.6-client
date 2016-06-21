package petko.osm.api.client.v06.api.model.capabilities;

import javax.xml.bind.annotation.XmlAttribute;

public class Api {
	private Version version;
	private Area area;
	private TracePoints tracepoints;
	private Waynodes waynodes;
	private Changesets changesets;
	private Timeout timeout;
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timeout getTimeout() {
		return timeout;
	}

	public void setTimeout(Timeout timeout) {
		this.timeout = timeout;
	}

	public void setChangesets(Changesets changesets) {
		this.changesets = changesets;
	}

	public Changesets getChangesets() {
		return changesets;
	}

	public Waynodes getWaynodes() {
		return waynodes;
	}

	public void setWaynodes(Waynodes waynodes) {
		this.waynodes = waynodes;
	}

	public TracePoints getTracepoints() {
		return tracepoints;
	}

	public void setTracepoints(TracePoints tracepoints) {
		this.tracepoints = tracepoints;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public static class Version {
		@XmlAttribute(name = "minimum", required = true)
		private float min;
		@XmlAttribute(name = "maximum", required = true)
		private float max;

		public float getMax() {
			return max;
		}

		public float getMin() {
			return min;
		}
	}

	public static class Area {
		private float maximum;

		public float getMaximum() {
			return maximum;
		}
	}

	public static class TracePoints {
		@XmlAttribute(name = "per_page")
		private int perPage;

		public int getPerPage() {
			return perPage;
		}
	}

	public static class Waynodes {
		@XmlAttribute
		private int maximum;

		public int getMaximum() {
			return maximum;
		}
	}

	public static class Changesets {
		@XmlAttribute(name = "maximum_elements")
		private int maximumElements;

		public int getMaximumElements() {
			return maximumElements;
		}
	}

	public static class Timeout {
		@XmlAttribute
		private int seconds;

		public int getSeconds() {
			return seconds;
		}
	}

	public static class Status {
		@XmlAttribute
		private String database;
		@XmlAttribute
		private String api;
		@XmlAttribute
		private String gpx;

		public String getGpx() {
			return gpx;
		}

		public String getApi() {
			return api;
		}

		public String getDatabase() {
			return database;
		}
	}
}