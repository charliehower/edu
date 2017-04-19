package org.platform.snail.security;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public interface FilterInvocationSecurityMetadataSourceNative {
	public void loadResourceDefine() throws Exception;
	public Collection<ConfigAttribute> getAllConfigAttributes();
}
