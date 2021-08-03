package com.magneto.entrypoint.controller;

import com.magneto.builder.BuilderConfigurationTest;
import com.magneto.builder.DataTestBuilder;
import com.magneto.crosscutting.constants.ResourceEndpoint;
import com.magneto.dto.DNARq;
import com.magneto.entrypoint.service.MagnetoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MagnetoController.class)
class MagnetoControllerTest extends BuilderConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MagnetoService magnetoService;

    @Test
    void verifyDnaIs200() throws Exception {
        Mockito.when(this.magnetoService.verifyDna(Mockito.any(DNARq.class)))
                .thenReturn(Mono.just(Boolean.TRUE));

        this.mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(
                this.mockMvc.perform(MockMvcRequestBuilders.post(ResourceEndpoint.MUTANT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataTestBuilder.dnaRqAsJsonString()))
                        .andExpect(MockMvcResultMatchers.request().asyncStarted())
                        .andReturn()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void verifyDnaIs403() throws Exception {
        Mockito.when(this.magnetoService.verifyDna(Mockito.any(DNARq.class)))
                .thenReturn(Mono.just(Boolean.FALSE));

        this.mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(
                this.mockMvc.perform(MockMvcRequestBuilders.post(ResourceEndpoint.MUTANT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DataTestBuilder.dnaRqAsJsonString()))
                        .andExpect(MockMvcResultMatchers.request().asyncStarted())
                        .andReturn()))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }


    @Test
    void getStatsIs200() throws Exception {
        Mockito.when(this.magnetoService.getStats())
                .thenReturn(Mono.just(DataTestBuilder.getStats()));
        this.mockMvc.perform(MockMvcRequestBuilders.asyncDispatch(
                this.mockMvc.perform(MockMvcRequestBuilders.get(ResourceEndpoint.STATS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.request().asyncStarted())
                        .andReturn()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
