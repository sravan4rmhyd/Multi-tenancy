package org.examples;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
	private String defaultTenant ="public";

    @Override
	public String resolveCurrentTenantIdentifier() {
		String t =  SchemaContext.getSchema();
		if(t!=null){
			return t;
		} else {
			return defaultTenant;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
