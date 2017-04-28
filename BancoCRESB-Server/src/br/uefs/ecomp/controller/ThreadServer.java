/**
 * <h1>Componente Curricular: Módulo Integrado de Concorrencia e
 * Conectividade</h1>
 * Autor: <Marcos de Jesus Ramos>
 * Data:  <20 de abril de  2017>
 * Declaro que este código foi elaborado por mim de forma individual e não
 * contém nenhum trecho de código de outro colega ou de outro autor, tais como
 * provindos de livros e apostilas, e páginas ou documentos eletrônicos da
 * Internet. Qualquer trecho de código de outra autoria que uma citação para o
 * não a minha está destacado com autor e a fonte do código, e estou ciente que
 * estes trechos não serão considerados para fins de avaliação. Alguns trechos
 * do código podem coincidir com de outros colegas pois estes foram discutidos
 * em sessões tutorias.
 */
package br.uefs.ecomp.controller;

import br.uefs.ecomp.model.Cliente;
import br.uefs.ecomp.model.Conta;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author marcos ramos
 * @version 1.0
 *
 */
public class ThreadServer extends Thread {

    /** <h2>Classe ThreadCliente</h2>
     * <br/>nesta classe que herda da classe do java Thread tem
     * <br/>nela toda logica pra ter a comunicação entre hosts.
     */
    private ArrayList<Cliente> clientesBanco;  /**  Atributo que vai guarda uma coleção de clientes do banco. <br/>*/
    private ArrayList<Conta> contasBanco;      /**  Atributo que vai guarda uma coleção de contas do banco. <br/> */
    private DataOutputStream outputDados;      /**  Atributo que vai receber o dados do hots do  cliente. <br/> */
    private ObjectInputStream inputDados;      /**  Atributo que vai enviar  dados  para ohotos do  cliente. <br/>*/
    private Socket connection;                 /**  Atributo que sever estabelecer uma conexao. <br/>*/

    /** <h3> No construtor desta classe vai recerber
     * <br/> o objeto por estabelecer uma conexão com hots do cliente </h3>
     *
     * @param Socket chamado connection para estabelecer esta conexão<br/>
     * no construtor vai criar os objetos para pooder passa e <br/>
     * receber dados do hosts do cliente.
     * E as estrutura de dados  que vai armazaner temporariamente os dados  dos  clientes. <br/>
     */

    public ThreadServer(Socket connection) throws IOException {

        this.connection = connection;
        this.clientesBanco = new ArrayList<>();
        this.contasBanco = new ArrayList<>();
    }

    /**
     * como esta classe herda de Thread sobrescrito o método run para atender a
     * necessidade do sistema. <br/>
     */
    public void run() {

       
        try (ObjectInputStream inputDados = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream outputDados = new ObjectOutputStream(connection.getOutputStream());) {
               
            try {
                /**
                 * a seguinte tem o loop que v ai aguarda solicitações de hosts
                 * de clientes. <br/>
                 */
                while (true) {
                    String pack = inputDados.readUTF();
                    System.out.println(pack);
                    /**
                     * A condicional ficar verificando se o usuario esta
                     * conectado. <br/>
                     */
                    
                    if(pack.startsWith("1")){
                        /** a primira condicional  verificar qual é opção do usuario e realiza a operação<br/> 
                         *  de  cadastra um  cliente com  o dados  qua o usuario informou. */
                        System.out.println(pack.startsWith("1"));

                         String nome, nascimento, sexo, cpfcnpj, username, password = new String();
                         String[] data = new String[pack.length()];
                         data = pack.split(";");
                         nome = data[1];
                         nascimento = data[2];
                         sexo = data[3];
                         cpfcnpj = data[4];
                         username = data[5];
                         password = data[6];

                        Cliente novo = new Cliente(username, password, nome, sexo, nascimento, cpfcnpj);
                        /** Verificar se o usuario já  foi cadastra anteriormente ,caso já  sim não realizar o cadastra  se não realizar o cadastro. <br>*/
                        if (clientesBanco.isEmpty() || clienteCadastrado(clientesBanco, novo)) {
                          clientesBanco.add(novo);
                          salvarClientes(novo);
                          outputDados.writeUTF("1");
                          outputDados.flush();
                       }else {
                          outputDados.writeUTF("-1");
                          outputDados.flush();
                        }
                        
                    } else if(pack.startsWith("2")){
                            /** a segunda  condicional  verificar qual é opção do usuario e realiza a operação<br/> 
                           * login. <br> */
                        System.out.println(pack.startsWith("2"));
                        String  username, password = new String();
                        String [] data=new String[pack.length()];
                        data = pack.split(";");
                        username = data[1];
                        password =data[2]; 
                        
                      boolean aux= login ( clientesBanco,username,password);
                      
                      if(aux==true){
                          /**  verificar se o login  ocorreu com sucesso  ou não. <br/>*/
                          outputDados.writeUTF("2");
                          outputDados.flush();
                       }else{
                          outputDados.writeUTF("-2");
                          outputDados.flush();
                      }
                        
                    }else if(pack.startsWith("3")){
                           /** a terceira  condicional  verificar qual é opção do usuario e realiza a operação<br/> 
                           * de abertura de uma conta  no banco.  <br/>  */
                       System.out.println(pack.startsWith("3"));
                       String agencia, numero, tipo, saldo, tipoConta, idCliente = new String();
                       String [] data=new String[pack.length()];
                       data = pack.split(";");
                       agencia= data[0];
                       numero =data[1];
                       tipo=data[2];
                       saldo=data[3];
                       tipoConta=data[4];
                       idCliente=data[5];
                       
                       Conta nova = new Conta(idCliente,tipo,numero,agencia,saldo,tipoConta);
                        /** Verificar se já exite  a conta  já  foi aberta  anteriormente ,caso já  sim não realizar o abertura   se não realizar o abertura . <br>*/
                         if (contasBanco.isEmpty() || contaCadastrada(contasBanco, nova)) {
                          contasBanco.add(nova);
                          salvarContas(nova);
                          outputDados.writeUTF("3");
                          outputDados.flush();
                        }else {
                          outputDados.writeUTF("-3");
                          outputDados.flush();
                        }
                      
                    }else if(pack.startsWith("4")){
                         /** a quarta condicional  verificar qual é opção do usuario e realiza a operação<br/> 
                           * deposito  na  conta  bancária do cliente.  <br/>  */
                         System.out.println(pack.startsWith("4"));
                         String username, password, valor, numero, tipo, agencia = new String();
                         String[] data = new String[pack.length()];
                         data = pack.split(";");
                         username = data[1];
                         password = data[2];
                         valor = data[3];
                         numero = data[4];
                         agencia = data[5];
                         tipo = data[6];

                      int temp=Deposita(clientesBanco, contasBanco, username, password, valor, numero, agencia, tipo);
                   
                     
                     /** Caso a transação bancária tem ocorrido com sucesso salvo  a conta em um arquivo. <br/> */   
                     if(temp==4){
                      
                          outputDados.writeUTF("4");
                          outputDados.flush();
                        }else{
                            outputDados.writeUTF("-4");
                          outputDados.flush();
                        }
                    }else if(pack.startsWith("5")){
                        /** a quinta condicional  verificar qual é opção do usuario e realiza a operação<br/> 
                           * Transferência  bancária  solicitada  pelo  cliente .  <br/>  */
                        System.out.println(pack.startsWith("5"));
                        String username, password, valor, numero, agencia, tipo, DestinoNumero, agenciaDestino, tipoDestino = new String();
                        String[] data = new String[pack.length()];
                        data = pack.split(";");
                        username = data[1];
                        password = data[2];
                        valor = data[3];
                        numero = data[4];
                        agencia = data[5];
                        tipo = data[6];
                        DestinoNumero = data[7];
                        agenciaDestino = data[8];
                        tipoDestino = data[9];

                    int  temp= Transferir(clientesBanco, contasBanco, username, password, valor, numero, agencia, tipo, DestinoNumero, agenciaDestino, tipoDestino);
                    
                     
                     /** Caso  a transferência bancária tem ocorrido com sucesso   vou guarda em um arquivo a nova infromações desta conta. <br/>*/
                     if(temp==5){
                         
                          outputDados.writeUTF("5");
                          outputDados.flush();
                        }else{
                            outputDados.writeUTF("-5");
                          outputDados.flush();
                        }
                    } 
                    else {
                        System.out.println("Conexão encerrada!!");
                         connection.close();
                        
                 }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (ExceptionInInitializerError e) {

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /** A função  Login receber  com  parâmetro  o seguinte dados :<br/>
      * @param  Coleção de cliente  do banco <br/> 
      * @param  String  com username  do  cliente <br/>
      * @param  String  com  senha do cliente <br>
      * o  sistema realizar um buca em sua basse de dados  o cliente   caso  encontado vai o seguinte returno <br>
      * @return  true  caso não encontado  vai ter o seguinte  retorno <br/>
      * @return false 
      */  
     public  boolean login (ArrayList<Cliente> clientesBanco, String username,String password){
         
         for(Cliente c: clientesBanco ){
             
              if(c.getUsername().contentEquals(username) && c.getPassword().contentEquals(password) ){
                 
                     return true; 
             }
     
         }
         
         return  false;
     }
    /**
     * a função a seguir realizaçãi a ação de verificra ser o cliente <br/>
     * esta ou na na base de dados do sistema, para isso rebe alguns parâmetros
     * que são :
     * @param Coleção de cliente do sistema <br/>
     * @param Cliente , no caso o novo cliente do sistema vai resulta em
     * retorno que poder ser ,<br/>
     * @return true caso o sistema encontre algum cliente já cadastrado <br/>
     * @return false caso o sistema não encontre algum cliente já cadastrado <br/>
     */
    public boolean clienteCadastrado(ArrayList<Cliente> clientesBanco, Cliente novo) {

        for (Cliente c : clientesBanco) {
            if (c.getUsername().contentEquals(novo.getUsername())
                    || c.getCpfCnpj().contains(novo.getCpfCnpj())
                    || c.getNascimento().contentEquals(novo.getNascimento())) {
                return false;
            }
        }
        return true;
    }

    /**
     * a função a seguir realizar a ação de verificar se o cliente <br/>
     * esta ou na na base de dados do sistema, para isso rebe alguns parâmetros
     * que são :
     *
     * @param Coleção de  contas  do sistema <br/>
     * @param Cliente  e  uma  conta <br/>
     *assim o sistema vai procurar se uma conta  já esta  cadastra  ou  não <br/>
     * @return true  caso  encontre a conta  já cadastra no  sistema <br/>
     * @return false caso não  encontre a conta  já cadastra no  sistema <br/>
     */
    public boolean contaCadastrada(ArrayList<Conta> contasBanco, Conta nova) {

        for (Conta cont : contasBanco) {
            if (cont.getAgencia().contentEquals(nova.getAgencia())
                    || cont.getNumero().contentEquals(nova.getNumero())
                    || cont.getTipo().contains(nova.getTipo())) {
                return false;
            }
        }
        return true;
    }

    /**
     * a função a seguir realizar a ação de verificar se a nova conta <br/>
     * esta ou na na base de dados do sistema, para isso rebe alguns parâmetros
     * que são :
     * @param Coleção  de clientes do  banco <br/>
     * @param String  com username  do cliente <br/>
     *  caso  encontre  o cliente  tem o seguinte retorno 
     * @return cliente. <br/>
     * caso não  econtre tenho o seguinte retorno
     * @return null . <br/>
     */
    public Cliente buscar(ArrayList<Cliente> clientesBanco, String username, String password) {
        for (Cliente c : clientesBanco) {
            if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
                Cliente achou = new Cliente(c.getUsername(), c.getPassword(), c.getNome(), c.getSexo(), c.getNascimento(), c.getCpfCnpj());
                return achou;
            }
        }
        return null;
    }

    /**
     * A função a seguir receber como parâmtros
     * <br/>
     * @param Coleção  de contas que o sistema  tem <br/>
     * @param String  com numero de identificação da conta <br/>
     * @param String  com a agencia que a  conta pertence <br/>
     * @param String  com o tipo  da  conta <br/>
     * com isso o sistema vai poder localizar um derterminada conta <br/>
     * caso encontre vou ter um retorno que é :<br/>
     * @return Conta ou null.
     */
    public Conta Pesquisar(ArrayList<Conta> contasBanco, String numero, String agencia, String tipo) {

        for (Conta cont : contasBanco) {

            if (cont.getNumero().equals(numero) && cont.getAgencia().equals(agencia) && cont.getTipo().equals(tipo)) {
                Conta achou = new Conta(cont.getIdCliente(), cont.getAgencia(), cont.getNumero(), cont.getTipo(), cont.getSaldo(),cont.getTipoConta());
                return achou;
            }
        }

        return null;
    }

    /**
     * A função de Deposita  vai ter um da operações básicas do sistema e para isso
     * vai receber os seguinte parêmetros<br/>
     * @param Coleção de clientes que  o sistema  tem <br/>
     * @param Coleção de  contas que o sistema tem <br/>
     * @param String  com   username do cliente <br/>
     * @param String  com a senha do cliente <br/>
     * @param String com o valaor que a conta vai receber <br/>
     * @param String com número de sua conta <br/>
     * @param String  com  a agencia que sua conta pertence <br/>
     * @param String  como tipo  de sua  conta <br/>
     * com isso o sistema vai somar ao saldo da conta o valor que voi informado.
     */
    public synchronized int Deposita(ArrayList<Cliente> clientesBanco, ArrayList<Conta> contasBanco,
            String username, String password, String valor,
            String numero, String agencia, String tipo) {

        Cliente aux = buscar(clientesBanco, username, password);
        Conta aux2 = Pesquisar(contasBanco, numero, agencia, tipo);

        if (aux == null) {
            return-4;
        } else if (aux2 == null) {
            
            return-4;
            
        } else {
            if (aux2.getIdCliente().equals(aux)) {
                double rs = Double.parseDouble(valor);
                double s = Double.parseDouble(aux2.getSaldo());
                double novosaldo;
                novosaldo = rs + s;
                String novo2 = Double.toString(novosaldo);
                aux2.setSaldo(novo2);
                contasBanco.add(aux2);
                return 4;
            } else {
                return-4;
            }
        }
    }

    /**
     * A função  de Transferir vai ter um da operações básicas do sistema e para isso
     * vai receber os seguinte parêmetros<br/>
     *
     * @param Coleção de clientes  do banco <br/>
     * @param Coleção de contas que se encontra cadastradas no sistema <br/>
     * @param String  com  o username  do  clientes <br/>
     * @param String  com a senha  do cliente <br/>
     * @param String  com  o valor  que  vai sair da conta de origem e vai para conta de destino <br/>
     * @param String  com número da  conta de origem <br/>
     * @param String  com  o tipo que é  conta de origem <br/>
     * @param String  com número da conta de destino <br/>
     * @param String  com a identificação da  agencia   que a conta de destino que pertence <br/>
     * @param String  com  o  tipo  da conta de destino <br>
     * com isso o sistema vai poder subtrair do saldo da conta de origem o valor
     * informdor,<br/>
     * e somar na conta de destino o valor que foi subtrarir da conta de origem. <br/>
     */
    public synchronized  int Transferir(ArrayList<Cliente> clientesBanco, ArrayList<Conta> contasBanco,
            String username, String password, String valor,
            String numero, String agencia, String tipo, String DestinoNumero,
            String agenciaDestino, String tipoDestino) {

        Cliente aux = buscar(clientesBanco, username, password);
        Conta origem = Pesquisar(contasBanco, numero, agencia, tipo);
        Conta destino = Pesquisar(contasBanco, DestinoNumero, agenciaDestino, tipoDestino);

        if (aux == null) {
            return -5;
        } else {
            if (origem == null || destino == null) {
                return -5;
                
            } else {
                double saldoOrigem = Double.parseDouble(origem.getSaldo());
                double saldoDestino = Double.parseDouble(destino.getSaldo());
                double qtd = Double.parseDouble(valor);
                double saldoFinal;
                if (saldoOrigem < qtd) {
                    return -5;

                } else {
                    double temp = saldoOrigem - qtd;
                    String ok = Double.toString(temp);
                    origem.setSaldo(ok);
                    try {
                        salvarContas(origem);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                           
                    double t = saldoDestino + qtd;
                    String m = Double.toString(t);
                    destino.setSaldo(m);
                    try {
                        salvarContas(destino);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return 5;
                }
            }
        }
    }
    /** A função a caminho realiza a ação de verificar se a pasta para guarda os 
     * dados  de contas e clientes  existe, <br/>
     * caso não exista cria se não cria a pasta. */
    private void caminho() {
        File caminhoCliente = new File("\\BancoCRESB-Server\\clientes");  /** verifica se a pasta existe. <br/>*/
        if (!caminhoCliente.exists()) {
            caminhoCliente.mkdirs();                                      /** caso não exista cria a pasta. <br/>*/
        } 
        File caminhoConta = new File("\\BancoCRESB-Server\\contas");      /**verifica se a pasta existe. <br/>*/
        if (!caminhoConta.exists()) {
            caminhoConta.mkdirs();                                        /**caso não exista cria a pasta. <br/>*/
        }
    }
    /**A função salvarCliente, vai realizar a seguinte ação, vai chamar a função privada  caminho, <br/> 
     * para verificar se a pasta para guarda  dados do cliente existe se não vai criar esta pasta ,<br/>
     * e depois disso  vai grava em um arquivo .txt esta  infromações. */
    private synchronized void salvarClientes(Cliente cliente) throws FileNotFoundException, IOException {
        caminho();                          /** cria o caminho caso ele não exista. <br/>*/
        ObjectOutputStream objectOutC = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("BancoCRESB-Server\\clientes" +cliente.getCpfCnpj()+ ".txt")));/** grava o objeto no caminho informado.<br/>*/	
        objectOutC.writeObject(cliente);    /** escreve o arquivo. <br/> */                                      
        objectOutC.flush();                
        objectOutC.close();               /** fecha em encerra a ação. <br/>*/
    }
    /**A função salvarContas, vai realizar a seguinte ação, vai chamar a função privada  caminho, <br/> 
     * para verificar se a pasta para guarda  dados das contas  existe se não vai criar esta pasta ,<br/>
     * e depois disso  vai grava em um arquivo .txt esta  infromações. */
    private synchronized void salvarContas(Conta conta) throws IOException {

        caminho();                       /** cria o caminho caso ele não exista. <br/>*/
        ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("BancoCRESB-Server\\clientes" + conta.getNumero() + ".txt"))); /**grava o objeto no caminho informado.<br/>*/			
        objectOut.writeObject(conta);    /**escreve o arquivo. <br/>*/
        objectOut.flush();
        objectOut.close();                /** fecha em encerra a ação. <br/>*/
    }

}
