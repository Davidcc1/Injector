/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.factories;

import common.DependencyException;
import Tests.common.ImplementationA1;
import simple.Factory;
import Tests.common.ImplementationA1;
import Tests.interfaces.InterfaceB;
import Tests.interfaces.InterfaceC;

/**
 *
 * @author Usuario
 */
public class SimpleFactoryA1 implements Factory{

    @Override
    public Object create(Object... param) throws DependencyException {
        InterfaceB b;
        InterfaceC c;
        try{
         
            b = (InterfaceB) param[0];
            c = (InterfaceC) param[1];
            
            
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationA1(b,c);
    }
}
