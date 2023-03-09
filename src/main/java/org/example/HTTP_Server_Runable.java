package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HTTP_Server_Runable implements  Runnable {
    private int id = 0 ;
    private Socket ts ;
    private boolean ispost = false ;
    private  int content = 0 ;

    public HTTP_Server_Runable(Socket tts) {
        ts= tts ;

    }

    @Override
    public void run() {
        try{

            BufferedReader buffRead = new BufferedReader(new InputStreamReader(ts.getInputStream())) ;
            OutputStream client_output = ts.getOutputStream() ;
            OutPutPrinter output_client_print = new OutPutPrinter(client_output);
            String s ;
            while ( ( s = buffRead.readLine() ) != null ) {

                System.out.println(s);
                if(s.startsWith("GET")){
                    ///end id parsing
                    if(id ==0){
                        output_client_print.firstPage();

                    }


                }
                else  if(s.startsWith("POST")){
                      //  output_client_print.personnenotherepage();

                        while ( ( s = buffRead.readLine() ) != null ) {
                                System.out.println(s);
                                if(s.startsWith("Content-Length:")) {
                                content = Integer.parseInt(s.split("Content-Length: ")[1]);
                                break;
                            }
                                output_client_print.firstPage();
                        }
                    }

            }

            if(content > 0){
                String mystring = buffRead.readLine();
                System.out.println(mystring);
            }

            buffRead.close();
            output_client_print.close();
            ts.close();

            }catch (Exception exp){
                exp.printStackTrace();
            }
        }

}