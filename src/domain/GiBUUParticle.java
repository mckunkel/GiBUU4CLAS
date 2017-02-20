package domain;

/**
 *
 * @author mkunkel
 */
public class GiBUUParticle {

	private int partId;
	private int charge;

	public GiBUUParticle(int partId, int charge) {
		this.partId = partId;
		this.charge = charge;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + charge;
		result = prime * result + partId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiBUUParticle other = (GiBUUParticle) obj;
		if (charge != other.charge)
			return false;
		if (partId != other.partId)
			return false;
		return true;
	}

}
