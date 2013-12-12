/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.nioserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danecek
 */
public class BusinessOperationTask implements Runnable {

    SocketChannel sc;

    public BusinessOperationTask(SocketChannel sc) {
        this.sc = sc;
    }
    ByteBuffer bb = ByteBuffer.allocate(1024);

    @Override
    public void run() {
        try {
            Object result;

            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
            sc.read(bb);

            bb.flip();
            System.out.println(bb);
            System.out.println(bb.getInt());
            System.out.println(bb);


//
//            LibraryCommand command = (LibraryCommand) NIOProtocolSupport.getInstance().array2Object(baos.toByteArray());
//            try {
//                result = command.execute(LibraryFacadeService.getDefault());
//            } catch (LibraryException ex) {
//                result = ex;
//            }
//            byte[] byteArray = NIOProtocolSupport.getInstance().object2Array(result);
//            bb = ByteBuffer.wrap(byteArray);
//            while (bb.hasRemaining()) {
//                sc.write(bb);
//            }
        } catch (IOException ex) {
            Logger.getLogger(BusinessOperationTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
