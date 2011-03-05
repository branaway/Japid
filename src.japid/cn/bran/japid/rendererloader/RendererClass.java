package cn.bran.japid.rendererloader;

import java.util.Date;

import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;

public class RendererClass {
	String className;
	String sourceCode;
	long lastUpdated;
	byte[] bytecode;
	Class<? extends JapidTemplateBaseWithoutPlay> clz;
	ClassLoader cl;
	
	public ClassLoader getCl() {
		return cl;
	}
	public void setCl(ClassLoader cl) {
		this.cl = cl;
	}
	public Class<? extends JapidTemplateBaseWithoutPlay> getClz() {
		return clz;
	}
	public void setClz(Class<? extends JapidTemplateBaseWithoutPlay> clz) {
		this.clz = clz;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public long getLastUpdates() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdates) {
		this.lastUpdated = lastUpdates;
	}
	public byte[] getBytecode() {
		return bytecode;
	}
	public void setBytecode(byte[] bytecode) {
		this.bytecode = bytecode;
	}
	
	public void clear() {
		this.bytecode = null;
	}
}
