package tech_5dhub.mapper;

import org.mapstruct.Mapper;
import tech_5dhub.dto.UserDto;
import tech_5dhub.dto.UserRegistration;
import tech_5dhub.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    User userRegistrationToUser(UserRegistration userRegistration);
}
