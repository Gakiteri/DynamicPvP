package net.gakiteri.dynamicpvp.data;

public class DataPlayer {

    private Boolean pvp;
    private int coolDown;

    public DataPlayer(Boolean pvp) {
        this.pvp = pvp;
        coolDown = 0;
    }

    /** PVP **/
    public void setPvp(Boolean val) {
        pvp = val;
    }
    public Boolean getPvp() {
        return pvp;
    }

    /** COOLDOWN **/
    public void setCoolDown(int val) {
        coolDown = val;
    }
    public int getCoolDown() {
        return coolDown;
    }

}
