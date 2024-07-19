package mockito;

import io.github.fubanko.fubanko.controller.DashboardController;
import io.github.fubanko.fubanko.controller.dto.DashboardResponse;
import io.github.fubanko.fubanko.model.Status;
import io.github.fubanko.fubanko.model.Transaction;
import io.github.fubanko.fubanko.model.User;
import io.github.fubanko.fubanko.service.TransactionService;
import io.github.fubanko.fubanko.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserDashboardTests {

    @Mock
    private UserService userService;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetUserDashboard() {

        String username = "testuser";
        String username_2 = "testuser_2";

        User mockUser = new User();
        mockUser.setUsername(username);
        User mockUser2 = new User();
        mockUser2.setUsername(username_2);

        Transaction transaction = new Transaction();

        transaction.setDateTime(LocalDateTime.now());
        transaction.setPayer(mockUser);
        transaction.setRecipient(mockUser2);
        transaction.setStatus(Status.SUCCESS);
        transaction.setAmount(BigDecimal.TEN);
        transaction.setMessage("Test message");

        List<Transaction> transactions = List.of(transaction);

        when(userService.findByUsername(username)).thenReturn(mockUser);
        when(transactionService.findUserTransactions(username)).thenReturn(transactions);

        ResponseEntity<DashboardResponse> response = dashboardController.getUserDashboard(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        var dashboardResponse = response.getBody();
        assert dashboardResponse != null;
        assertEquals(username, dashboardResponse.username());
        assertEquals(1, dashboardResponse.transactions().size());
        var transactionDto = dashboardResponse.transactions().getFirst();

        assertEquals(transaction.getDateTime(), transactionDto.dateTime());
        assertEquals(transaction.getAmount(), transactionDto.amount());
        assertEquals(transaction.getPayer().getUsername(), transactionDto.from());
        assertEquals(transaction.getRecipient().getUsername(), transactionDto.to());
        assertEquals(transaction.getStatus(), transactionDto.status());
        assertEquals(transaction.getMessage(), transactionDto.description());



    }

}
