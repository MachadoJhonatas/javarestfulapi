package com.example.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="TB_DEPLOYITEM")
public class DeployItemModel extends RepresentationModel<DeployItemModel> implements Serializable  {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;

    private String task;
    private String nome;
    private String caminho;
    private String responsavel;
    private String dataInclusao;
    private String status;

    public DeployItemModel (){

    }

    public DeployItemModel(UUID idProduct, String task, String nome, String caminho, String responsavel,
            String dataInclusao, String status) {
        this.idProduct = idProduct;
        this.task = task;
        this.nome = nome;
        this.caminho = caminho;
        this.responsavel = responsavel;
        this.dataInclusao = dataInclusao;
        this.status = status;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public UUID getIdProduct() {
        return idProduct;
    }
    public String getTask() {
        return task;
    }
    public String getNome() {
        return nome;
    }
    public String getCaminho() {
        return caminho;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public String getDataInclusao() {
        return dataInclusao;
    }
    public String getStatus() {
        return status;
    }
    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "DeployItemModel [idProduct=" + idProduct + ", task=" + task + ", nome=" + nome + ", caminho=" + caminho
                + ", responsavel=" + responsavel + ", dataInclusao=" + dataInclusao + ", status=" + status + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
        result = prime * result + ((task == null) ? 0 : task.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((caminho == null) ? 0 : caminho.hashCode());
        result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
        result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeployItemModel other = (DeployItemModel) obj;
        if (idProduct == null) {
            if (other.idProduct != null)
                return false;
        } else if (!idProduct.equals(other.idProduct))
            return false;
        if (task == null) {
            if (other.task != null)
                return false;
        } else if (!task.equals(other.task))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (caminho == null) {
            if (other.caminho != null)
                return false;
        } else if (!caminho.equals(other.caminho))
            return false;
        if (responsavel == null) {
            if (other.responsavel != null)
                return false;
        } else if (!responsavel.equals(other.responsavel))
            return false;
        if (dataInclusao == null) {
            if (other.dataInclusao != null)
                return false;
        } else if (!dataInclusao.equals(other.dataInclusao))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
    
    

}
