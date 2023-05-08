package api.lanches.lanchonete.modules.waiter.useCases;


import api.lanches.lanchonete.modules.waiter.dtos.CreateWaiterDTO;
import api.lanches.lanchonete.modules.waiter.dtos.ListWaiterDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class WaiterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CreateWaiterDTO> createWaiterDTOJacksonTester;

    @Autowired
    private JacksonTester<ListWaiterDTO> listWaiterDTOJacksonTester;

    @MockBean
    private WaiterUseCase waiterUseCase;

    @Test
    @DisplayName("Deveria devolver Erro 400 na criação de um garçom quando as informações estão invalidas.")
    @WithMockUser
    void createWaiterError() throws Exception {

        var response = mvc.perform(post("/waiter"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo 201 na criação de um garçom quando ocorre sucesso.")
    @WithMockUser
    void createWaiterSuccess() throws Exception {

        var listWaiter = new ListWaiterDTO(null, "Pedro", 1700);

        when(waiterUseCase.create(any())).thenReturn(listWaiter);

        var response = mvc.perform(post("/waiter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createWaiterDTOJacksonTester.write(
                        new CreateWaiterDTO("Pedro", 1700)
                ).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var waiterJson = listWaiterDTOJacksonTester.write(listWaiter).getJson();

        assertThat(response.getContentAsString()).isEqualTo(waiterJson);
    }

}