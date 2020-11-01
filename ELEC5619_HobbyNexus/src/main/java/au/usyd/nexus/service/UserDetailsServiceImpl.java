package au.usyd.nexus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import au.usyd.nexus.dao.LoginDAO;
import au.usyd.nexus.domain.User;


public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private LoginDAO loginDAO;

	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = loginDAO.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
    }

	
}
