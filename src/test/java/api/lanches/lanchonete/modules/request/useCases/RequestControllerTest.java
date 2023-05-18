package api.lanches.lanchonete.modules.request.useCases;

import api.lanches.lanchonete.modules.request.dtos.CreateRequestDTO;
import api.lanches.lanchonete.modules.request.dtos.ListRequestDTO;
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
class RequestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ListRequestDTO> listRequestDTOJacksonTester;

    @Autowired
    private JacksonTester<CreateRequestDTO> createRequestDTOJacksonTester;

    @MockBean
    private RequestUseCase requestUseCase;

    @Test
    @DisplayName("Deveria retornar erro 400 quando tentar criar pedido com dados invalidos")
    @WithMockUser
    void createRequestError() throws Exception {

        var response = mvc.perform(post("/request"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar 201 quando tentar criar pedido com sucesso")
    @WithMockUser
    void createRequestSuccess() throws Exception {

        var listRequest = new ListRequestDTO(null, 1, "Suco", 6, 0);
        var createdRequest = new CreateRequestDTO(11L, 1L);

        when(requestUseCase.create(any())).thenReturn(listRequest);

        var response = mvc.perform(post("/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRequestDTOJacksonTester.write(createdRequest).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var requestJson = listRequestDTOJacksonTester.write(listRequest).getJson();

        assertThat(response.getContentAsString()).isEqualTo(requestJson);

    }
}