/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pac.Comunicacao.Servidor;

import java.util.Scanner;

/**
 *
 * @author felip
 */
public class PrincipalServidor {
    public static void main(String[] args) {
        
        ServidorProdutor servidorProdutor = null;
        ServidorConsumidor servidorConsumidor;
        
        MensagemBuffer mensagensServidorEnvio=new MensagemBuffer(10);
        MensagemBuffer mensagensServidorRecepcao=new MensagemBuffer(10);
        ServidorSocket servidorSocket=new ServidorSocket(8889);
        servidorSocket.conect();
        
        if (servidorSocket.getSocket() != null) {
            servidorProdutor = new ServidorProdutor(mensagensServidorEnvio, servidorSocket.getSocket());
            servidorConsumidor = new ServidorConsumidor(mensagensServidorRecepcao, servidorSocket.getSocket());
        }
        
        System.out.println("Mensagem do cliente: "+mensagensServidorRecepcao.get()+"\n");
        
        Scanner msg;
        String nome="";
        String mensagem="";
        try{
               msg= new Scanner(System.in);
               System.out.println("Informe seu Nome:");
               nome=msg.nextLine();
               
               System.out.println("Insira a mesagem que deseja enviar ao Cliente:");
               mensagem=msg.nextLine();
           }catch(Exception e){
               e.printStackTrace();
           }
        
        servidorProdutor.enviarMensagem("Olá meu nome é "+nome+", "+mensagem);
         
        while(true){
            System.out.println(mensagensServidorRecepcao.get());
            Scanner msg2;
            String mensg;
            msg2=new Scanner(System.in);
            System.out.print("\nMensagem: ");
            mensg=msg2.nextLine();
            servidorProdutor.enviarMensagem(nome+": "+mensg);
        }
    }
}
