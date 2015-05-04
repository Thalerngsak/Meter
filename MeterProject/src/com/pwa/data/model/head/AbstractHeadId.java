package com.pwa.data.model.head;

/**
 * AbstractDetailId entity provides the base persistence definition of the DetailId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractHeadId implements java.io.Serializable {

	// Fields

	private String id;

	// Constructors
	public AbstractHeadId() {
	}
	/**
	 * @param id
	 */
	public AbstractHeadId(String id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractHeadId))
			return false;
		AbstractHeadId castOther = (AbstractHeadId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())
				))
				 ;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		//result = 37 * result + (getZn() == null ? 0 : this.getZn().hashCode());
		//result = 37 * result + (getCustcode() == null ? 0 : this.getCustcode().hashCode());
		//result = 37 * result + (getRte() == null ? 0 : this.getRte().hashCode());
		//result = 37 * result + (getAssigndate() == null ? 0 : this.getAssigndate().hashCode());
		return result;
	}

}