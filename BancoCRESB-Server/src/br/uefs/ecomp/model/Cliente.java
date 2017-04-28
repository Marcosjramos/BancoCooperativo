 /**
    * <h1>Componente Curricular: Módulo Integrado de Concorrencia e Conectividade</h1>
    * Autor: <Marcos de Jesus Ramos>
    * Data:  <20 de abril de 2017>
    * Declaro que este código foi elaborado por mim de forma individual e
    * não contém nenhum trecho de código de outro colega ou de outro autor, 
    * tais como provindos de livros e apostilas, e páginas ou documentos 
    * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
    * uma citação para o  não a minha está destacado com  autor e a fonte do
    * código, e estou ciente que estes trechos não serão considerados para fins
    * de avaliação. Alguns trechos do código podem coincidir com de outros
    * colegas pois estes foram discutidos em sessões tutorias.
    */
package br.uefs.ecomp.model;
/**
 *<h2> Classe Cliente do sistema </h2>
 *<br/> Esta classe foi desenvolvida para representar algo 
 *  muito importante para o sistema bancário. <br>
 */
import java.io.Serializable;
/**
 * @author marcos ramos 
 * @version 1.0
 * 
 */
public class Cliente implements Serializable {
     /** <h3> Classe Cliente </h3> */
     /**<br/> Esta classe implementa serializable para poder ser gravada em um arquivo<br/> */
    private String username;  /** Atributo da classe cliente que refere-se ao username do cliente no sistema <br/>*/
    private String password;  /** Atributo da classe cliente que refere-se a senha do cliente no sistema <br/> */
    private String nome;      /** Atributo da classe cliente que refere-se ao nome do cliente no sistema <br/>*/
    private String sexo;      /** Atributo da classe cliente que refere-se ao sexo do cliente no sistema <br/>*/
    private String nascimento;/** Atributo da classe cliente que refere-se data de nascimento do cliente no sistema <br/>*/
    private String CpfCnpj;   /** Atributo da classe cliente que refere-se CPF ou CNPJ que vai depender do tipo do cliente no sistema <br/> */
/**
     * A classe cliente  recebe em seu construtor  o seguintes  parâmetros :
     * @param  String  que  refere-se ao username  do  cliente do sistema  <br/>
     * @param  String  que  refere-se    senha  do cliente  no sistema  <br/>
     * @param  String  que  refere-se ao nome do cliente no sistema  <br/>
     * @param  String  que  refere-se ao sexo  do  cliente  no sistema <br/>
     * @param  String  que  refere-se a data de nascimento do  cliente no sistema <br/>
     * @param  String  que  refere-se ao CPF em caso de cliente  fisico e CNPJ em caso de cliente jurídico <br/> 
     */
    public Cliente(String username, String password, String nome, String sexo,
                   String nascimento, String CpfCnpj){
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.CpfCnpj = CpfCnpj;
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpfCnpj() {
        return CpfCnpj;
    }

    public void setCpfCnpj(String CpfCnpj) {
        this.CpfCnpj = CpfCnpj;
    }
}
