/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
public class Person {
    private String name;
    private Education edu;
    
    public Person(String anyName,Education nameEdu){
        this.name = anyName;
        this.edu = nameEdu;
    } 
    public Education getEducation(){
        return this.edu;
    }
    @Override
    public String toString(){
        return this.name +", "+ this.edu;
    }
    
}
