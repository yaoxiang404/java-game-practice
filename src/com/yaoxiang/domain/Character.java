package com.yaoxiang.domain;

public class Character {
    public String name;
    public int HP;
    public int maxHp;
    public int attack;
    public int defense;
    public Character(){
    }
    public Character(String name, int HP, int attack, int defense){
        this.name = name;
        this.HP = HP;
        this.maxHp = HP;
        this.attack = attack;
        this.defense = defense;
    }
    //判断是否存活
    public boolean isAlive(){
        return HP > 0;
    }
    //回复血量
    public void heal(int amount){
        HP += amount;
        if (HP > maxHp) HP = maxHp;
    }
    //受到伤害
    public void takeDamage(int damage){
        HP -= damage;
        if (HP < 0) HP = 0;
    }
    //增加最大血量
    public void increaseMaxHp(int amount){
        maxHp += amount;
    }
    //设置血量为最大血量
    public void setHpToMax(){
        HP = maxHp;
    }
    //展示属性
    public String show(){
        return name+"[ 血量："+HP+"/"+maxHp+" 攻击力："+attack+" 防御力："+defense+"]";
    }
}
