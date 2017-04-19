package org.platform.snail.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.platform.snail.dao.SystemDao;
import org.platform.snail.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
public class UserDetailServiceImpl implements UserDetailsService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private FilterInvocationSecurityMetadataSourceNative securityMetadataSource;
	public UserDetails loadUserByUsername(String username) {
		logger.info(username);
		try {
			logger.info("securityMetadataSource.loadResourceDefine");
			securityMetadataSource.loadResourceDefine();
		} catch (Exception e) {
			logger.error(e);
		}
		Users users=systemDao.selectUsersByAccount(username);
		logger.info(users);
		return new BasicUsers(users.getUserId(), users.getPassword(), users.getAccount(), users.getStauts().equals("1"), users.getStauts().equals("1"), users.getStauts().equals("1"), users.getStauts().equals("1"),this.getAuthorities(users.getUserId()));
	}
	private Collection<GrantedAuthority> getAuthorities(String userId) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Map<String,String>> list=systemDao.selectRoleListByUserId(userId);
		for(Map<String,String> map:list){
			authorities.add(new GrantedAuthorityImpl(map.get("ROLE"))); // 赋予一个临时权限
		}
		return authorities;
	}
}
