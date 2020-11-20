package com.wgu.gigacamp.Model;

public class State {
    int stateID;
    String stateName;

    public State(int stateID, String stateName) {
        setStateID(stateID);
        setStateName(stateName);
    }

    /**
     * _______________________________________________________________________
     *                                  SETTERS
     * -----------------------------------------------------------------------
     */

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * _______________________________________________________________________
     *                                  GETTERS
     * -----------------------------------------------------------------------
     */

    public int getStateID() {
        return stateID;
    }

    public String getStateName() {
        return stateName;
    }

}
