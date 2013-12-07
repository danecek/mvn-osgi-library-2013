/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.jaxbserver;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.model.BookId;
import org.lib.protocol.Books;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBooks;
import org.lib.protocol.Disconnect;
import org.lib.protocol.GetBooks;
import org.lib.protocol.GetReaders;
import org.lib.protocol.LibraryCommand;
import org.lib.protocol.Ok;
import org.lib.protocol.Readers;
import org.lib.utils.LibraryException;

/**
 *
 * @author danecek
 */
public class JAXBServerThread extends Thread {
    
    static final Logger logger = Logger.getLogger(JAXBServerThread.class.getName());
    static Class[] preloaded = {
        Book.class,
        BookId.class,
        GetReaders.class,
        GetBooks.class
    };
    JAXBContext jc;
    Unmarshaller u;
    Marshaller m;
    
    public JAXBServerThread() {
        try {
            jc = JAXBContext.newInstance(GetReaders.class,
                    GetBooks.class,
                    Readers.class,
                    Books.class,
                    CreateBook.class,
                    DeleteBooks.class,
                    Ok.class);
            u = jc.createUnmarshaller();
            m = jc.createMarshaller();

            //   new Book(new BookId(1), null);
            //        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            //            @Override
            //            public void uncaughtException(Thread thread, Throwable thrwbl) {
            //                System.out.println("internal error!!!");
            //        });
            //        });
        } catch (JAXBException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            ServerSocket ssocket = new ServerSocket(LibraryCommand.PORT);
            logger.log(Level.INFO, "waiting for client");
            try (Socket s = ssocket.accept();
                    InputStream is = s.getInputStream();
                    DataInputStream ois = new DataInputStream(is);
                    PrintWriter oos = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8))) {
                char[] cbuf = new char[1000];
                for (;;) {
                    logger.log(Level.INFO, "waiting for command");
                    int l = ois.readInt();
                    byte[] buf = new byte[l];
                    ois.readFully(buf);
                    logger.log(Level.INFO, "command: {0}", new String(buf));
                    LibraryCommand comm = (LibraryCommand) u.unmarshal(new ByteArrayInputStream((buf)));
                    logger.log(Level.INFO, "command: {0}", comm);
                    if (comm instanceof Disconnect) {
                        break;
                    }
                    Object result;
                    try {
                        result = comm.execute(LibraryFacade.getDefault());
                    } catch (LibraryException ex) {
                        result = ex;
                    }
                    logger.log(Level.INFO, "result: {0}", result);
                    m.marshal(result, oos);
                    oos.flush();
                }
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
