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

public final class AlternateAccessSelection {

	public byte[] code = null;
	public final static class SubSeq_selectAlternateAccess {

		public final static class SubSeq_indexRange {

			public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
			protected BerIdentifier id;

			public byte[] code = null;
			public BerInteger lowIndex = null;

			public BerInteger numberOfElements = null;

			public SubSeq_indexRange() {
				id = identifier;
			}

			public SubSeq_indexRange(byte[] code) {
				id = identifier;
				this.code = code;
			}

			public SubSeq_indexRange(BerInteger lowIndex, BerInteger numberOfElements) {
				id = identifier;
				this.lowIndex = lowIndex;
				this.numberOfElements = numberOfElements;
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
					codeLength += numberOfElements.encode(berOStream, false);
					codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)).encode(berOStream);
					
					codeLength += lowIndex.encode(berOStream, false);
					codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)).encode(berOStream);
					
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
					if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
						lowIndex = new BerInteger();
						subCodeLength += lowIndex.decode(iStream, false);
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
					if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
						numberOfElements = new BerInteger();
						subCodeLength += numberOfElements.decode(iStream, false);
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

		public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
		protected BerIdentifier id;

		public byte[] code = null;
		public BerVisibleString component = null;

		public BerInteger index = null;

		public SubSeq_indexRange indexRange = null;

		public BerNull allElements = null;

		public AlternateAccess alternateAccess = null;

		public SubSeq_selectAlternateAccess() {
			id = identifier;
		}

		public SubSeq_selectAlternateAccess(byte[] code) {
			id = identifier;
			this.code = code;
		}

		public SubSeq_selectAlternateAccess(BerVisibleString component, BerInteger index, SubSeq_indexRange indexRange, BerNull allElements, AlternateAccess alternateAccess) {
			id = identifier;
			this.component = component;
			this.index = index;
			this.indexRange = indexRange;
			this.allElements = allElements;
			this.alternateAccess = alternateAccess;
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
				codeLength += alternateAccess.encode(berOStream, true);
				
				codeLength += allElements.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 3)).encode(berOStream);
				
				codeLength += indexRange.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)).encode(berOStream);
				
				codeLength += index.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)).encode(berOStream);
				
				codeLength += component.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)).encode(berOStream);
				
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
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
					component = new BerVisibleString();
					subCodeLength += component.decode(iStream, false);
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
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
					index = new BerInteger();
					subCodeLength += index.decode(iStream, false);
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
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)) {
					indexRange = new SubSeq_indexRange();
					subCodeLength += indexRange.decode(iStream, false);
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
					allElements = new BerNull();
					subCodeLength += allElements.decode(iStream, false);
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
				if (berIdentifier.equals(AlternateAccess.identifier)) {
					alternateAccess = new AlternateAccess();
					subCodeLength += alternateAccess.decode(iStream, false);
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

	public SubSeq_selectAlternateAccess selectAlternateAccess = null;

	public BerVisibleString component = null;

	public BerInteger index = null;

	public IndexRangeSeq indexRange = null;

	public BerNull allElements = null;

	public AlternateAccessSelection() {
	}

	public AlternateAccessSelection(byte[] code) {
		this.code = code;
	}

	public AlternateAccessSelection(SubSeq_selectAlternateAccess selectAlternateAccess, BerVisibleString component, BerInteger index, IndexRangeSeq indexRange, BerNull allElements) {
		this.selectAlternateAccess = selectAlternateAccess;
		this.component = component;
		this.index = index;
		this.indexRange = indexRange;
		this.allElements = allElements;
	}

	public int encode(BerByteArrayOutputStream berOStream, boolean explicit) throws IOException {
		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				berOStream.write(code[i]);
			}
			return code.length;

		}
		int codeLength = 0;
		if (allElements != null) {
			codeLength += allElements.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 4)).encode(berOStream);
			return codeLength;

		}
		
		if (indexRange != null) {
			codeLength += indexRange.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 3)).encode(berOStream);
			return codeLength;

		}
		
		if (index != null) {
			codeLength += index.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)).encode(berOStream);
			return codeLength;

		}
		
		if (component != null) {
			codeLength += component.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)).encode(berOStream);
			return codeLength;

		}
		
		if (selectAlternateAccess != null) {
			codeLength += selectAlternateAccess.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)).encode(berOStream);
			return codeLength;

		}
		
		throw new IOException("Error encoding BerChoice: No item in choice was selected.");
	}

	public int decode(InputStream iStream, BerIdentifier berIdentifier) throws IOException {
		int codeLength = 0;
		int choiceDecodeLength = 0;
		BerIdentifier passedIdentifier = berIdentifier;
		if (berIdentifier == null) {
			berIdentifier = new BerIdentifier();
			codeLength += berIdentifier.decode(iStream);
		}
		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)) {
			selectAlternateAccess = new SubSeq_selectAlternateAccess();
			codeLength += selectAlternateAccess.decode(iStream, false);
			return codeLength;
		}

		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
			component = new BerVisibleString();
			codeLength += component.decode(iStream, false);
			return codeLength;
		}

		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
			index = new BerInteger();
			codeLength += index.decode(iStream, false);
			return codeLength;
		}

		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 3)) {
			indexRange = new IndexRangeSeq();
			codeLength += indexRange.decode(iStream, false);
			return codeLength;
		}

		if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 4)) {
			allElements = new BerNull();
			codeLength += allElements.decode(iStream, false);
			return codeLength;
		}

		if (passedIdentifier != null) {
			return 0;
		}
		throw new IOException("Error decoding BerChoice: Identifier matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream berOStream = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(berOStream, false);
		code = berOStream.getArray();
	}
}

