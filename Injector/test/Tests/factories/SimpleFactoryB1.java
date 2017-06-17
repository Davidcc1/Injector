/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.factories;

import common.DependencyException;
import Tests.common.ImplementationB1;
import simple.Factory;
import Tests.common.ImplementationB1;
import Tests.interfaces.InterfaceD;

/**
 *
 * @author Usuario
 */
public class SimpleFactoryB1 implements Factory{

    @Override
    public Object create(Object... param) throws DependencyException {
        InterfaceD d;
        try{
         
            d = (InterfaceD) param[0];
            
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationB1(d);
    }
}

