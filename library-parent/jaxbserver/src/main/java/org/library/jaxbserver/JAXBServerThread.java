/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.jaxbserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
import org.library.impl.JAXBConnection;

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
    Unmarshaller unmarshaller;
    Marshaller marshaller;

    public JAXBServerThread() {
        try {
            jc = JAXBContext.newInstance(GetReaders.class,
                    GetBooks.class,
                    Readers.class,
                    Books.class,
                    CreateBook.class,
                    DeleteBooks.class,
                    Ok.class);
            unmarshaller = jc.createUnmarshaller();
            marshaller = jc.createMarshaller();
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
                    DataInputStream ois = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream())) {
                for (;;) {
                    LibraryCommand comm = (LibraryCommand) JAXBConnection.unmarshalMessage(ois, unmarshaller);
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
                    JAXBConnection.marshallObject(result, marshaller, dos);
                }
            } catch (JAXBException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
