package com.netcrackerTask.backend.business.service;

import com.netcrackerTask.backend.business.entity.Role;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.RoleRepository;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MailService mailService;

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }



    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());


        userRepository.save(user);
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format("Здравствуйте "
                    +user.getUsername()+".  Для активации аккаунта перейдите по ссылке"
                    +" http://localhost:8080/activate/%s",
                    user.getActivationCode());

            mailService.send(user.getEmail(), "Код активации", message);
        }

        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);

        userRepository.save(user);
        return true;
    }

//    public List<User> usergtList(Long idMin) {
//        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
}
