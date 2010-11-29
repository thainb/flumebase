// (c) Copyright 2010 Odiago, Inc.

package com.odiago.rtengine.lang;

import java.util.ArrayList;
import java.util.List;

import org.apache.avro.Schema;

/**
 * Represents a nullable instance of a type (e.g. "INT" vs. "INT NOT NULL").
 */
public class NullableType extends Type {
  
  /** the type name of the non-null type being wrapped. */
  private TypeName mNullableType;

  protected NullableType(TypeName name) {
    super(TypeName.NULLABLE);
    mNullableType = name;
  }

  @Override
  public boolean isNullable() {
    return true;
  }

  /** 
   * @return the TypeName of the non-null type being wrapped.
   */
  public TypeName getNullableTypeName() {
    return mNullableType;
  }

  @Override
  public Schema getAvroSchema() {
    // Our schema is a union of our ordinary type, or null.
    List<Schema> unionTypes = new ArrayList<Schema>();
    unionTypes.add(getAvroSchema(mNullableType));
    unionTypes.add(Schema.create(Schema.Type.NULL));
    return Schema.createUnion(unionTypes);
  }

  @Override
  public String toString() {
    return "NULLABLE " + mNullableType;
  }

  @Override
  public int hashCode() {
    // Return a different position than its basic type counterpart.
    return 3 * super.hashCode() + 7;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other == null) {
      return false;
    } else if (!other.getClass().equals(getClass())) {
      return false;
    }

    NullableType otherType = (NullableType) other;
    if (mNullableType.equals(otherType.mNullableType)) {
      return true;
    }

    return false;
  }
}