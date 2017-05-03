package net.collaud.fablab.manager.service.util;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.type.PriceUnit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DecimalFormat;
import java.time.Duration;

/**
 * Created by Gaétan on 21/04/2017.
 */
@Slf4j
@UtilityClass
public class PriceUtil {

	public static double evaluatePrice(String expression, double amount) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String equation = expression.replaceAll("amount", Double.valueOf(amount).toString());
		try {
			String result = engine.eval(equation).toString();
			return Double.valueOf(result);
		} catch (Exception e) {
			throw new IllegalArgumentException("Expression '" + equation + "' cannot be resolved");
		}
	}

	public static String prettyPrintValue(double amount, PriceUnit unit) {
		switch (unit) {
			case HOUR:
				Duration duration = Duration.ofSeconds((long) (amount * 3600));
				long hour = Math.ceil(duration.getSeconds() / 3600.0);
				long min = Math.round(duration.minusHours(hour).getSeconds() / 60.0);
				return new StringBuilder()
						.append(Long.valueOf(hour).toString()).append("h ")
						.append(Long.valueOf(min).toString()).append("min ")
						.toString();
			default:
				DecimalFormat df = new DecimalFormat("#.##");
				return df.format(amount) + unit.getTextUnit();
		}
	}

}
