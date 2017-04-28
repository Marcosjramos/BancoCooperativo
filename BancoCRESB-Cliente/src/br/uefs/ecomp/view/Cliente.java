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
package br.uefs.ecomp.view;

import br.uefs.ecomp.controller.controllerClientes;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author marcos ramos 
 * @version 1.0
 * 
 */
public class Cliente {
    /** Classe que é a interface de comunicação entre usuario com a parte do sistema que vai se comunicar com o servidor. <br/>
     */

  private static controllerClientes novo;  /**Atributo do tipo controllerClientes para poder criar um objeto para acessar os métodos da classe controllerClientes. <br/> */
  private static String  enderecoip;      /** Atributo para poder receber o  endereço do servidor. <br/> */
  private static int porta;               /** Atributo para poder receber a porta  para ocorrer a comunicação  com o servidor. <br/>  */
  private static int opcao;               /** Atributo para receber a opção do menu do cliente. <br/> */
  private static int numeroConta;          /**Atributo para poder gerar o número da conta. <br/> */
  private static int agenciaConta;        /**Atributo para poder gerar o número da agencia  da conta . <br> */
  
    public static void main(String[] args) {
           System.out.println("##########################################");
           System.out.println("Welcome! (Bem Vindo!) ao ");/**Mensagem  para com o Cliente do sistema. <br/> */
           System.out.println("Banco Cooperativo !");
           System.out.println("##########################################\n");
       do{
           System.out.println("##########################################");
           System.out.println("informe o endereço  do servidor \n");/** solicitar ao usuario do sistema o endereço do servidor . <br/>*/
           Scanner aux = new Scanner(System.in);
           enderecoip=aux.nextLine();
           System.out.println("####################################################");
           System.out.println("informe a para ter a comunicação com o servidor  \n");/**solicitar ao usuario do sistema a porta que ele possa se comunicar com o servidor . <br/>*/
           porta=aux.nextInt();
           
           if(porta<=1024){
               /**Caso a porta informa seja ilegal vai informa  que é uma porta ilegal e assim vai solicita novamente. <br/> */
               System.out.println("################################################################");
               System.out.println(" Para o servidor funcionar só pode porta  acima da porta 1024 ##");
               System.out.println(" tente outra  porta!  ##########################################");
               System.out.println("################################################################\n");
            
           }           
       }while(porta<=1024);

      try {
          novo = new controllerClientes(enderecoip,porta);/**Vai criar o objeto do tipo controllerClientes passando a informações necessaria e assim gerando a comunicação entre hosts. <br/> */
          novo.limparTela();/**Limpa a tela de exibição para com o usuario. <br/>*/
          
          do{
              
              novo.limparTela();
              /** Opçãoes  de operações do sistema. <br/>*/
              System.out.println("########################################");
              System.out.println("########### 1- Se cadastrar          ###");
              System.out.println("########### 2- Realizar  login       ###");
              System.out.println("########### 7-          sair         ###");
              System.out.println("########################################");
              Scanner aux = new Scanner(System.in);
              opcao= aux.nextInt();
              
              if(opcao==1){
                  /** tem o opção 1 como escolha  realizará o cadastro do cliente  para poder usar o sistema. <br/> */
                  System.out.println("Insira  o seu nome  completo");
                   Scanner n = new Scanner(System.in);
                   String nome = n.nextLine();
                  
                  System.out.println("Insira  sua data de nascimento");
                   Scanner nas = new Scanner(System.in);
                   String nasc = nas.nextLine();
                
                  System.out.println("Insira  qual é o  seu sexo ");
                   Scanner s = new Scanner(System.in);
                   String sexo=s.nextLine();
                  
                  System.out.println("Insira  seu CPF ou CNPJ");
                  Scanner cc = new Scanner(System.in);
                  String CpfCnpj= cc.nextLine();
                   
                  System.out.println(" Qual  o seu usario  do sistema ");
                   Scanner u = new Scanner(System.in);
                   String username = u.nextLine();
              
                  System.out.println(" Qual  vai ser sua senha  no sistema  ");
                   Scanner pass = new Scanner(System.in);
                   String password = pass.nextLine();
                   
                 novo.cadastrarCliente(nome, nasc, sexo, CpfCnpj, username, password);
                
                 opcao=0;
                  
              }else if(opcao==2){
                   /** tem o opção 2 como escolha  realizará o login  do cliente  no sistema para poder utilizar as funções do sistema . <br/> */ 
                 System.out.println(" Insira seu  usuario ");
                 Scanner u = new Scanner(System.in);
                 String username = u.nextLine();
                 
                 System.out.println(" Insira  sua senha  ");
                 Scanner pass = new Scanner(System.in);
                 String password = pass.nextLine();
                 
                  int temp = novo.Login(username, password);
                  /** Após a realização do login com sucesso  vai exibir o novo menu com novas funções. <br/> */
                  if(temp==2){
                      
                       int op = 0;
                      do{
                          novo.limparTela();
                          /** Neste menu o cliente  poderá  realizar algumas transações banárias. <br/>*/
                          System.out.println("##########################################");
                          System.out.println("####   3-  Abertura de conta         #####");
                          System.out.println("####   4-  Realizar deposito         #####");
                          System.out.println("####   5-  Realizar Transferencia    #####"); 
                          System.out.println("####   6 -        Sair               #####");
                          System.out.println("##########################################");
                          Scanner a = new Scanner(System.in);
                          op =a.nextInt();
                          
                          if(op==3){
                           /**  Caso  a escolha seja 3 ocorrerá  a abertura de uma conta. <br/> */
                         String tipo=null;
                         Random gerador = new Random();
                         numeroConta = gerador.nextInt(99999);/** vai gerar um valor para número da conta. <br/> */
                         agenciaConta= gerador.nextInt(88888);/** vai gerar um valor para número da agencia da conta. <br/> */
                         String numero= Integer.toString( numeroConta); /** transfoma o número da conta em um String. <br/> */
                         String agencia=Integer.toString(agenciaConta); /** transfoma o número da agencia que conta pertence em um String. <br/> */
                         int teste=0;
                         String fisica ="física";
                          String juridica ="Jurídica";
                            /**  usuario  indicar  qual é tipo de sua  conta. <br/> */
                             System.out.println("##########################################");
                             System.out.println("####  Qual é  tipo da sua  conta ?  ######");
                             System.out.println("######### 1-  Física     #################");
                             System.out.println("######### 2-  Jurídica   #################");
                             System.out.println("##########################################");
                             Scanner w = new Scanner(System.in);
                             teste = w.nextInt();
                             
                             if(teste==1){
                                 tipo=fisica;
                                 
                             }else if(teste==2){
                                 tipo=juridica;
                             }
                        
                        
                         String cont="poupança";
                         String conta="corrente";
                         int opt=0;
                         String tipoConta=null;
                         /**  usuario  indicar  qual é o seu perfil de sua conta. <br/> */
                             System.out.println("##########################################");
                             System.out.println("#####  Insira o perfil  de  sua ? ########");
                             System.out.println("#####  1- poupança     ###################");
                             System.out.println("#####  2- corrente     ###################");
                              System.out.println("##########################################");
                             Scanner hj = new Scanner(System.in);
                             opt = hj.nextInt();
                             
                             if(opt==1){
                                tipoConta=cont;
                                 
                             }else if(opt==2){
                                 tipoConta=conta;
                             }
                          /**  Vai  exibir  ao  cliente o número de  sua conta e de sua  agencia. <br/>*/
                          System.out.println("##############################################");
                          System.out.println("##  Numero de  sua  agencia é :"+agencia+" ###");
                          System.out.println("##  Numero de  sua conta é :"+numero+ "#######");
                          System.out.println("##############################################");
                          
                          novo.abrirConta(agencia, numero, tipo, tipoConta);
                          
                         op=9;
                         
                          }else if(op==4){
                           /**  Caso  a escolha seja 4 ocorrerá  a ação de  depósitar  um valor  na conta . <br/> */
                         System.out.println("Insira  o número de sua  conta");
                         Scanner  nu= new Scanner(System.in);
                         String numero= nu.nextLine();
                         
                         System.out.println("Insira   agencia  que sua  conta pertence ");
                         Scanner  ag= new Scanner(System.in);
                         String agencia= ag.nextLine();
                         
                         
                           String conta=null;
                           int ao=0;
                           String fisica ="física";
                           String juridica ="Jurídica";
                            /**  usuario  indicar  qual é o seu perfil de sua conta. <br/> */
                             System.out.println("##########################################");
                             System.out.println("####  Qual é  tipo da sua  conta ?  ######");
                             System.out.println("######### 1-  Física     #################");
                             System.out.println("######### 2-  Jurídica   #################");
                             System.out.println("##########################################");
                             Scanner qtd = new Scanner(System.in);
                             ao = qtd.nextInt();
                             
                             if(ao==1){
                                 conta=fisica;
                                 
                             }else if(ao==2){
                                 conta=juridica;
                             }
                         
                         System.out.println("Como forma de segurança  insira  o seu  usuario novamente");
                         Scanner fc=new Scanner(System.in);
                         String usuario = fc.nextLine();
                         
                         System.out.println("Como forma de segurança  insira  o sua senha novamente");
                         Scanner sen=new Scanner(System.in);
                         String senha = sen.nextLine();
                         
                         System.out.print("Insira o valor a senher depositado em sua conta R$ ");
                         Scanner  v= new Scanner(System.in);
                         String valor= v.nextLine();
                         
                          novo.Deposito(usuario, senha, valor, numero, agencia, conta);
                         
                         op=9;
                         
                          }else if(op==5){
                         
                              /**  Caso  a escolha seja 4 ocorrerá  a  Trasnferẽncia  bancária. <br/> */
                             System.out.println("Insira  o número de sua conta");
                             Scanner  n= new Scanner(System.in);
                             String num= n.nextLine();
                             
                             System.out.println("Insira agencia que sua conta pertence");
                             Scanner ct= new Scanner(System.in);
                             String conta= ct.nextLine();
                             
                             
                            String tipo=null;
                           int at=0;
                           String fisica ="física";
                           String juridica ="Jurídica";
                             /**  usuario  indicar  qual é tipo de sua  conta. <br/> */
                             System.out.println("##########################################");
                             System.out.println("####  Qual é  tipo da sua  conta ?  ######");
                             System.out.println("######### 1-  Física     #################");
                             System.out.println("######### 2-  Jurídica   #################");
                             System.out.println("##########################################");
                             Scanner qt = new Scanner(System.in);
                             at = qt.nextInt();
                             
                             if(at==1){
                                 tipo=fisica;
                                 
                             }else if(at==2){
                                 tipo=juridica;
                             }
                             
                             
                             System.out.println("Insira número da conta  que vai receber o valor");
                             Scanner nD= new Scanner(System.in);
                             String numeroDestino= nD.nextLine();
                             
                             System.out.println("Insira a agencia que a conta que vai receber o valor  pertence");
                             Scanner aD= new Scanner(System.in);
                             String agenciaDestino= aD.nextLine();
                             
                             System.out.println("Insira tipo da  conta  que  vai receber o valor");
                             Scanner tD= new Scanner(System.in);
                             String tipoDestino= tD.nextLine();
                             
                             System.out.print("Insira o valor que vai ser tranferido de sua  conta :R$");
                             Scanner va= new Scanner(System.in);
                             String valor= va.nextLine();
                             
                             System.out.println("Como forma de segurança  insira  o seu  usuario novamente");
                             Scanner fc=new Scanner(System.in);
                             String usuario = fc.nextLine();
                         
                             System.out.println("Como forma de segurança  insira  o sua senha novamente");
                             Scanner sen=new Scanner(System.in);
                             String senha = sen.nextLine();
                             
               
                             novo.Transferencia(usuario,senha,valor,num,conta,tipo,numeroDestino,agenciaDestino,tipoDestino);
                             op=9;
                          }
                   
                      }while(op!=6);
                      opcao=0;
                  }else{
                      /** Mensagem de  erro  para com usuario. <br/>*/
                      System.out.println("#######################################################");
                      System.out.println("### Erro, você  não podera realizar nenhuma  ação #####");
                      System.out.println("######################################################");
                  }
                  
              }
           
          }while(opcao!=7);
          
          System.out.println("##########################################");
          System.out.println("###             OBRIGADO  ################ ");/** agradecimento ao usuario do sistema. <br/> */
          System.out.println("###             VOLTE SEMPRE! ############");  
          System.out.println("##########################################");
          
         
      } catch (IOException ex) {
          
          Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   
}