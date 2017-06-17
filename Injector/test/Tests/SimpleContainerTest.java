/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Tests.common.ImplementationA1;
import Tests.common.ImplementationC1;
import Tests.common.ImplementationB1;
import Tests.common.ImplementationD1;
import Tests.factories.SimpleFactoryA1;
import Tests.interfaces.InterfaceC;
import Tests.interfaces.InterfaceD;
import Tests.interfaces.InterfaceB;
import Tests.factories.SimpleFactoryD1;
import common.DependencyException;
import Tests.factories.SimpleFactoryB1;
import Tests.factories.SimpleFactoryC1;
import Tests.interfaces.InterfaceA;
import simple.Container;
import simple.Injector;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class SimpleContainerTest {
    Injector injector;
    
    InterfaceD inD;
    InterfaceC inC;
    InterfaceB inB;
    InterfaceA inA;
    
            
    public SimpleContainerTest() {
    }
    @Before
    public void setUp(){
        this.injector = new Container();
    }
    @Test
    public void testImplD() throws DependencyException{

        injector.registerConstant("I", 42);
        injector.registerFactory("D",new SimpleFactoryD1(),"I");
        inD = (InterfaceD) injector.getObject("D");
        assertTrue(ImplementationD1.class.isInstance(inD));
        
        ImplementationD1 d1 = (ImplementationD1) inD;
        assertEquals(d1.i, 42);
    }
    
    @Test
    public void testImplC() throws DependencyException{

        injector.registerConstant("I", "hello");
        injector.registerFactory("C",new SimpleFactoryC1(),"I");
        inC = (InterfaceC) injector.getObject("C");
        assertTrue(ImplementationC1.class.isInstance(inC));
        
        ImplementationC1 c1 = (ImplementationC1) inC;
        assertEquals(c1.s, "hello");
    }
    
    @Test
    public void testImplB() throws DependencyException{

        InterfaceD inD = new ImplementationD1(1);
        injector.registerConstant("I", inD);
        injector.registerFactory("B",new SimpleFactoryB1(),"I");
        inB = (InterfaceB) injector.getObject("B");
        assertTrue(ImplementationB1.class.isInstance(inB));
        
        ImplementationB1 b1 = (ImplementationB1) inB;
        assertEquals(b1.d, inD);
    }   
    
    @Test
    public void testImplA() throws DependencyException{

        inD = new ImplementationD1(1);
        inC = new ImplementationC1("ImplC");
        inB = new ImplementationB1(inD);
        injector.registerConstant("IB", inB);
        injector.registerConstant("IC", inC);
        injector.registerFactory("A",new SimpleFactoryA1(),"IB","IC");
        inA = (InterfaceA) injector.getObject("A");
        assertTrue(ImplementationA1.class.isInstance(inA));
        
        ImplementationA1 a1 = (ImplementationA1) inA;
        assertEquals(a1.b, inB);
        assertEquals(a1.c, inC);
    }   
    @Test(expected = DependencyException.class)    
    public void testErrorConstants() throws DependencyException{
        injector.registerConstant("I", "first constant");
        injector.registerConstant("I", "repeat constant");
    }
    @Test(expected = DependencyException.class)    
    public void testErrorFactories() throws DependencyException{
        injector.registerConstant("I", 1);
        injector.registerConstant("O", 2);
        injector.registerFactory("A", new SimpleFactoryD1(),"I");
        injector.registerFactory("A", new SimpleFactoryD1(),"O");
    }
    
    @Test(expected = DependencyException.class)    
    public void testTypeErrorFactories() throws DependencyException{
        injector.registerConstant("I", "Hello");
        injector.registerFactory("A", new SimpleFactoryD1(),"I");
        injector.getObject("A");

    }
}
