package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;


public record DeployItemRecordDto(  @NotBlank String nome, 
                                    @NotBlank String task,
                                    @NotBlank String caminho, 
                                    @NotBlank String responsavel, 
                                    @NotBlank String dataInclusao, 
                                    @NotBlank String status) {

}
