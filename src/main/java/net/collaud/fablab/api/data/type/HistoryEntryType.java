/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.collaud.fablab.api.data.type;

/**
 *
 * @author Gaetan Collaud
 */
public enum HistoryEntryType {

	PAYMENT("payment"),
	USAGE("usage"),
	SUBSCRIPTION("subscription");
	private final String css;

	private HistoryEntryType(String css) {
		this.css = css;
	}

	public String getCss() {
		return css;
	}

}
