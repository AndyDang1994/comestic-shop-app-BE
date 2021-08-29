package com.hacorp.shop.configuration;

import java.sql.Types;

import org.hibernate.type.StandardBasicTypes;

/**
 * @author shds01
 *
 */
public class MysqlDialect extends org.hibernate.dialect.MySQLDialect {

	public MysqlDialect() {
		registerColumnType(Types.VARCHAR, 200, "nvarchar2($l)");
		registerColumnType(Types.VARCHAR, 255, "nvarchar2($l)");
		registerColumnType(Types.VARCHAR, 4000, "nvarchar2($l)");
		registerColumnType(Types.VARCHAR, "nvarchar2(max)");
		registerColumnType(Types.DECIMAL, "number($p,$s)");
		
		registerHibernateType(Types.DECIMAL, "big_decimal");
		registerHibernateType(Types.NCLOB, StandardBasicTypes.CLOB.getName());
		registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
		registerColumnType(Types.LONGNVARCHAR, "NUMBER");
	}
}
