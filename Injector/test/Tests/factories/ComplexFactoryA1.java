/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.factories;

import Tests.common.ImplementationA1;
import Tests.interfaces.InterfaceB;
import Tests.interfaces.InterfaceC;

import common.DependencyException;
import complex.Factory;

/**
 *
 * @author Usuario
 */
public class ComplexFactoryA1 implements Factory<ImplementationA1> {

    @Override
    public ImplementationA1 create(Object... parameters) throws DependencyException {
        InterfaceB b;
        InterfaceC c;
        try{
            b =(InterfaceB) parameters[0];
            c =(InterfaceC) parameters[1];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        return new ImplementationA1(b,c);
    }
    
}
