package org.examples;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

@Component
public class SchemabasedMultiTenantConnectionProvider implements MultiTenantConnectionProvider {

	private static final long serialVersionUID = 1L;

	private DataSource datasource;
	
	private String DEFAULT_TENANT = "public";

	
	public SchemabasedMultiTenantConnectionProvider(DataSource dataSource) {
		this.datasource = dataSource;
	}
	@Override
	@SuppressWarnings("rawtypes")
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return this.datasource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		connection.setSchema(tenantIdentifier);
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		connection.setSchema(DEFAULT_TENANT);
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
