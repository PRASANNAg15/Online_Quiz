package com.example.OnlineQuiz.Service;

import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Entity.UserEntity;
import com.example.OnlineQuiz.Repository.UserRepository;
import com.example.OnlineQuiz.UpdateDto.UserUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public UserUpdateDto update(long id, UserUpdateDto dto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));


        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        UserEntity saved=userRepository.save(userEntity);
        return modelMapper.map(saved,UserUpdateDto.class);
    }

    public UserDto createUser(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userEntity.setName(dto.getName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(dto.getRole());
        UserEntity saved=userRepository.save(userEntity);
        return modelMapper.map(saved,UserDto.class);
    }

    public List<UserDto> findAll() {
        List<UserEntity> users=userRepository.findAll();
        List<UserDto> filtered=users.stream().map((user)->modelMapper.map(user,UserDto.class)).toList();
        return filtered;
    }

    public UserDto UserById(long id) {
        UserEntity user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        return modelMapper.map(user,UserDto.class);
    }

    public void  deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
