/**
 * Copyright 2020 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package next.factory;

import core.jdbc.JdbcTemplate;

public class JdbcTemplateFactory {
	private static class StaticJdbcTemplate {
		public static final JdbcTemplate JDBC_TEMPLATE = new JdbcTemplate();
	}

	public static JdbcTemplate getJdbcTemplate() {
		return StaticJdbcTemplate.JDBC_TEMPLATE;
	}
}