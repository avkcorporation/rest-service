package ua.avk.rest.web.exceptions;

public enum Error{
	NOT_FOUND(4040, "Not Found."),
	SERVER_ERROR(5001, "Server error during operation.");

	private final int code;
	private final String description;

	private Error(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}