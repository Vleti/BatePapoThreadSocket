/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pac.Comunicacao.Cliente;

import java.util.Scanner;

/**
 *
 * @author felip
 */
public class PrincipalCliente {

    public static void main(String[] args) {
        ClienteProdutor clienteProdutor = null;
        ClienteConsumidor clienteConsumidor;

        MensagemBuffer mensagensClienteEnvio = new MensagemBuffer(10);
        MensagemBuffer mensagensClienteRecepcao = new MensagemBuffer(10);
        ClienteSocket clienteSocket = new ClienteSocket("127.0.0.1", 8889);

        clienteSocket.conect();

        if (clienteSocket.getSocket() != null) {
            clienteConsumidor = new ClienteConsumidor(mensagensClienteRecepcao, clienteSocket.getSocket());
            clienteProdutor = new ClienteProdutor(mensagensClienteEnvio, clienteSocket.getSocket());

        }
        
        Scanner msg;
        String nome="";
        String mensagem="";
        try{
               msg= new Scanner(System.in);
               System.out.println("Informe seu Nome:");
               nome=msg.nextLine();
               
               System.out.println("Insira a mesagem que deseja enviar ao Servidor:");
               mensagem=msg.nextLine();
           }catch(Exception e){
               e.printStackTrace();
           }
            
        clienteProdutor.enviarMensagem("Olá meu nome é "+nome+", "+mensagem);
        
        System.out.println("\nMensagem do servidor: "+mensagensClienteRecepcao.get());
    }
}
