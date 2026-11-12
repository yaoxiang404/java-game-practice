package com.yaoxiang.domain;

public class EnenyCharacter extends Character {
    public String skill;
    public boolean defending;
    public EnenyCharacter(){
        super();
    }
    public EnenyCharacter(String name, int HP, int attack, int defense,String skill){
        super(name,HP,attack,defense);
        this.skill = skill;
    }
    public void takeDamage(int damage){
        if(defending){
            damage=damage/2>1?damage/2:1;
            defending=false;
        }
        //调用父类方法扣血
        super.takeDamage(damage);
    }
}
