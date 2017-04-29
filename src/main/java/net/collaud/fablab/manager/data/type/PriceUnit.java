package net.collaud.fablab.manager.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Gaétan on 21/04/2017.
 */
@Getter
@AllArgsConstructor
public enum PriceUnit {
	HOUR("h"),
	GRAMME("g");
	private final String textUnit;
}
