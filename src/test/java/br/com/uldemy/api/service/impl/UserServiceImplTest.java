package br.com.uldemy.api.service.impl;

import br.com.uldemy.api.model.User;
import br.com.uldemy.api.model.dto.UserDTO;
import br.com.uldemy.api.repositories.UserRepsitory;
import br.com.uldemy.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID                   = 1;
    public static final String NAME                  = "Gabriel";
    public static final String EMAIL                 = "gabriel@hotmail.com";
    public static final String PASSWORD              = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepsitory repsitory;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        when(repsitory.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNoFoundException(){
        when(repsitory.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
           service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}