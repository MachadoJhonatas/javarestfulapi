package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DeployItemRecordDto(  @NotBlank String nome, 
                                    @NotBlank String task,
                                    @NotBlank String caminho, 
                                    @NotBlank String responsavel, 
                                    @NotBlank String dataInclusao, 
                                    @NotBlank String status) {

}
