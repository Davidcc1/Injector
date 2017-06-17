/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class Container implements Injector {
    String name;
    Object value;
    Map cMap;
    Map lm;
    
    public Container(){
        this.cMap = new HashMap<String, Object>();
        this.lm = new HashMap<String, FactoryContainer>();
    }
    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        try{
            if(!cMap.containsKey(name)){
                cMap.put(name, value);
            }else throw new DependencyException("Clau repetida!");
        }catch(Exception e){
            throw new DependencyException("Clau repetida!" + e);
        }
    }

    @Override
    public void registerFactory(String name, Factory creator, String... param) throws DependencyException {
        try{
            //hm.put(name,creator.create(hm.get(param[0])));
            FactoryContainer fc;
            fc = new FactoryContainer(creator, param);
            if(!lm.containsKey(name)){
                lm.put(name, fc);
            }else throw new DependencyException("Error! Clau repetida");
        }catch(Exception e){
            throw new DependencyException("Error! "+e);
        }    
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        Object v = null;
        try{
            if(cMap.containsKey(name)){
                v = cMap.get(name);
            }else{
                FactoryContainer fc = (FactoryContainer) lm.get(name);
                
                Object[] params = fc.paramFromFactory(( Object[] )fc.param, cMap);
                v = fc.fac.create(params);
            }
            System.out.println(v);

        }catch(Exception e){
            throw new DependencyException(e);
        }    
        return v;
    }
    
}
