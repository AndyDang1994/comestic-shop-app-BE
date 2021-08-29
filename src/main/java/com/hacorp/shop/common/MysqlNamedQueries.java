/**
 * 
 */
package com.hacorp.shop.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shds01
 *
 */
public class MysqlNamedQueries extends HashMap<String, String> {
	private static final long serialVersionUID = 1060286526025776068L;

	public MysqlNamedQueries(Map<String, String> namedQueries) {
		super(namedQueries);
	}

}
