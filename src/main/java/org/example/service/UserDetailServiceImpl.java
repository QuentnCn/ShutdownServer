package org.example.service;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ClientMapperService clientMapperService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = clientMapperService.selectUserName(username);
		if (user == null) throw new UsernameNotFoundException("用户名不存在!");
		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(user.getUsername(),password,
				AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
	}
}
