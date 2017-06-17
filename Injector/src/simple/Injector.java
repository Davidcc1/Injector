/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import simple.Factory;
import common.DependencyException;
/**
 *
 * @author Usuario
 */
public interface Injector {
    void registerConstant(String name, Object value) throws DependencyException;
    
    void registerFactory(String name, Factory creator, String... param) throws DependencyException;
    
    Object getObject(String name) throws DependencyException;
}
