package jvm;

import org.joor.Reflect;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Ref;

import static org.joor.Reflect.*;

/**
 *
 *һ���°빦����Java����⣺
 *
 * https://droidyue.com/blog/2017/01/09/joor-examples/
 *
 * https://github.com/jOOQ/jOOR/blob/master/README.md
 *
 * Created by user on 2018/8/4.
 */


public class joor {

    public interface StringProxy{
        String substring(int beginIndex);
    }

    public static class Address{
        private String street;

        public Address(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }
    }

    public static class Employee{
        private Address address;

        public Employee(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    public static class Department{
        private Employee[] employees;

        public Department(Employee[] employees) {
            this.employees = employees;
        }

        public Employee[] getEmployees() {
            return employees;
        }
    }

    public static void main(String[] args) {

        final String CONST = "hello world";

        String world = on("java.lang.String")
                .create(CONST)
                .call("substring",6)
                .call("toString")
                .get();
        String old = "hello world".substring(6);
        System.out.println("old = " + old);
        System.out.println("world = " + world);

        // �����������ʲô��
        String substring = on("java.lang.String")
                .create(CONST)
                .as(StringProxy.class)
                .substring(6);

        System.out.println("substring = " + substring);


        // 1. ����ʵ��
        String string = Reflect.on(String.class).create(CONST).get();
        System.out.println("string = " + string);

        // 2. �������ԣ�public,protected,package,private���ɣ�
        char pathSeparatorChar = Reflect.on(File.class).create("/sdcard/droidyue.com").field("pathSeparatorChar").get();
        int prefixLength = Reflect.on(File.class).create("/sdcard/droidyue.com").field("prefixLength").get();
        System.out.println("pathSeparatorChar = " + pathSeparatorChar);
        System.out.println("prefixLength = " + prefixLength);

        // 3. �޸�����(final����Ҳ�����޸�)
        String path = Reflect.on(File.class).create("/sdcard/droidyue.com").set("path","fakePath").get("path");
        System.out.println("path = " + path);

        Employee[] employees = new Employee[5];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(new Address("street num:"+i));
        }

        Department department = new Department(employees);
        Employee[] employees1;

        /*
        *
        * �Ա���ͨ������á���Reflect�ĺô���
        *
        * Reflect ����࣡
        * */
        System.out.println("�����ȡstree��");
        try {
            Method getEmploMethod = department.getClass().getDeclaredMethod("getEmployees");
            employees1 = (Employee[])getEmploMethod.invoke(department);

            for (Employee employee : employees1) {
                Method m1 = employee.getClass().getDeclaredMethod("getAddress");
                Address address = (Address)m1.invoke(employee);

                Method m2 = address.getClass().getDeclaredMethod("getStreet");
                String street = (String)m2.invoke(address);

                System.out.println("street = " + street);
            }

        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("ʹ�� ʹ��Relect��ȡstree��");

        // ʹ��Relect��ȡstree
        employees1 = Reflect.on(department).call("getEmployees").get();
        for (Employee employee : employees1) {
            String street = Reflect.on(employee).call("getAddress").call("getStreet").get();
                                                                    // �˴����ص�ʵ���� Reflect��Address����ķ�װ��
            System.out.println("street = " + street);
        }

    }
}
