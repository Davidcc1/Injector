/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests.factories;

import Tests.common.ImplementationB1;
import Tests.interfaces.InterfaceD;
import common.DependencyException;
import complex.Factory;

/**
 *
 * @author Usuario
 */
public class ComplexFactoryB1 implements Factory<ImplementationB1> {

    @Override
    public ImplementationB1 create(Object... parameters) throws DependencyException {
        InterfaceD d;
        try{
            d =(InterfaceD) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        return new ImplementationB1(d);
    }
    
}