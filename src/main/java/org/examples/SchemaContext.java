package org.examples;

public class SchemaContext {
	private static ThreadLocal<String> schemaContext = new ThreadLocal<String>();
	public static String getSchema()
	{
		return schemaContext.get();
	}
	public static void setSchema(String schemaName)
	{
		schemaContext.set(schemaName);
	}
}
