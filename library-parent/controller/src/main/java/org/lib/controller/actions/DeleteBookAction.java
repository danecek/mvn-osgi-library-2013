/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lib.controller.actions;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lib.business.LibraryFacade;
import org.lib.model.Book;
import org.lib.utils.LibraryException;
import org.lib.view.MainFrame;

/**
 *
 * @author danecek
 */
public class DeleteBookAction extends AbstractLibraryAction {
    
    private DeleteBookAction() {
        super("Delete Book"); // todo
    }
    
    public static DeleteBookAction getInstance() {
        return DeleteBookActionHolder.INSTANCE;
    }
    
    private static class DeleteBookActionHolder {
        private static final DeleteBookAction INSTANCE = 
                new DeleteBookAction();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      Book b =  MainFrame.getInstance().getSelectedBook();
      if (b == null) {
          MainFrame.getInstance().showError("Neni vybrana"); // todo
          
      } else {
          try {
              LibraryFacade.getDefault().deleteBook(b.getId());
              MainFrame.getInstance().refresh();
          } catch (LibraryException ex) {
              MainFrame.getInstance().showError(ex);
          }
      }
//        new DeleteBookDialog();
    }

    @Override
    public boolean shouldEnabled() {
        return MainFrame.getInstance().getSelectedBook() != null;
    }
    
    
    
    
}
