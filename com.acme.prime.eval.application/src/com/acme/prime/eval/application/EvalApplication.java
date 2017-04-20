package com.acme.prime.eval.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.acme.prime.eval.api.Eval;

import osgi.enroute.debug.api.Debug;

@Component(service = EvalApplication.class, property = { Debug.COMMAND_SCOPE + "=eval",
		Debug.COMMAND_FUNCTION + "=eval" }, name = "com.acme.prime.eval")
public class EvalApplication {

	private Eval eval;

	public double eval(String m) throws Exception {
		return eval.eval(m);
	}

	@Reference
	void setEval(Eval eval) {
		this.eval = eval;
	}
}
