package model;

public class FileField {
	
	private String name;
	private String type;
	private int length;
	private boolean primaryKey;
	private String displayName = null;
	
	public FileField(String name, String type, int length, boolean primaryKey) {
		this.name = name;
		this.type = type;
		this.length = length;
		this.primaryKey = primaryKey;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isPrimaryKey()
	{
		return primaryKey;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Override
	public String toString() {
		if (displayName != null) return displayName;
		return name;
	}

	
}
