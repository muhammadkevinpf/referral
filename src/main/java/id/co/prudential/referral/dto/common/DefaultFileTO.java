package id.co.prudential.referral.dto.common;

public class DefaultFileTO {
	private String fileName;
	private String fileType;
	private byte[] blobValue;

	public DefaultFileTO() {  

	}

	public DefaultFileTO(String fileName, String fileType, byte[] blobValue) {
		this.fileName = fileName;
		this.blobValue = blobValue;
		this.fileType = fileType;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}

}
