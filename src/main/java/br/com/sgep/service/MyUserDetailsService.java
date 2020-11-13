package br.com.sgep.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String usuarioRecebido) throws UsernameNotFoundException {

        UserDetails theUser = User.withUsername("admin")
                        .password("admin").roles("ADMIN").build();
		
			return theUser;	
	}

}
