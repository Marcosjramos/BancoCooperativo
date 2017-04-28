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
package br.uefs.ecomp.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author marcos ramos 
 * @version 1.0
 * 
 */
public class controllerClientes {
    /**classe  reponsavél  receber  o dados coletados  da tela e , após isso interegir com o sevidor<br/>
     *enviando  ou  recebendo dados. <br/>*/
    private int porta;                 /** Atributo que serve para para refere-se a porta que vai ocorre a comunica com do host cliente com o sevidor. <br/>*/
    private Socket Conexao;            /** Atributo que serve para cirar  o objeto  que cria a via de comunicação entre cliente e servidor. <br/>*/
    private String enderecoServidor;   /** Atributo que serve para informa  o endereço  do servidor que o cliente vai se comunica. <br/>*/
    private ObjectInputStream inputDados;/**Atributo  usado poder receber  dados  do servidor. <br/> */
    private ObjectOutputStream outputDados;/** Atributo usado para poder enviar dados para o servidor. <br/>*/
    private String  idCliente;
    /** no construtor  vai  vai receber  o seguintes  parâmetros: 
     *@param String  que  refere-se ao endereço ip do servidor <br/>
     *@param int que refere-se a porta  que  host cliente vai se comunica  com o servidor<br/> 
     * Além  disso , o  construtor  criar os  objstos para comunica  entre  host e  para envio e recebimento de dados. <br/>
     */
    public controllerClientes(String enderecoServidor ,int porta) throws IOException{
      
     try {
         this.enderecoServidor = enderecoServidor;
         this.porta = porta;
         this.Conexao= new Socket(enderecoServidor, porta);
        this.outputDados= new ObjectOutputStream(this.Conexao.getOutputStream());
        this.inputDados= new ObjectInputStream(this.Conexao.getInputStream());
        
     } catch (IOException ex){
        Logger.getLogger(controllerClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     } 
    /**A função limpaTela realiza a ação de limpa  a ela que será exibida para o usuario sem muita coisa  nela . <br/> */
    public void limparTela(){ 
        
        for(int i=0;i<15;i++){
            
            System.out.println("\n");

        }
     System.out.println("###############################################################################\n");
      
    }
    
  
   /** A função a cadastrarCliente  vai receber os seguintes parâmetros: <br/>
    *@param String  com o nome completo  do  cliente  da conta <br/>
    *@param String  com a data de nascimento do cliente do sistema bancário <br/>
    *@param String  com a o  sexo do cliente <br/>
    *@param String  com CNPJ em caso de cliente jurídico ou o CPF em caso de pessoas física <br/>
    *@param String  com nome  do usuario  do sistema  que vai  usar para acessar suas informações depois  <br/>
    *@param String  com  a senha do usuario  do sistema bancário <br/>
    *  assim  o sistema  vai realizar a operação de cadastra um cliente na sua  base de dados <br/>
    */
   public void cadastrarCliente(String nomeCompleto, String Nascimento, String sexo,
                                String cpfcnpj, String username, String password){
       
      try {
           
        
      outputDados.writeUTF("1"+";"+nomeCompleto+";"+Nascimento+";"+ sexo+";"
                             +cpfcnpj+";"+ username+";"+password);
      outputDados.flush();
      String pack= inputDados.readUTF();
         if(pack.startsWith("1")){
             
             System.out.println(pack); 
             /**gera  um id do cliente  para conta. <br/> */
           Random gerador = new Random();
           int x= gerador.nextInt(7777);
           idCliente=String.valueOf(x);
           /**mostro  o id cliente. <br/> */
          System.out.println("cadastro realizadocom suscesso !!!!");
          System.out.println(" Id de  sua conta é :"+idCliente);
          System.out.println(" a note seu id é importante  para localizar sua conta");
       
          }else{
          System.out.println("Ocorreu  algum  erro e o cadastro poder ser realizado");
         
          }
        } catch (IOException ex) {
           System.out.println(ex.toString());
        }
       
    
   }
   /** A função  Login  receber como  parâmetros os seguinte dados:<br/>
     *@param String  com username do cliente que  vai logar <br/>
     *@param String  com  a agencia  do cliente  que vai logarr <br/>
     *@param String  com numero  da conta  do cliente que vai logar <br/>
     *@param String  com a senha  da conta que o cliente vai logar <br/>
     * assim o sistema  vai procura o cliente  que informou os dados anteriores <br/>
     */
   public int  Login(String username ,  String  password){
       
        try {
            outputDados.writeUTF("2"+";"+username+";"+password);
            outputDados.flush();
            String pack= inputDados.readUTF();
            if(pack.startsWith("2")){
                 System.out.println(pack); 
                 System.out.println("Operação realizada com sucesso !!!!"); 
                 return 2;
            }else{
                 System.out.println("Ocorreu  um erro  e o senhor(a) não  poder realizar o login"); 
                return-2;
            }
        } catch (IOException ex) {
            Logger.getLogger(controllerClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
       
   }
   /** A  função abrirConta  recebe os seguintes parâmetros:
    *@param String   com a agencia  que a conta pertence <br/>
    *@param String  com o  número da conta <br/>
    *@param String  com  os dados deum titular  <br/>
    *@param String   com  os dados de titular <br/>
    *@param String   com dados de titular <br/>
    *@param String   com dados de titular <br/>
    *  assim  o sistem vai realizar  a operação de abrir  um conta <br/>
    */
  public void abrirConta(String agencia, String numero, String tipo,String tipoConta 
                       ){
      
   
        try {
       
        outputDados.writeUTF("3" + ";" + agencia + ";" + numero+ ";" + tipo + ";"+tipoConta+";"
                               + idCliente);
        outputDados.flush();
        String pack= inputDados.readUTF();
         if(pack.startsWith("3")){
          
           System.out.println(pack); 
           System.out.println("Operação realizada com sucesso !!!!"); 
           
           
          }else{
           System.out.println(pack);   
           System.out.println("Ocorreu  algum erro  na  abertura de conta  !!!!"); 
          
           }
        } catch (IOException ex) {
           System.out.println(ex.toString());
        }
       
  }
  /** A função  a seguir recebe os seguintes parâmetros :
    *@param String  com username do cliente <br/>
    *@param String  com a senha  do cliente <br/>
    *@param String  com o número do  cliente <br/>
    *@param String  com o valor que vai ser adicionado a conta do cliente <br/>
    *@param String  com o número da agencia <br/> 
    *@param String  com o tipo de  conta <br/>
    * assim realizar  a operação de depositar um  valor na conta <br/>
    */
  public void Deposito(String username, String password,String valor, 
                       String numero,String agencia,String tipo){
        try {
        outputDados.writeUTF("4"+";"+username+";"+password+";"+valor+";"
                                +numero+";"+tipo+";"+ agencia+"\n");
        outputDados.flush();
         String pack= inputDados.readUTF();
         if(pack.startsWith("4")){
           System.out.println(pack); 
           System.out.println("Operação realizada com sucesso !!!!"); 
          }else{
          System.out.println(pack);
          System.out.println("Ocorreu  algum erro  na  abertura de conta  !!!!"); 
          
          }
        } catch (IOException ex) {
           System.out.println(ex.toString());
        }  
  }
  /**A função Transferencia que recebe os seguintes parâmetros :
    *@param String  com username  do cliente <br/> 
    *@param String  com a senha  do cliente <br/>
    *@param String  com o valor que vai sair da conta de origem e vai para conta de destino <br/>
    *@param String  com o  número  da conta <br/>
    *@param String  com o tipo da conta de origem <br/>
    *@param String  com a identificação  da agencia da conta <br/>
    *@param String  com o número  da conta que vai receber o valor <br/>
    *@param String  com a identificação  da agencia da conta que vai receber o valor <br/>
    *@param String  com o  tipo da conta que  vai receber o valor <br/>
    * assim o sistema realizar ação  de transferencia bancária. <br/>
    */
  public void Transferencia( String username, String password,String valor, 
                             String numero,String agencia,String tipo, 
                             String DestinoNumero,String agenciaDestino,
                             String tipoDestino){
        try {
         outputDados.writeUTF("5"+";"+username+";"+password+";"+valor+";"
                                   +numero+";"+agencia+";"+tipo+";"+DestinoNumero+
                                    ";"+agenciaDestino+";"+tipoDestino);
         outputDados.flush();
        String pack= inputDados.readUTF();
         if(pack.startsWith("5")){
           System.out.println("Operação realizada com sucesso !!!!");
           System.out.println(pack); 
          }else{
          System.out.println(pack);
          System.out.println("Ocorreu  algum erro  na  abertura de conta  !!!!"); 
          }
        } catch (IOException ex) {
           System.out.println(ex.toString());
        }  
  }  
  
}
