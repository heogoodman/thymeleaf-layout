package com.basic.myspringboot.service;

import com.basic.myspringboot.repository.UserRepository;
import com.basic.myspringboot.dto.UserReqDto;
import com.basic.myspringboot.dto.UserResDto;
import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    public UserResDto saveUser(UserReqDto userReqDto) {
        //reqDto => entity
        User user = modelMapper.map(userReqDto, User.class);
        User savedUser = userRepository.save(user);
        //entity => resDto
        return modelMapper.map(savedUser, UserResDto.class);
    }

    @Transactional(readOnly = true)
    public UserResDto getUserById(Long id) {
        User user = userRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(user, UserResDto.class);
    }

    public UserResDto updateUser(String email, UserReqDto userReqDto) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        //setter method 호출
        existUser.setName(userReqDto.getName());
        return modelMapper.map(existUser, UserResDto.class);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
    @Transactional(readOnly = true)
    public List<UserResDto> getUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResDto> userResDtoList = userList.stream() //Stream<User>
                .map(user -> modelMapper.map(user, UserResDto.class))//Stream<UserResDto>
                .collect(toList());//List<UserResDto>
        return userResDtoList;
    }


}
