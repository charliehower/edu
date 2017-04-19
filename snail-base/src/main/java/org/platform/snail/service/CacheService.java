package org.platform.snail.service;

public interface CacheService {
	
	public abstract Object get(String key);
	public abstract void put(String key,Object value);
	public abstract boolean containsKey(String key);
	
	public abstract void clear();
	
	public abstract void init();
}
