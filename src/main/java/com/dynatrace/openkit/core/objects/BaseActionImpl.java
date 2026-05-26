/**
 * Copyright 2018-2021 Dynatrace LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dynatrace.openkit.core.objects;

import com.dynatrace.openkit.api.Action;
import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.api.WebRequestTracer;
import com.dynatrace.openkit.protocol.Beacon;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

/**
 * Abstract base class implementing the {@link Action} interface.
 */
public abstract class BaseActionImpl extends OpenKitComposite implements CancelableOpenKitObject, Action {

    /**
     * {@link Logger} for tracing log message
     */
    final Logger logger;

    /**
     * Parent object of this {@link Action}
     */
    private OpenKitComposite parent;

    /**
     * The parent action id
     */
    final int parentActionID;

    /**
     * object for synchronization, internal for derived classes within this package
     */
    final Object lockObject = new Object();

    /**
     * Unique identifier of this {@link Action}
     */
    final int id;

    /**
     * Name of this {@link Action}
     */
    final String name;

    /**
     * start time when this {@link Action} has been started
     */
    private final long startTime;

    /**
     * end time when this {@link Action} has been ended
     */
    private long endTime = -1;

    /**
     * Start sequence number of this {@link Action}
     */
    private final int startSequenceNo;

    /**
     * End sequence number of this {@link Action}
     */
    private int endSequenceNo = -1;

    /**
     * boolean indicating whether this action has been left or not
     */
    private boolean isActionLeft;

    /**
     * Beacon for sending data
     */
    final Beacon beacon;

    /**
     * Constructor for constructing the base action class.
     *
     * @param logger The logger used to log information
     * @param parent The parent object, to which this web action belongs to
     * @param name The action's name
     * @param beacon The beacon for retrieving certain data and sending data
     */
    BaseActionImpl(Logger logger, OpenKitComposite parent, String name, Beacon beacon) {
        this.logger = logger;
        this.parent = parent;
        parentActionID = parent.getActionID();
        id = beacon.createID();
        this.name = name;
        startTime = beacon.getCurrentTimestamp();
        startSequenceNo = beacon.createSequenceNumber();
        isActionLeft = false;
        this.beacon = beacon;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportEvent(String eventName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportValue(String valueName, int value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportValue(String valueName, long value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportValue(String valueName, double value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportValue(String valueName, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportError(String errorName, int errorCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportError(String errorName, String causeName, String causeDescription, String causeStackTrace) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action reportError(String errorName, Throwable throwable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer traceWebRequest(URLConnection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer traceWebRequest(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action leaveAction() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Action cancelAction() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Action doLeaveAction(boolean discardData) {
        synchronized (lockObject) {
            if (isActionLeft()) {
                // leaveAction has been called previously
                return getParentAction();
            }
            isActionLeft = true;
        }
        // close all child object
        // Note: at this point it's save to do any further operations outside a synchronized block
        // after the endTime has been set, no further child objects must be added
        List<OpenKitObject> childObjects = getCopyOfChildObjects();
        for (OpenKitObject childObject : childObjects) {
            try {
                if (discardData) {
                    if (childObject instanceof CancelableOpenKitObject) {
                        ((CancelableOpenKitObject) childObject).cancel();
                    } else {
                        logger.warning(childObject.toString() + " is not cancelable - falling back to close() instead");
                        childObject.close();
                    }
                } else {
                    childObject.close();
                }
            } catch (IOException e) {
                // should not happen, nevertheless let's log an error
                logger.error(this + "Caught IOException while closing OpenKitObject (" + childObject + ")", e);
            }
        }
        // set end time and end sequence number
        endTime = beacon.getCurrentTimestamp();
        endSequenceNo = beacon.createSequenceNumber();
        // handle this object
        if (!discardData) {
            beacon.addAction(this);
        }
        // detach from parent
        parent.onChildClosed(this);
        parent = null;
        return getParentAction();
    }

    @Override
    public long getDurationInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the parent {@link} Action, which might be {@code null} in case the parent does not implement {@link Action}.
     *
     * @return The parent action object, or {@code null} if parent does not implement {@link Action}.
     */
    protected abstract Action getParentAction();

    @Override
    void onChildClosed(OpenKitObject childObject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getActionID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getName() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getParentID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getStartTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getEndTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getStartSequenceNo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getEndSequenceNo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isActionLeft() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
