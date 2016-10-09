package com.bam.GESTIBANKBAM.data;

public class BAMDataFactory {
	private static BAMData bd;

	static {
		switch (BAMDataMode.BAM_MODE) {
		case BAMDataMode.BAM_FAKE:
			bd = BAMDataFake.getBAMData();
			break;
		case BAMDataMode.BAM_JPA:
		default:
			throw new UnsupportedOperationException("BAMDataMode.BAM_MODE=" + 
					BAMDataMode.BAM_MODE +
					" is not supported yet.");
		}
	};

	public static BAMData getBAMData() {
		return bd;
	}
}
