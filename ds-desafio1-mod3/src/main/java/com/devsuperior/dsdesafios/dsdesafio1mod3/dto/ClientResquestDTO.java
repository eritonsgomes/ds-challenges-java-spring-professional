package com.devsuperior.dsdesafios.dsdesafio1mod3.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;

public class ClientResquestDTO implements Serializable {

    public ClientResquestDTO() {
    }

    public ClientResquestDTO(String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    @NotBlank(message = "O nome deve ser informado")
    private String name;

    @NotNull(message = "O CPF deve ser informado")
    @CPF(message = "O CPF é Inválido")
    private String cpf;

    @Min(value = 0, message = "O Salário não pode ser menor que zero")
    private Double income;

    @PastOrPresent(message = "A Data de Aniversário deve estar no passado ou presente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Min(value = 0, message = "A quantidade de filhos não pode ser menor que zero")
    private Integer children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

}
