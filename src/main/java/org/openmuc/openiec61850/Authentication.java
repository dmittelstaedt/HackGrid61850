package org.openmuc.openiec61850;

/**
 * This class represents the model of authentication tag from the icd file.
 * 
 * @author david
 *
 */
public final class Authentication extends ModelNode {
	
	private ObjectReference objectReference;
	private boolean certificate;
	private boolean none;
	private boolean password;
	private boolean strong;
	private boolean weak;
	
	public Authentication(ObjectReference objectReference, boolean certificate, boolean none, boolean password, boolean strong, boolean weak) {
		this.objectReference = objectReference;
		this.certificate = certificate;
		this.none = none;
		this.password = password;
		this.strong = strong;
		this.weak = weak;
	}

	@Override
	public ModelNode copy() {
		return new Authentication(objectReference, certificate, none, password, strong, weak);
	}

}
