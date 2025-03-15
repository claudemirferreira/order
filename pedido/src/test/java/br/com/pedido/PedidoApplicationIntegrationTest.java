package br.com.pedido;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PedidoApplicationIntegrationTest {

    @Test
    void contextLoads(ApplicationContext context) {
        // Verifica se o contexto do Spring foi carregado corretamente
        assertNotNull(context, "O contexto do Spring não foi carregado.");
    }

    @Test
    void main_DeveIniciarAplicacaoSemErros() {
        PedidoApplication.main(new String[]{});
    }
}