package org.lib.nioserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.lib.business.LibraryFacadeService;
import org.lib.protocol.Disconnect;
import org.lib.protocol.LibraryCommand;
import org.lib.utils.LibraryException;
import org.lib.xmlconnection.JAXBUtils;

/**
 *
 * @author danecek
 */
class NIOServer implements Runnable {

//    private ExecutorService pool;
    private Selector selector;
    static final Logger logger = Logger.getLogger(NIOServer.class.getName());
    private Unmarshaller unmarshaller;
    private Marshaller marshaller;
    Map<SocketChannel, X> socketMap = new HashMap<>();

    class X {

        ByteBuffer bb = ByteBuffer.allocate(1024);

        void handleRead(SocketChannel sc) throws IOException, JAXBException {
            sc.read(bb);
            bb.flip();
            if (bb.remaining() >= 4) {
                int messLen = bb.getInt();
                if (bb.remaining() >= messLen) {
                    byte[] dst = new byte[messLen];
                    bb.get(dst);
                    //   String commstr = new String(dst, StandardCharsets.UTF_8);
                    LibraryCommand comm = (LibraryCommand) unmarshaller.unmarshal(new ByteArrayInputStream(dst));
                    info(comm.toString());
                    if (comm instanceof Disconnect) {
                        socketMap.remove(sc);
                        sc.close();
                        return;
                    }
                    Object result;
                    try {
                        result = comm.execute(LibraryFacadeService.getDefault());
                    } catch (LibraryException ex) {
                        result = ex;
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    marshaller.marshal(result, baos);
                    info(baos.toString(StandardCharsets.UTF_8.name()));
                    byte[] ba = baos.toByteArray();
                    ByteBuffer outbb = ByteBuffer.allocate(ba.length + 4);
                    outbb.putInt(ba.length);
                //    info(bb.toString());
                    outbb.put(ba);
                    outbb.flip();
                  //  info(outbb.toString());
                    while (outbb.remaining() > 0) {
                     //   info(outbb.toString());
                        sc.write(outbb);
                    }
                //    info("response sent");
                }

            }
            bb.compact();
        }
    }

    public NIOServer(int port) {
        try {
         //   pool = Executors.newCachedThreadPool();
            selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(port));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
          //  pool.submit(this);
            JAXBContext jc = JAXBUtils.createJAXBContext();
            unmarshaller = jc.createUnmarshaller();
            marshaller = jc.createMarshaller();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(NIOServer.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void info(String mess) {
        logger.info(mess);
    }
    ByteBuffer bb = ByteBuffer.allocate(1024);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Override
    public void run() {
        for (;;) {
            try {
                info("waiting on selector");
                selector.select();
                Iterator<SelectionKey> selKeysIt = selector.selectedKeys().iterator();
                while (selKeysIt.hasNext()) {
                    SelectionKey key = selKeysIt.next();
                    selKeysIt.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) != 0) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChanell = ssc.accept();
                        info("new client:" + socketChanell.getRemoteAddress());
                        socketChanell.configureBlocking(false);
                        socketChanell.register(selector, SelectionKey.OP_READ);
                        socketMap.put(socketChanell, new X());
                        continue;
                    }
                    if ((key.readyOps() & SelectionKey.OP_READ) != 0) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        socketMap.get(sc).handleRead(sc);
                    }
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(NIOServer.class.getName()).log(Level.SEVERE, null, ex);

            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
    }
}
