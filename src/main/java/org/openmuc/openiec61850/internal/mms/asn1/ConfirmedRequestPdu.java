/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.LinkedList;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;

public final class ConfirmedRequestPdu {

	public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
	protected BerIdentifier id;

	public byte[] code = null;
	public BerInteger invokeID = null;

	public ConfirmedServiceRequest confirmedServiceRequest = null;

	public ConfirmedRequestPdu() {
		id = identifier;
	}

	public ConfirmedRequestPdu(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public ConfirmedRequestPdu(BerInteger invokeID, ConfirmedServiceRequest confirmedServiceRequest) {
		id = identifier;
		this.invokeID = invokeID;
		this.confirmedServiceRequest = confirmedServiceRequest;
	}

	public int encode(BerByteArrayOutputStream berOStream, boolean explicit) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				berOStream.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			codeLength += confirmedServiceRequest.encode(berOStream, true);
			
			codeLength += invokeID.encode(berOStream, true);
			
			codeLength += BerLength.encodeLength(berOStream, codeLength);
		}

		if (explicit) {
			codeLength += id.encode(berOStream);
		}

		return codeLength;

	}

	public int decode(InputStream iStream, boolean explicit) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		int choiceDecodeLength = 0;
		BerIdentifier berIdentifier = new BerIdentifier();
		boolean decodedIdentifier = false;

		if (explicit) {
			codeLength += id.decodeAndCheck(iStream);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(iStream);

		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			if (berIdentifier.equals(BerInteger.identifier)) {
				invokeID = new BerInteger();
				subCodeLength += invokeID.decode(iStream, false);
				decodedIdentifier = false;
			}
			else {
				throw new IOException("Identifier does not macht required sequence element identifer.");
			}
		}
		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			confirmedServiceRequest = new ConfirmedServiceRequest();
			choiceDecodeLength = confirmedServiceRequest.decode(iStream, berIdentifier);
			if (choiceDecodeLength != 0) {
				decodedIdentifier = false;
				subCodeLength += choiceDecodeLength;
			}
		}
		if (subCodeLength != length.val) {
			throw new IOException("Decoded sequence has wrong length tag");

		}
		codeLength += subCodeLength;

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream berOStream = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(berOStream, false);
		code = berOStream.getArray();
	}
}

