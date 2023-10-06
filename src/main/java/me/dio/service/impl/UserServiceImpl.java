package me.dio.service.impl;

import jakarta.websocket.server.ServerEndpoint;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())){
            throw new IllegalArgumentException("This User Number already exists:");
        }
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User byId = findById(id);
        byId.setName(user.getName());
        return userRepository.save(byId);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
