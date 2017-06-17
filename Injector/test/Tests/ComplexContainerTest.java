package Tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Tests.common.ImplementationA1;
import Tests.common.ImplementationB1;
import Tests.common.ImplementationC1;
import Tests.common.ImplementationD1;
import Tests.factories.ComplexFactoryA1;
import Tests.factories.ComplexFactoryB1;
import Tests.factories.ComplexFactoryC1;
import Tests.factories.ComplexFactoryD1;
import Tests.interfaces.InterfaceA;
import Tests.interfaces.InterfaceB;
import Tests.interfaces.InterfaceC;
import Tests.interfaces.InterfaceD;
import common.DependencyException;
import complex.Container;
import complex.Injector;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class ComplexContainerTest {
    Injector injector;
    
    InterfaceD inD;
    InterfaceC inC;
    InterfaceB inB;
    InterfaceA inA;
    
    public ComplexContainerTest() {
    }
    
    @Before
    public void setUp(){
        this.injector = new Container();
    }
    
    @Test
    public void testImplD() throws DependencyException{
        injector = new Container();
        injector.registerConstant(Integer.class , 42);
        injector.registerFactory(InterfaceD.class, new ComplexFactoryD1() , Integer.class);
        inD = injector.getObject(InterfaceD.class);
        assertTrue(ImplementationD1.class.isInstance(inD));
        ImplementationD1 d1 = (ImplementationD1) inD;
        assertEquals(d1.i,42);
    }
    @Test
    public void testImplC() throws DependencyException{
        injector = new Container();
        injector.registerConstant(String.class , "hello");
        injector.registerFactory(InterfaceC.class, new ComplexFactoryC1() , String.class);
        inC = injector.getObject(InterfaceC.class);
        assertTrue(ImplementationC1.class.isInstance(inC));
        ImplementationC1 c1 = (ImplementationC1) inC;
        assertEquals(c1.s,"hello");
    }
    @Test
    public void testImplB() throws DependencyException{
        injector = new Container();
        inD = new ImplementationD1(1);
        injector.registerConstant(InterfaceD.class , inD);
        injector.registerFactory(InterfaceB.class, new ComplexFactoryB1() , InterfaceD.class);
        inB = injector.getObject(InterfaceB.class);
        assertTrue(ImplementationB1.class.isInstance(inB));
        ImplementationB1 b1 = (ImplementationB1) inB;
        assertEquals(b1.d,inD);
    }
    
    @Test
    public void testImplA() throws DependencyException{
        injector = new Container();
        
        inD = new ImplementationD1(1);
        inC = new ImplementationC1("ImplC");
        inB = new ImplementationB1(inD);
        
        injector.registerConstant(InterfaceB.class , inB);
        injector.registerConstant(InterfaceC.class , inC);
        
        injector.registerFactory(InterfaceA.class, new ComplexFactoryA1(), InterfaceB.class, InterfaceC.class);
        inA = (InterfaceA) injector.getObject(InterfaceA.class);
        assertTrue(ImplementationA1.class.isInstance(inA));
        
        ImplementationA1 a1 = (ImplementationA1) inA;
        assertEquals(a1.b,inB);
        assertEquals(a1.c,inC);
    }
    
    @Test(expected = DependencyException.class)    
    public void testErrorConstants() throws DependencyException{
        injector.registerConstant(String.class, "1st constant");
        injector.registerConstant(String.class, "repeat constant");
    }
    @Test(expected = DependencyException.class)    
    public void testErrorFactories() throws DependencyException{
        injector.registerConstant(Integer.class, 1);
        injector.registerConstant(Integer.class, 2);
        injector.registerFactory(InterfaceD.class, new ComplexFactoryD1(),Integer.class);
        injector.registerFactory(InterfaceD.class, new ComplexFactoryD1(),Integer.class);
    }
    
    @Test(expected = DependencyException.class)    
    public void testTypeErrorFactories() throws DependencyException{
        injector.registerConstant(String.class, "1st constant");
        injector.registerFactory(InterfaceD.class, new ComplexFactoryD1(),String.class);
        injector.getObject(InterfaceD.class);

    }
}
