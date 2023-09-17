public class Enemy {
    private int mHP;
    private int mAttack;
    private String mName;

    public Enemy(String name, int attack, int hp) {
        mName = name;
        mAttack = attack;
        mHP = hp;
    }

    // return current HP after taking incoming damanger
    // HP can never be below 0
    public int takeDamage(int incomingDmg) {
        mHP = mHP - incomingDmg;
        if(mHP < 0) {
            mHP = 0;
        }
        return mHP;
    }

    public String getName() {
        return mName;
    }

    public int getAttack() {
        return mAttack;
    }

    public int getHP() {
        return mHP;
    }
}
