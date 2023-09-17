public class Character {
    private int mHP;
    private String mName;
    private int mStartATK;
    private int mTotalMana;
    public Character(String name, int startingHP, int startingATK) {
        mName = name;
        mHP = startingHP;
        mStartATK = startingATK;
    }
    public void takeDamage(int dmg) {
        mHP = mHP - dmg;
        if (mHP < 0) {
            mHP = 0;
        }
    }
}
