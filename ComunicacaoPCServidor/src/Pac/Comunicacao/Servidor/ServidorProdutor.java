/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pac.Comunicacao.Servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class ServidorProdutor extends Thread{
    Socket socket;
    MensagemBuffer B;
    PrintWriter pw;
    
    public ServidorProdutor(MensagemBuffer b, Socket socket) {
        super();
        B = b;
        this.socket=socket;
        try {
            synchronized(this.socket){
                   pw=new PrintWriter(this.socket.getOutputStream());
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        super.start();
    }
    
    public void enviarMensagem(String mensagem){
        synchronized(B){
            B.put(mensagem);
        }
    }

    public void run() {
        while (true) {
            synchronized (B) {
                pw.println(B.get());
                pw.flush();
            }
        }
    }
}