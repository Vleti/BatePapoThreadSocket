/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pac.Comunicacao.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class ClienteConsumidor extends Thread{
    Socket ss;
    MensagemBuffer B;
    BufferedReader lei;

    public ClienteConsumidor(MensagemBuffer b, Socket ss) {
        super();
        B = b;
        this.ss=ss;
        try {
            lei=new BufferedReader(new InputStreamReader(ss.getInputStream()));
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        super.start();
    }

    public void run() {
        String mensagem;
        while (true) {
            try {
                mensagem = lei.readLine();
                B.put(mensagem);
                //System.out.println("Valor Consumido: "+mensagem);
            } catch (IOException ex) {
                ex.getStackTrace();
            }
            
        }
    }
}  