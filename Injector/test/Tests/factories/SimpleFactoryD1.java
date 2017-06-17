/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.factories;

import common.DependencyException;
import Tests.common.ImplementationD1;
import simple.Factory;
import Tests.common.ImplementationD1;

/**
 *
 * @author Usuario
 */
public class SimpleFactoryD1 implements Factory{

    @Override
    public Object create(Object... param) throws DependencyException {
        int i;
        try{
         
            i = (int) param[0];
            
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
}
