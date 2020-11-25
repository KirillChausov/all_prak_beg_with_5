package ru.mirea.prak_17and18;

public class Action {
    private String nameAction;
    private int numNameAct0;
    private int numNameAct1;
    private Action nextAction0;
    private Action nextAction1;

    public Action(String nameAction) {
        this.nameAction = nameAction;
    }

    public String getNameAction() {
        return nameAction;
    }

    public Action getNextAction0() {
        return nextAction0;
    }

    public Action getNextAction1() {
        return nextAction1;
    }

    public int getNumNameAct0() {
        return numNameAct0;
    }

    public int getNumNameAct1() {
        return numNameAct1;
    }

    public void setNextActions(Action nextAction0, Action nextAction1, int numNameAct0, int numNameAct1) {
        this.nextAction0 = nextAction0;
        this.nextAction1 = nextAction1;
        this.numNameAct0 = numNameAct0;
        this.numNameAct1 = numNameAct1;
    }
}
