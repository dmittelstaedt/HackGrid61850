/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerIdentifier;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.types.BerInteger;

public final class InitiateRequestPdu {

	public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS,
			BerIdentifier.CONSTRUCTED, 16);
	protected BerIdentifier id;

	public byte[] code = null;
	public BerInteger localDetailCalling = null;

	public BerInteger proposedMaxServOutstandingCalling = null;

	public BerInteger proposedMaxServOutstandingCalled = null;

	public BerInteger proposedDataStructureNestingLevel = null;

	public InitRequestDetail mmsInitRequestDetail = null;

	public InitiateRequestPdu() {
		id = identifier;
	}

	public InitiateRequestPdu(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public InitiateRequestPdu(BerInteger localDetailCalling, BerInteger proposedMaxServOutstandingCalling,
			BerInteger proposedMaxServOutstandingCalled, BerInteger proposedDataStructureNestingLevel,
			InitRequestDetail mmsInitRequestDetail) {
		id = identifier;
		this.localDetailCalling = localDetailCalling;
		this.proposedMaxServOutstandingCalling = proposedMaxServOutstandingCalling;
		this.proposedMaxServOutstandingCalled = proposedMaxServOutstandingCalled;
		this.proposedDataStructureNestingLevel = proposedDataStructureNestingLevel;
		this.mmsInitRequestDetail = mmsInitRequestDetail;
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
			codeLength += mmsInitRequestDetail.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4))
					.encode(berOStream);

			if (proposedDataStructureNestingLevel != null) {
				codeLength += proposedDataStructureNestingLevel.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 3))
						.encode(berOStream);
			}

			codeLength += proposedMaxServOutstandingCalled.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2))
					.encode(berOStream);

			codeLength += proposedMaxServOutstandingCalling.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1))
					.encode(berOStream);

			if (localDetailCalling != null) {
				codeLength += localDetailCalling.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0))
						.encode(berOStream);
			}

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
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
				localDetailCalling = new BerInteger();
				subCodeLength += localDetailCalling.decode(iStream, false);
				decodedIdentifier = false;
			}
		}
		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
				proposedMaxServOutstandingCalling = new BerInteger();
				subCodeLength += proposedMaxServOutstandingCalling.decode(iStream, false);
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
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
				proposedMaxServOutstandingCalled = new BerInteger();
				subCodeLength += proposedMaxServOutstandingCalled.decode(iStream, false);
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
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 3)) {
				proposedDataStructureNestingLevel = new BerInteger();
				subCodeLength += proposedDataStructureNestingLevel.decode(iStream, false);
				decodedIdentifier = false;
			}
		}
		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)) {
				mmsInitRequestDetail = new InitRequestDetail();
				subCodeLength += mmsInitRequestDetail.decode(iStream, false);
				decodedIdentifier = false;
			}
			else {
				throw new IOException("Identifier does not macht required sequence element identifer.");
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
