package com.iaec.guichetautomatique.security;

import com.iaec.guichetautomatique.entities.User;
import com.iaec.guichetautomatique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class iGuichetUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.getByLogin(login);

        if(user != null){
            return new iGuichetUserDetails(user);
        }
        throw new UsernameNotFoundException("Aucun utilisateur n'a été trouvé avec ce login: " + login);

    }
}
