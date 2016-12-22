package hackaton.com.br.hackatonapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import hackaton.com.br.hackatonapp.interfaces.MessageInterface;

    public class Client {

        private static final long serialVersionUID = 1L;
        private Socket socket;
        private OutputStream ou ;
        private Writer ouw;
        private BufferedWriter bfw;
        private String txtIP;
        private String txtPorta;
        private String txtNome;
        private MessageInterface messageInterface;

        public Client(){
            txtIP = "192.168.43.122";
            txtPorta = "12345";
            txtNome = "Cliente";
        }

        /***
         * Método usado para conectar no server socket, retorna IO Exception caso dê algum erro.
         * @throws IOException
         */
        public void conectar() throws IOException{

            socket = new Socket(txtIP,Integer.parseInt(txtPorta));
            ou = socket.getOutputStream();
            ouw = new OutputStreamWriter(ou);
            bfw = new BufferedWriter(ouw);
            bfw.write(txtNome+"\r\n");
            bfw.flush();
        }

        /***
         * Método usado para enviar mensagem para o server socket
         * @param msg do tipo String
         * @throws IOException retorna IO Exception caso dê algum erro.
         */
        public void enviarMensagem(String msg) throws IOException{
            if(msg.equals("Sair")){
                bfw.write("Desconectado \r\n");
            }else{
                bfw.write(msg+"\r\n");
            }
            bfw.flush();
        }

        public void escutar() throws IOException{
            InputStream in = socket.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader bfr = new BufferedReader(inr);
            String msg = "";

            while(!"Sair".equalsIgnoreCase(msg))
                if(bfr.ready()){
                    msg = bfr.readLine();
                    if(msg.equals("Sair"))
                        messageInterface.receiveMessage("Servidor caiu! \r\n");
                    else
                        messageInterface.receiveMessage(msg);
                }
        }

        /***
         * Método usado quando o usuário clica em sair
         * @throws IOException retorna IO Exception caso dê algum erro.
         */
        public void sair() throws IOException{

            enviarMensagem("Sair");
            bfw.close();
            ouw.close();
            ou.close();
            socket.close();
        }

        public void setMessageInterface(MessageInterface messageInterface) {
            this.messageInterface = messageInterface;
        }
    }
