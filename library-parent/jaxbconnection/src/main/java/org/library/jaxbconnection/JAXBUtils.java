/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.library.jaxbconnection;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lib.protocol.Books;
import org.lib.protocol.CreateBook;
import org.lib.protocol.DeleteBooks;
import org.lib.protocol.Disconnect;
import org.lib.protocol.GetBooks;
import org.lib.protocol.GetReaders;
import org.lib.protocol.Ok;
import org.lib.protocol.Readers;

/**
 *
 * @author danecek
 */
public class JAXBUtils {

    static final Logger logger = Logger.getLogger(JAXBUtils.class.getName());

    public static JAXBContext createJAXBContext() {
        try {
            return JAXBContext.newInstance(GetReaders.class,
                    GetBooks.class,
                    Readers.class,
                    Books.class,
                    CreateBook.class,
                    DeleteBooks.class,
                    Disconnect.class,
                    Ok.class);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void marshallMessage(Object message, Marshaller marshaller, DataOutputStream dos) throws JAXBException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(message, baos);
        byte[] buff = baos.toByteArray();
        dos.writeInt(buff.length);
        dos.write(buff);
        dos.flush();
    }

    public static Object unmarshalMessage(Unmarshaller u, DataInputStream ois) throws IOException, JAXBException {
        int len = ois.readInt();
        byte[] buff = new byte[len];
        ois.readFully(buff);
        String xmlMessage = new String(buff, StandardCharsets.UTF_8);
        logger.log(Level.INFO, "xml message: {0}", xmlMessage);
        Object message = u.unmarshal(new ByteArrayInputStream(buff));
        logger.log(Level.INFO, "binary message: {0}", message);
        return message;
    }
}
