package net.collaud.fablab.manager.service.util.recaptcha;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Gaétan on 21/04/2017.
 */
@Slf4j
@UtilityClass
public class PriceUtil {

	public static double evaluatePrice(String expression, double amount) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String equation = expression.replaceAll("x", Double.valueOf(amount).toString());
		try {
			return Double.valueOf(engine.eval(equation).toString());
		} catch (Exception e) {
			throw new IllegalArgumentException("Expression '" + equation + "' cannot be resolved");
		}
	}

}
