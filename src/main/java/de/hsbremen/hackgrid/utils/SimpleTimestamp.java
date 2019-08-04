package de.hsbremen.hackgrid.utils;

import java.util.Date;

public class SimpleTimestamp {
	
	private byte[] value;
	
	/**
	 * Returns the value as the number of seconds since epoch 1970-01-01 00:00:00 UTC
	 * 
	 * @return the number of seconds since epoch 1970-01-01 00:00:00 UTC
	 */
	private long getSecondsSinceEpoch() {
		return ((0xffL & value[0]) << 24 | (0xffL & value[1]) << 16 | (0xffL & value[2]) << 8 | (0xffL & value[3]));
	}
	
	/**
	 * The attribute FractionOfSecond shall be the fraction of the current second when the value of the TimeStamp has
	 * been determined. The fraction of second shall be calculated as
	 * <code>(SUM from I = 0 to 23 of bi*2**–(I+1) s).</code>
	 * 
	 * NOTE 1 The resolution is the smallest unit by which the time stamp is updated. The 24 bits of the integer
	 * provides 1 out of 16777216 counts as the smallest unit; calculated by 1/2**24 which equals approximately 60 ns.
	 * 
	 * NOTE 2 The resolution of a time stamp may be 1/2**1 (= 0,5 s) if only the first bit is used; or may be 1/2**2 (=
	 * 0,25 s) if the first two bits are used; or may be approximately 60 ns if all 24 bits are used. The resolution
	 * provided by an IED is outside the scope of this standard.
	 * 
	 * @return the fraction of seconds
	 */
	private int getFractionOfSecond() {
		return ((0xff & value[4]) << 16 | (0xff & value[5]) << 8 | (0xff & value[6]));
	}
	
	public void setDate(Date date) {
		if (date == null) {
			value = new byte[8];
		}

		int secondsSinceEpoch = (int) (date.getTime() / 1000L);
		int fractionOfSecond = (int) ((date.getTime() % 1000L) / 1000.0 * (1 << 24));

		// 0x8a = time accuracy of 10 and LeapSecondsKnown = true, ClockFailure
		// = false, ClockNotSynchronized = false
		value = new byte[] { (byte) ((secondsSinceEpoch >> 24) & 0xff), (byte) ((secondsSinceEpoch >> 16) & 0xff),
				(byte) ((secondsSinceEpoch >> 8) & 0xff), (byte) (secondsSinceEpoch & 0xff),
				(byte) ((fractionOfSecond >> 16) & 0xff), (byte) ((fractionOfSecond >> 8) & 0xff),
				(byte) (fractionOfSecond & 0xff), (byte) 0x8a };

	}
	
	public void setValue(byte[] value) {
		if (value == null) {
			this.value = new byte[8];
		}
		this.value = value;
	}
	
	public Date getDate() {
		if (value == null || value.length == 0) {
			return null;
		}
		long time = getSecondsSinceEpoch() * 1000L + (long) (((float) getFractionOfSecond()) / (1 << 24) * 1000 + 0.5);
		return new Date(time);
	}

	public byte[] getValue() {
		return value;
	}
	
	/**
	 * Sets Timestamp to current time
	 */
	public void setCurrentTime() {
		setDate(new Date());
	}

}
