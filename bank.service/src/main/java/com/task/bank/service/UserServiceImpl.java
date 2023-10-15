package com.task.bank.service;

import com.task.bank.dto.UserDto;
import com.task.bank.entity.UserEntity;
import com.task.bank.repository.UserRepository;
import com.task.bank.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long registerUser(UserDto userDto) {
        UserEntity entity = new UserEntity();

        entity.setName(userDto.getName());
        entity.setLastName(userDto.getLastName());
        entity.setAge(userDto.getAge());

        userRepository.save(entity);

        return entity.getId();
    }
}
