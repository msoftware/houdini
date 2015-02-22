package com.github.vbauer.houdini.service;

import com.github.vbauer.houdini.core.BasicTest;
import com.github.vbauer.houdini.model.User;
import com.github.vbauer.houdini.model.UserDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Vladislav Bauer
 */

public class ObjectConverterServiceTest extends BasicTest {

    private static final int ID = 1;
    private static final String LOGIN = "vbauer";
    private static final String PASSWORD = "password";


    @Test
    public void testShortUserInfo() {
        final User user = createUser();
        final UserDTO userDTO = converterService.convert(UserDTO.class, user);

        checkUserDTO(userDTO, false);
    }

    @Test
    public void testFullUserInfo() {
        final User user = createUser();
        final UserDTO userDTO = converterService.convert(UserDTO.class, user, true);

        checkUserDTO(userDTO, true);
    }

    @Test
    public void testShortUserInfoSet() {
        final Set<User> users = Collections.singleton(createUser());
        final Set<UserDTO> userDTOs = converterService.convert(UserDTO.class, users);

        Assert.assertEquals(1, userDTOs.size());
        checkUserDTO(userDTOs.iterator().next(), false);
    }

    @Test
    public void testShortUserInfoList() {
        final List<User> users = Collections.singletonList(createUser());
        final List<UserDTO> userDTOs = converterService.convert(UserDTO.class, users);

        Assert.assertEquals(1, userDTOs.size());
        checkUserDTO(userDTOs.iterator().next(), false);
    }

    @Test
    public void testShortUserInfoOneOrSet() {
        final Set<User> users = Collections.singleton(createUser());
        final UserDTO userDTO = (UserDTO) converterService.convertToOneOrSet(UserDTO.class, users);
        checkUserDTO(userDTO, false);
    }

    @Test
    public void testShortUserInfoOneOrList() {
        final List<User> users = Collections.singletonList(createUser());
        final UserDTO userDTO = (UserDTO) converterService.convertToOneOrList(UserDTO.class, users);
        checkUserDTO(userDTO, false);
    }


    /*
     * Helper methods.
     */

    private void checkUserDTO(final UserDTO userDTO, final boolean hasPassword) {
        Assert.assertEquals(ID, userDTO.getId());
        Assert.assertEquals(LOGIN, userDTO.getLogin());

        if (hasPassword) {
            Assert.assertEquals(PASSWORD, userDTO.getPassword());
        } else {
            Assert.assertNull(userDTO.getPassword());
        }
    }

    private User createUser() {
        return new User()
            .setId(ID)
            .setLogin(LOGIN)
            .setPassword(PASSWORD);
    }

}