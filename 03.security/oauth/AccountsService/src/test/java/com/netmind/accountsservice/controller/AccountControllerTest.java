package com.netmind.accountsservice.controller;

import com.netmind.accountsservice.model.Account;
import com.netmind.accountsservice.persistence.AccountRepository;
import com.netmind.accountsservice.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
//@ComponentScan("package com.netmind.accountsservice.services")
@Import(AccountService.class)
class AccountControllerTest {
    @BeforeEach
    public void setUp() {
        Account cuenta = new Account(1L, "Personal", null, 1000, 1L, null);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(cuenta));

        Mockito.when(repository.save(cuenta))
                .thenReturn(cuenta);

        Mockito.doNothing().when(repository).delete(Mockito.any());
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountRepository repository;

    @Test
    void givenAnAccount_WhenAddMoney_thenAccepted() throws Exception {
        mvc.perform(put("/accounts/addmoney/1?amount=10&ownerId=1")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.balance", is(1010)));
    }

    @Test
    void givenAnAccount_WhenWithdraw_thenAccepted() throws Exception {
        mvc.perform(put("/accounts/withdraw/1?amount=10&ownerId=1")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.balance", is(990)));
    }

    @Test
    void givenAnOwner_WhenDeleteAccountByUserId_thenOK() throws Exception{
        mvc.perform(delete("/accounts/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}