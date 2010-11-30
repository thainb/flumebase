// (c) Copyright 2010 Odiago, Inc.

package com.odiago.rtengine.exec;

import java.io.IOException;

import com.cloudera.flume.core.Event;

import com.odiago.rtengine.parser.TypedField;

/**
 * A wrapper interface around Events that offers access to individual fields.
 * This wrapper manages the deserialization of the event through Avro, a parser,
 * or other necessary means and delivers named, typed fields to its client.
 * EventWrapper implementations should be as lazy as possible for efficiency.
 *
 * The EventWrapper should be considered a read-only view of the input data;
 * if an output event from a given processing phase contains different data
 * than its input, then the event should be copied; the data in the prior
 * wrapper should not be modified.
 */
public abstract class EventWrapper {
  
  /**
   * Resets the EventWrapper's internal state and wraps around the specified
   * event 'e'.
   */
  public abstract void reset(Event e);

  /**
   * Returns an object representing the value for the specified field.
   */
  public abstract Object getField(TypedField field) throws IOException;

  /**
   * Return the event this wrapper operates on.
   */
  public abstract Event getEvent();
}