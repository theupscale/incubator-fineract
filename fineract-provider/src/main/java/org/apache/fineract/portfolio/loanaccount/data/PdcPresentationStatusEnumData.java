package org.apache.fineract.portfolio.loanaccount.data;

public class PdcPresentationStatusEnumData {

	private final Long id;
	private final String code;
	private final String value;

	private final boolean unused;
	private final boolean presented;
	private final boolean presentedAndCleared;
	private final boolean presentedAndDeclined;

	public PdcPresentationStatusEnumData(final Long id, final String code, final String value) {
		this.id = id;
		this.code = code;
		this.value = value;

		this.unused = Long.valueOf(1).equals(this.id);
		this.presented = Long.valueOf(2).equals(this.id);
		this.presentedAndCleared = Long.valueOf(3).equals(this.id);
		this.presentedAndDeclined = Long.valueOf(4).equals(this.id);
	}

	public Long id() {
		return this.id;
	}

	public String getCode() {
		return this.code;
	}

	public String getValue() {
		return this.value;
	}

	public boolean isUnused() {
		return this.unused;
	}

	public boolean isPresented() {
		return this.presented;
	}

	public boolean isPresentedAndCleared() {
		return this.presentedAndCleared;
	}

	public boolean isPresentedAndDeclined() {
		return this.presentedAndDeclined;
	}
}
