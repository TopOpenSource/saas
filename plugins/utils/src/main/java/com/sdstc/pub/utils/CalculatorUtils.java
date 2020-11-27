package com.sdstc.pub.utils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorUtils {
	public static String calculator(String express) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine SE = manager.getEngineByName("js");
		return SE.eval(express).toString();
	}
}
