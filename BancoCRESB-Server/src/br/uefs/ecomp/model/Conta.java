 /**
    * <h1>Componente Curricular: Módulo Integrado de Concorrencia e Conectividade</h1>
    * Autor: <Marcos de Jesus Ramos>
    * Data:  <20 de abril de  2017>
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
 *<h1> Classe conta do sistema </h1>
 *<br/> Esta classe foi desenvolvida para representar algo 
 *muito importante para o sistema bancário. <br>
 */
import java.io.Serializable;
/**
 * @author marcos ramos 
 * @version 1.0
 * 
 */
public class Conta implements Serializable {
    
    /**  <h2> Classe Conta</h2> */
    /**  <br/> Esta  classe  implementa serializable  para  poder  ser gravada em um arquivo<br/> */
    
    private String idCliente; /**O atributo do  que rrefere-se a identificação do cliente  cono da conta  <br/> */
    private String tipo;       /**O atributo do tipo String chamado tipo vai refere-se qual é o tipo da conta criada <br/>*/
    private String numero;     /**O atributo do tipo String chamado numero vai refere-se o numero de identificação da conta <br/> */
    private String agencia;    /**O atributo do tipo String chamado agencia que vai refere-se agencia a qual a conta faz parte <br/>*/
    private String saldo;      /**O atributo do tipo String chamado saldo  qua vai refere-se ao saldo da conta <br/> */
    private String tipoConta; /** O atributo do tipo String  chamdo tipoConta  que  refere-se se a conta vai ser corrente ou poupança . <br/>*/ 
    /**
     * A classe conta  recebe em seu construtor  o seguintes  parâmetros :
     * @param  String  de   identificação do cliente dono da conta  <br/>
     * @param  String  que  refere-se tipo da conta se é  física ou  jurídica <br/>
     * @param  String  que  refere-se ao número de identificação da  conta <br/>
     * @param  String  que  refere-se ao número  da agencia  que  a conta faz parte <br/>
     * @param  String  que  refere-se  ao saldo  da  conta <br/>*/
    public Conta(String idCliente, String tipo, String numero, String agencia,String saldo,String tipoConta) {
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo=saldo;
        this.tipoConta=tipoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
    

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }


}
