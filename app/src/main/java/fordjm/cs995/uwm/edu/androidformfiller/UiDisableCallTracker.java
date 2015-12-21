package fordjm.cs995.uwm.edu.androidformfiller;

import java.util.Stack;

public class UiDisableCallTracker {
    private Stack<Boolean> isDisabled;

    public UiDisableCallTracker() {
        isDisabled = new Stack<Boolean>();
    }

    public void addDisable() {
        isDisabled.push(true);
    }

    public void addEnable() {
        isDisabled.pop();
    }

    public boolean shouldEnable() {
        return isDisabled.size() == 0;
    }

}
