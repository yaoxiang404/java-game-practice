package com.yaoxiang.ui;

import com.yaoxiang.domain.EnenyCharacter;
import com.yaoxiang.domain.HeroCharacter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightingGame {
    public static void gameStart(String username){
        System.out.println("╔════════════════════════════════╗");
        System.out.println("    🎮"+username+" 欢迎来到文字格斗游戏 🎮   ");
        System.out.println("╚════════════════════════════════╝");
        //创建玩家（名字+属性分配）
        HeroCharacter player = createPlayerCharacter(username);
        //打印玩家角色信息
        System.out.println("玩家"+username+"的角色创建成功");
        System.out.println("初始属性:"+player.show());
        System.out.println("拥有的技能:"+player.showSkill());
        //创建敌人
//        | 敌人名称 | 生命值 | 攻击力 | 防御力 | 技能（变量）                                           |
//| -------- | ------ | ------ | ------ | ------------------------------------------------------ |
//                | 初级战士 | 80     | 15     | 10     | 猛击（150%伤害）                                       |
//| 敏捷刺客 | 60     | 20     | 5      | 快速攻击（2次50%伤害）                                 |
//| 重装坦克 | 120    | 10     | 20     | 防御姿态（下回合伤害减半） buff（ boolean defendding） |
//| 神秘法师 | 70     | 25     | 8      | 火球术（180%伤害）                                     |
        ArrayList<EnenyCharacter> enemyList = new ArrayList<>();
        enemyList.add(new EnenyCharacter("初级战士",80,15,10,"猛击（150%伤害）"));
        enemyList.add(new EnenyCharacter("敏捷刺客",60,20,5,"快速攻击（2次50%伤害）"));
        enemyList.add(new EnenyCharacter("重装坦克",120,10,20,"防御姿态（下回合伤害减半）"));
        enemyList.add(new EnenyCharacter("神秘法师",70,25,8,"火球术（180%伤害）"));
        int count = 1;//记录第几个敌人战斗
        int wins = 0;
        while(player.isAlive()){
            if(wins != 0){
                for (int i = 0; i < enemyList.size(); i++){
                    EnenyCharacter c = enemyList.get(i);
                    c.maxHp = c.maxHp+10;
                    c.HP = c.maxHp;
                    c.attack = c.attack+3;
                    c.defense = c.defense+2;
                    //每场战斗baff清理
                    c.defending = false;
                }
            }
            //随机选择敌人
            Random r = new Random();
            int index = r.nextInt(enemyList.size());
            EnenyCharacter enemy = enemyList.get(index);
            System.out.println(enemy.show());
            System.out.println("--------------------------");
            System.out.println("⚔\uFE0F 第"+count+"场战斗开始！对手: "+enemy.name);
            int round = 1;
            while (player.isAlive()) {
                System.out.println("--------------------------");
                System.out.println("回合开始");
                System.out.println("玩家"+player.name+"的生命值:"+getHealthBar(player.name,player.maxHp,player.HP));
                System.out.println("敌人"+enemy.name+"的生命值:"+getHealthBar(enemy.name,enemy.maxHp,enemy.HP));
                //5.4玩家回合，选择行动（1普通攻击2强力一击/3生命汲取）
                playerTurn(player,enemy);
                //5.5敌人是否被击败(判断敌方血量是否为0)
                if(!enemy.isAlive()){
                    //🎉 你击败了 敏捷刺客！
                    System.out.println("🎉 你击败了 "+enemy.name+"！");
                    //我方胜利了，连胜的计数器自增一次
                    wins++;
                    break;
                }
                //5.6敌人回合，选择行动（50%1普通攻击50%2技能）
                enemyTurn(player,enemy);
                //5.7回合结束，判断玩家是否存活
                if(!player.isAlive()){
                    System.out.println("💀 你被击败了！");
                    break;
                }
                //没有击败，进入下一回合
                round++;

            }
            //5.8跟一个敌人的战斗结束之后
            if(player.isAlive()){
                //计算玩家回复多少血
                int healHP = r.nextInt(21)+20;
                //回复玩家血量
                player.heal(healHP);
                //💚 战斗结束！你恢复了 36 点生命值
                System.out.println("\uD83D\uDC9A 战斗结束！你回复了"+healHP+"点生命值");
                System.out.println("\uD83C\uDFC6 当前胜场:"+wins);
                System.out.println("--------------------------");
            }
            //5.9每3场属性增加
            if(player.isAlive() && count%3==0 && wins>0){
                player.maxHp = player.maxHp+30;
                player.attack = player.attack+5;
                player.defense = player.defense+3;
                System.out.println("恭喜你，你的属性提升了！");
                System.out.println("最大生命+30，攻击力+5，防御力+3");
                System.out.println("当前属性:"+player.show());
            }
            //5.10询问是否继续战斗
            if(player.isAlive()){
                System.out.println("是否继续战斗？(y/n)");
                Scanner scanner = new Scanner(System.in);
                String chooce = scanner.next();
                if("y".equalsIgnoreCase(chooce)){
                    count++;
                    //继续战斗，回到5.4
                    continue;
                }else if("n".equalsIgnoreCase(chooce)){
                    break;
                }else{
                    System.out.println("没有这个选项，默认继续");
                }
            }
        }
        //6游戏结算
        System.out.println("═══════════════════════════════");
        System.out.println("🏆 游戏结束！");
        System.out.println("感谢游玩文字格斗游戏！");
        System.out.println("最终胜场:"+wins);
        System.exit(0);
    }
//敌人的回合
    private static void enemyTurn(HeroCharacter player, EnenyCharacter enemy) {
        System.out.println(enemy.name+"的回合");
        //（50%1普通攻击50%2技能）
        //判断敌人是谁
        String action = "普通攻击";
        Random r = new Random();
        int num = r.nextInt(10);
        if(num>=5){
            action = enemy.skill;
        }
        //判断action
        switch (action){
            case "普通攻击":
                System.out.println(enemy.name+"使用了"+action);
                int damage1=calculateDamage(enemy.attack,player.defense);
                System.out.println("\uD83D\uDCA5" + enemy.name + "对你使用"+action+enemy.name+"造成:"+damage1+"伤害");
                player.takeDamage(damage1);
                break;
            case "猛击":
                System.out.println(enemy.name+"使用了"+action);
                int damage2=calculateDamage((int)(enemy.attack*1.5),player.defense);
                System.out.println("\uD83D\uDCA5" + enemy.name + "对你使用"+action+enemy.name+"造成:"+damage2+"伤害");
                player.takeDamage(damage2);
                break;
            case "快速攻击":
                System.out.println(enemy.name+"使用了"+action);
                int damage3 = 0;
                for (int i = 0; i < 2; i++) {
                    int temp = calculateDamage(enemy.attack/2,player.defense);
                    damage3 =damage3+temp;
                }
                System.out.println("\uD83D\uDCA5" + enemy.name + "对你使用"+action+enemy.name+"造成:"+damage3+"伤害");
                player.takeDamage(damage3);
                break;
            case "防御姿态":
                System.out.println(enemy.name+"使用了"+action);
                //修改变量
                enemy.defending = true;
                System.out.println("敌人进入了防御姿态，下一回合受到的伤害减半");
                break;
            case "火球术":
                System.out.println(enemy.name+"使用了"+action);
                int damage4=calculateDamage((int)(enemy.attack*1.8),player.defense);
                System.out.println("\uD83D\uDCA5" + enemy.name + "对你使用"+action+enemy.name+"造成:"+damage4+"伤害");
                player.takeDamage(damage4);
                break;
        }
    }

    //█
    public static String getHealthBar(String name, int maxHp, int HP){
        int barlength = 20;
        int filled = (int)((HP*1.0 / maxHp)*barlength);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(":[");
        for (int i = 0; i < barlength; i++) {
            if(i<filled){
                sb.append("█");
            }else{
                sb.append(" ");
            }
        }
        sb.append("]").append(HP).append("/").append(maxHp).append("HP");
        return sb.toString();
    }
    //用来创建玩家角色的方法
    public static HeroCharacter createPlayerCharacter(String username){
        System.out.println("玩家"+username+"创建角色");
        //属性分配
        int point = 20;
        System.out.println("请分配属性点(共20点)");
        System.out.println("生命值(每点+10hp)");
        System.out.println("攻击力(每点+2)");
        System.out.println("防御力(每点+1)");
        Scanner sc = new Scanner(System.in);
        String[] attributes ={"生命值","攻击力","防御力"};
        int [] vslued = new int[3];
        for (int i = 0; i < attributes.length; i++) {
            System.out.println("请输入" + attributes[i] + "的点数:" + "(剩余点数：" + point + ")");
            int  input = sc.nextInt();
            if(input<0){
                System.out.println("无效");
                input=0;
            }
            if(input>point){
                System.out.println("属性点不够,剩余全部给:"+attributes[i]);
                input=point;
            }
            //计算还有多少属性点
            point=point-input;
            vslued[i]=input;
        }
        //创建玩家角色的对象
        HeroCharacter player =new HeroCharacter(
                username,
                100+vslued[0]*10,
                10+vslued[1]*2,
                0+vslued[2]
        );
        //添加玩家的技能
        player.skillList.add("普通攻击");
        player.skillList.add("强力一击");
        player.skillList.add("生命汲取");
        return player;
    }
    public static void playerTurn(HeroCharacter player,EnenyCharacter enemy){
        System.out.println("========玩家回合========");
        System.out.println("1.普通攻击");
        System.out.println("2.强力一击");
        System.out.println("3.生命汲取");
        Scanner sc = new Scanner(System.in);
        String choose = sc.next();
        switch (choose){
            default:
                System.out.println("无效选择,默认普通攻击");
            case "1":
                //普通攻击
                int damage1 = calculateDamage(player.attack,enemy.defense);
                System.out.println("\uD83D\uDCA5 你对"+enemy.name+enemy.name+"受到伤害:"+damage1+"伤害");
                enemy.takeDamage(damage1);
                break;
            case "2":
                //强力一击
                if(player.HP>10){
                    player.takeDamage(10);
                    int damage2 = calculateDamage((int)(player.attack*1.8),enemy.defense);
                    System.out.println("\uD83D\uDCA5 消耗10HP,你对"+enemy.name+enemy.name+"使用强力一击，造成:"+damage2+"伤害");
                    enemy.takeDamage(damage2);
                }else{
                    System.out.println("生命值不足10,无法使用");
                }
                break;
            case "3":
                //生命汲取
                if(player.HP>10){
                    player.takeDamage(10);
                    Random r = new Random();
                    int healHP= r.nextInt(21);
                    player.heal(healHP);
                    System.out.println("\uD83D\uDC9A 消耗10HP,你使用生命汲取，回复:"+healHP+"生命值");
                }else{
                    System.out.println("生命值不足10,无法使用");
                }
                break;
        }

    }
    //作用：用来计算玩家对敌人造成的伤害
    public static int calculateDamage(int attack, int defense){
        int damage = attack - defense;
        if(damage <1){
            damage=1;
        }
        return damage;
    }
}
