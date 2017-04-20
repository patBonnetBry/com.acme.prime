package com.acme.prime.eval.provider;

import org.osgi.service.component.annotations.Component;

import com.acme.prime.eval.api.Eval;

import osgi.enroute.debug.api.Debug;
import parsii.eval.Parser;

/**
 *
 */
@Component(name = "com.acme.prime.eval", property = { Debug.COMMAND_SCOPE + "=test", Debug.COMMAND_FUNCTION + "=eval" })
public class EvalImpl implements Eval {

	@Override
	public double eval(String expression) throws Exception {
		return Parser.parse(expression).evaluate();
	}	

}

/*
 * WARNING about the "*" character used as an operator : When debugging i
 * checked that the regex is correct but when using an
 * org.apache.felix.gogo.shell bundle in order to call this method, in a
 * debugging phase, i observed that expecting an expression with the "*"
 * character as the 'operator' implies to input is as "\*", i.e. to escape it.
 * 
 * The other 'operator' characters, "+", "-", "/" can be inputed as expected ->
 * correct
 * 
 * While using parsii, the expression is tokenized, so some other
 * non-alphanumeric characters are expected, like "(" and ")". These characters
 * cause also problem to the shell, they must be escaped
 * 
 * So, if we expect to input the following command :
 * 
 * test:eval sin(pi)
 * 
 * we must instead input :
 * 
 * test:eval sin\(pi\)
 * 
 * TODO : try to find a solution to avoid having to escape the special
 * characters when inputing the command string with this shell
 */
