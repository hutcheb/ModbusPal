/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modbuspal.automation;

/**
 * This interface defines method that a class must implement in order to receive
 * notifications about the values of automations.
 * @author nnovic
 */
public interface AutomationExecutionListener
{
    /**
     * this event is triggered when an automation starts.
     * @param source
     */
    public void automationHasStarted(Automation source);

    /**
     * this event is triggered when an automation ends.
     * @param source
     */
    public void automationHasEnded(Automation source);

    /**
     * this event is triggered when the current value of the automation is
     * modified.
     * @param source
     * @param value
     */
    public void automationValueHasChanged(Automation source, double time, double value);

    public void automationReloaded(Automation source);
}