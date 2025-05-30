package com.example.jsonprofesor;

public class Hero {
    private String squadName;
    private String homeTown;
    private long formed;
    private String secretBase;
    private boolean active;
    private Member[] members;


    public String getSquadName() { return squadName; }

    public void setSquadName(String value) { this.squadName = value; }


    public String getHomeTown() { return homeTown; }

    public void setHomeTown(String value) { this.homeTown = value; }


    public long getFormed() { return formed; }

    public void setFormed(long value) { this.formed = value; }


    public String getSecretBase() { return secretBase; }

    public void setSecretBase(String value) { this.secretBase = value; }


    public boolean getActive() { return active; }

    public void setActive(boolean value) { this.active = value; }


    public Member[] getMembers() { return members; }

    public void setMembers(Member[] value) { this.members = value; }
}
