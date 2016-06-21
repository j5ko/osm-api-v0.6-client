package petko.osm.api.client.v06.api.model.capabilities;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Policy {
	private Imagery imagery;

	public void setImagery(Imagery imagery) {
		this.imagery = imagery;
	}

	public Imagery getImagery() {
		return imagery;
	}

	public static class Imagery {
		@XmlElement(name = "blacklist")
		private List<Blacklist> blacklists;

		public List<Blacklist> getBlacklists() {
			return blacklists;
		}
	}

	public static class Blacklist {
		@XmlAttribute
		private String regex;

		public String getRegex() {
			return regex;
		}
	}
}