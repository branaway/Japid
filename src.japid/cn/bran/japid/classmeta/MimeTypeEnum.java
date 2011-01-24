package cn.bran.japid.classmeta;

public enum MimeTypeEnum {
	html("text/html"),
	txt("text/plain"),
	xml("text/xml"), // not using application/xml to be consistent with Play's way.
	json("application/json"),
	css("text/css"),
	js("application/x-javascript"),
	xls("application/excel"),
	;
	
	private String header;
	
	MimeTypeEnum(String header) {
		this.header = header;
	}
	
	public static String getHeader(String ext) {
		if (ext.startsWith("."))
			ext = ext.substring(1);
		try {
			MimeTypeEnum valueOf = MimeTypeEnum.valueOf(ext);
			return valueOf.header + "; charset=utf-8";
		} catch (Exception e) {
			return null;
		}
	}
}
