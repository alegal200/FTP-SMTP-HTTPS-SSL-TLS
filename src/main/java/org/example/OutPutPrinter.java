package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class OutPutPrinter {
    private OutputStream o ;
    private String page ;


    public OutPutPrinter(OutputStream out){
        o = out ;
        page = "";
    }

    private void startPage() throws IOException {

        page += "<!DOCTYPE html>\r\n";
        page += "<html> \r\n";
        page += "<body> \r\n";
        page += "<h1>VACANCIER FINDER</h1> \r\n";

    }
    private void endPage() throws IOException {
        page += "</body>\r\n";
        page += "</html>\r\n";
        page += "\r\n\r\n";

    }
    public void close() throws IOException  {
        o.close();
    }

    public void  firstPage() throws IOException {
        this.startPage();
        page += "<p>id du vac :</p> \r\n";
        page += "<form  method=\"GET\"> \r\n";
        page += "<input  name=\"id=\"> \r\n";
        page += "<input type=\"submit\" value=\"Send\"> \r\n";
        page += "<form> \r\n";

        this.endPage();

    }

    public void execpage() throws IOException {
        this.startPage();
        page += "<p>CONGRATS you are register !</p>";
        this.endPage();
        this.send();
    }


    public void personnenotherepage() throws IOException {
        this.startPage();
        page += "<p>this person is not in vacation camp </p>";
        this.endPage();
        this.send();
    }


    public void erreursend() throws IOException {


        page += "<!DOCTYPE html>";
        page += "<html>";
        page += "<body>";
        page += "<h1>VACANCIER FINDER</h1>";
        page += "<p>erreur du vac ( ID ou contenu) </p>";
        this.endPage();
        int nbrcar  = page.getBytes().length;
        page = ("Content-Length: "+nbrcar  +"\r\n\r\n" ) +page ;
        page = "HTTP/1.1 898 id introuvable "+"\r\n" + page;
        o.write(page.getBytes());
        o.flush();
        page = "";

    }

    public void erreurgeneralsend() throws IOException {


        page += "<!DOCTYPE html>";
        page += "<html>";
        page += "<body>";
        page += "<h1>VACANCIER FINDER</h1>";
        page += "<p>erreur ?????</p>";
        this.endPage();
        int nbrcar  = page.getBytes().length;
        page = ("Content-Length: "+nbrcar  +"\r\n\r\n" ) +page ;
        page = "HTTP/1.1 898 id introuvable "+"\r\n" + page;
        o.write(page.getBytes());
        o.flush();
        page = "";

    }




    public void send()throws IOException {

        int nbrcar  = page.getBytes().length;

        System.out.println("/"+nbrcar+"/");
        //o.write( "HTTP/1.1 200 ok ".getBytes() ) ;
        //o.write( "Content-Type: text/html; charset=UTF-8".getBytes());
        //o.write( ("Content-Length:"+nbrcar   ).getBytes() );

        page = ("Content-Length: "+nbrcar  +"\r\n\r\n" ) +page ;
        page = "HTTP/1.1 200 ok "+"\r\n" + page;
        o.write(page.getBytes());
        o.flush();
        page = "";

    }



}