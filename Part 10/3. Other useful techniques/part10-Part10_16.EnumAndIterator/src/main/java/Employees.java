/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Employees {

    private List<Person> employees;

    public Employees() {
        this.employees = new ArrayList<>();
    }

    public void add(Person personToAdd) {
        this.employees.add(personToAdd);
    }

    public void add(List<Person> peopleToAdd) {
        this.employees.addAll(peopleToAdd);
    }

    public void print() {
        Iterator<Person> iterator = this.employees.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person);
        }
    }

    public void print(Education education) {
        Iterator<Person> iterator = this.employees.iterator();
        while(iterator.hasNext()){
            Person person = iterator.next();
            if(person.getEducation().equals(education)){
                System.out.println(person);
            }
        }

    }
    public void fire(Education education){
        Iterator<Person> iterator = this.employees.iterator();
        while(iterator.hasNext()){
            Person person = iterator.next();
            if(person.getEducation().equals(education)){
               iterator.remove();
            }
        }
    }

}
