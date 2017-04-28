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

import br.uefs.ecomp.controller.ThreadServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author marcos ramos 
 * @version 1.0
 * 
 */
public class Server {
/** Esta classe Server,serve para realizar a interação do usuario com o servidor. */
   
    private static ServerSocket servidor;  /**Atributo que é usado para criar um objeto que é vai ser usado para  colocar o servidor  disponivél para aceitar conexões. <br/> */
    private static Socket Conexao;         /**Atributo que é usado para criar um objeto  que é criado com a indicação da porta que o servidor vai funcionar. <br/>*/
    private static int porta;              /**Atributo que é usado para  guarda o valor informado pelo usuario do sistema infromado qual porta o servidor vai funcionar. <br/> */
    
    public static void main (String []args){
        
           System.out.println("###########################################");
           System.out.println("#######  Welcome!(Bem Vindo!) ao #########"); /** Mensagens de boa vinda para o usuario  dod sistema. <br/> */
           System.out.println("#### Banco Cooperativo !       ############");
           System.out.println("###########################################\n");
       do{
           System.out.println("##########################################################");
           System.out.println("###  informe a porta  para o funcionamento do servidor ###");/** Solicita a porta para o funcionamento do seviço do sevidor. <br/>*/
           System.out.println("##########################################################\n"); 
           Scanner aux = new Scanner(System.in);
            porta=aux.nextInt(); /**ler  qual é a porta para o funcionamento  do serviço do sevidor. <br/> */
           
           if(porta<=1024){
               /**verificar  se  o usuario  informou um valor de porta não permitido. <br/> */
              System.out.println("#######################################################################");
              System.out.println("####  Para o servidor funcionar só pode porta  acima da porta 1024 ####");
              System.out.println("####  tente outra  porta!                  ############################");
              System.out.println("#######################################################################\n");
               /**Se o valor é ilegal  vai solocitar o valor de  novo.  <br/> */
           }           
       }while(porta<=1024);

        try {
           servidor = new ServerSocket(porta);/**Cria um objeto para criar a comunicação entre Cliente/Servidor, com o valor da porta informado pelo usuario do sistema. <br/> */
           System.out.println("###################################################################"); 
           System.out.println("#####   Servidor executando na porta" + " " + porta+"##############"); /**informa o valor da porta que vai ser usado para o funcionmento do servidor. <br/> */
           System.out.println("###################################################################\n");
           while (true) {
                 Conexao =  servidor.accept(); /** Criar um objeto que  ficara escutando e esperando as conexões de  clientes. <br/> */
                 System.out.println("########################################################################################");
                 System.out.println("###  Conexão Estabelecida com:" + " " + Conexao.getInetAddress().getHostAddress()+"#####");/** Informa que o sevidor recebeu uma conexão . <br/> */
                 System.out.println("#########################################################################################\n");
                 new ThreadServer(Conexao).start();/**Criar uma Thread para cada cliente e assim pode realizar as ações que o sistema foi projetado. <br/> */
            } 
   
        }catch (IOException ex) {
            System.out.println("######################################################");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("######    OCORREU  ALGUM ERRO ########################");
            System.out.println("######################################################\n");
        }
    }
    
}
