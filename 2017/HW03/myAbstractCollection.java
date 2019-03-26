
import java.util.AbstractCollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author furkan
 * @param <E>
 */
public abstract class myAbstractCollection<E> extends AbstractCollection<E>{
    
    
    public void appendAnything(myAbstractCollection<E> other){
        this.addAll(other);
    }

    
    
    
}
