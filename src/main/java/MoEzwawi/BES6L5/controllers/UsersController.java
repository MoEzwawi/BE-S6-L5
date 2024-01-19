package MoEzwawi.BES6L5.controllers;

import MoEzwawi.BES6L5.entities.User;
import MoEzwawi.BES6L5.payloads.ResponseDTO;
import MoEzwawi.BES6L5.payloads.UserRequestDTO;
import MoEzwawi.BES6L5.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0")int pageNumber,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String orderBy){
        return usersService.getUsers(pageNumber, size, orderBy);
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable long id){
        return usersService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status code 201
    public ResponseDTO save(@RequestBody UserRequestDTO body){
        User newUser = usersService.save(body);
        return new ResponseDTO(newUser.getUserId());
    }
    @PutMapping("/{id}")
    public User updateUserInfo(@PathVariable long id, @RequestBody UserRequestDTO partialBody){
        return this.usersService.updateUser(id,partialBody);
    }
    @PostMapping("/{userId}/upload")
    public User uploadProfilePic(@RequestParam("avatar") MultipartFile file, @PathVariable long userId) throws IOException {
        return usersService.updateProfilePic(userId,file);
    }
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable long userId) {
        usersService.deleteById(userId);
    }
}
