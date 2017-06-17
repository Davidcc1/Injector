/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.DependencyException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Container  implements Injector{
    
    Map cMap;
    Map lm;
    public Container(){
        this.cMap = new HashMap<>();
        this.lm = new HashMap<>();
    }
    
    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        try{
            if(!cMap.containsKey(name)){
                cMap.put(name, value);
            }else throw new DependencyException("Clau repetida!");
        }catch(Exception e){
            throw new DependencyException(e);
        }
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
        try{
            FactoryContainer fc;
            fc = new FactoryContainer(creator, parameters);
            if(!lm.containsKey(name)){
                lm.put(name, fc);
            }else throw new DependencyException("Error! Clau repetida");
        }catch(Exception e){
            throw new DependencyException("Error! " + e);
        }  
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
        E v = null;
        try{
            if(cMap.containsKey(name)){
                v = (E) cMap.get(name);
            }else{
                FactoryContainer fc = (FactoryContainer) lm.get(name);
                
                Object[] params = fc.paramFromFactory((Object[])fc.param, cMap);
                
                v = (E) fc.fac.create(params);
            }
            System.out.println(v);

        }catch(Exception e){
            throw new DependencyException(e);
        }    
        return v;
    }   
}
