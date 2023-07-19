package by.htp.ex.service.impl;

public enum DirectoryName {

	HTML("uploaded-files/html/"),
	IMAGES("uploaded-files/images/");

	private String dir;

	DirectoryName(String dir) {
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}
}
