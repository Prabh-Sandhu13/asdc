package CSCI5308.GroupFormationTool.RepositoryTest;

import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.ForgotPasswordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ForgotPasswordRepositoryTest {

    User user = new User();
    private ForgotPasswordRepository forgotPasswordRepository;

    @Test
    public void addTokenTest() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);

        when(forgotPasswordRepository.addToken(user, "sampleToken")).thenReturn(true);
        assertEquals(true, forgotPasswordRepository.addToken(user, "sampleToken"));
    }

    @Test
    public void getTokenTest() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.getToken(user)).thenReturn(null);
        assertEquals(null, forgotPasswordRepository.getToken(user));
    }

    @Test
    public void updatePasswordTest() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.updatePassword(user, "encryptedPassword")).thenReturn(true);
        assertEquals(true, forgotPasswordRepository.updatePassword(user, "encryptedPassword"));
    }

    @Test
    public void updateTokenTest() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.updateToken(user, "newToken")).thenReturn(true);
        assertEquals(true, forgotPasswordRepository.updateToken(user, "newToken"));
    }

    @Test
    public void deleteTokenTest() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.deleteToken(user, "token")).thenReturn(true);
        assertEquals(true, forgotPasswordRepository.deleteToken(user, "token"));
    }

    @Test
    public void getUserIdTest() {

        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.getUserId(user)).thenReturn(null);
        assertEquals(null, forgotPasswordRepository.getUserId(user));

    }

    @Test
    public void getEmailByTokenTest() {

        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        when(forgotPasswordRepository.getEmailByToken(user, "token")).thenReturn(null);
        assertEquals(null, forgotPasswordRepository.getEmailByToken(user, "token"));

    }

}
