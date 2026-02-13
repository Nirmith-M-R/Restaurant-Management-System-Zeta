package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAuthService {
    @Test
    public void testLogin(){
        assertEquals(true, AuthService.login(2,"123"));
    }
}
