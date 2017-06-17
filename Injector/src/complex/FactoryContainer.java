/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.AbsFactoryContainer;
import java.util.Map;


/**
 *
 * @author Usuario
 */
public class FactoryContainer extends AbsFactoryContainer {
    
    Factory fac;
    Class<?>[] param;
    
    public FactoryContainer(Factory fac, Class<?>... param){
        this.fac = fac;
        this.param = param;
    }
    @Override
    public Object[] paramFromFactory( Object[] params, Map cMap){
         return super.paramFromFactory(params, cMap);
    }
}